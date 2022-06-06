package pt.ipp.isep.dei.examples.tdd.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@AllArgsConstructor
@Getter
public class Bookmarks {

    private final List<Bookmark> bookmarkedURLs;

    public void addURLtoBookmarks(String urlAsString, String tag) throws MalformedURLException {

        List<Bookmark> bookmarkList = findBookmarkByURL(urlAsString);
        Set<String> keywords = new HashSet<>();
        keywords.add(tag);
        keywords.add(urlAsString);


        if (bookmarkList == null || bookmarkList.isEmpty()) {
            Bookmark url = Bookmark
                    .builder()
                    .url(new URL(urlAsString))
                    .tag(tag)
                    .rating(0)
                    .keywords(keywords)
                    .build();
            this.bookmarkedURLs.add(url);
        }

        if (bookmarkList != null && !bookmarkList.isEmpty()){
            increaseRatingForExistingBookmark(bookmarkList);
        }


    }

    public void addTagToExistingBookmark(String url, String tag) {
        findBookmarkByURL(url).get(0).setTag(tag);
    }

    public List<Bookmark> findBookmarkByURL(String url) {
        List<Bookmark> bookmarkList = this.bookmarkedURLs
                .stream()
                .filter(u -> u.getUrl().toString().equals(url))
                .collect(Collectors.toList());

        if (bookmarkList.isEmpty()) return null;

        return bookmarkList;
    }

    public void increaseRatingForExistingBookmark(List<Bookmark> bookmarkList) {

        if (bookmarkList == null || bookmarkList.isEmpty()) return;

        bookmarkList.get(0).increaseRating();
    }

    public int getNumberOfSecureURLsInBookmarksList() {
        int numberOfSecureURLs = 0;

        for (Bookmark bookmark : this.bookmarkedURLs) {
            if (bookmark.getUrl().getProtocol().equals("https")) numberOfSecureURLs++;
        }

        return numberOfSecureURLs;
    }

    public Set<Bookmark> filterBookmarksByKeyWords(Set<String> keywords) {

        Set<Bookmark> bookmarkHashSet = new HashSet<>();

        for (Bookmark bookmark : this.bookmarkedURLs) {
            for (String keyword : keywords) {
                if (bookmark.getKeywords().contains(keyword)) {
                    bookmarkHashSet.add(bookmark);
                    break;
                }
            }
        }

        return bookmarkHashSet;
    }



    /*
    public List<Bookmark> findBookmarksByDomain(String domain){
        return null;
    }

    public void associateURLWithExistingBookmarks(String url) throws IOException, URISyntaxException {

        List<Bookmark> bookmarkList = findBookmarkByURL(url);

        if (bookmarkList == null){
            return;
        }

        Bookmark bookmark = bookmarkList.get(0);
        String domain = getDomainOfURL(url);

        System.out.println(domain);

    }

    public String getDomainOfURL(String url) {
        Pattern pattern = Pattern.compile("(https?:\\/\\/)(www\\.)[-a-zA-Z0-9@:%._\\+~#=]{2,256}");
        Matcher matcher = pattern.matcher(url);

        return matcher.group();
    }

     */
}
