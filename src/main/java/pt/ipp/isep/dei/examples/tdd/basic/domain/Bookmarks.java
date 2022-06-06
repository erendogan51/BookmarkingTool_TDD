package pt.ipp.isep.dei.examples.tdd.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@AllArgsConstructor
@Getter
public class Bookmarks {

    private final String owner;
    private final List<Bookmark> bookmarkedURLs;

    public void addURLtoBookmarks(String urlAsString, String tag) throws MalformedURLException {

        List<Bookmark> bookmarkList = findBookmarkByURL(urlAsString);
        Set<String> keywords = new HashSet<>();
        keywords.add(tag);
        keywords.add(urlAsString);


        if (bookmarkList == null) {
            Bookmark url = Bookmark
                    .builder()
                    .url(new URL(urlAsString))
                    .tag(tag)
                    .rating(0)
                    .keywords(keywords)
                    .localDateTime(LocalDateTime.now())
                    .build();
            this.bookmarkedURLs.add(url);
        }

        if (bookmarkList != null) {
            increaseRatingForExistingBookmark(bookmarkList.get(0));
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

    public void increaseRatingForExistingBookmark(Bookmark bookmark) {

        if (bookmark == null) return;

        bookmark.increaseRating();
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

    public void removeKeywordFromBookmark(String url, String tag) {

        List<Bookmark> bookmarkList = findBookmarkByURL(url);

        if (bookmarkList == null || bookmarkList.isEmpty()) {
            throw new IllegalArgumentException("no such bookmark found");
        }

        if (!bookmarkList.get(0).getTag().equals(tag)) {
            throw new IllegalArgumentException("provided tag doesnt match existing one");
        }

        if (bookmarkList != null && !bookmarkList.isEmpty() && bookmarkList.get(0).getTag().equals(tag)) {
            bookmarkList.get(0).setTag(null);
        }

    }

    public void removeURLfromBookmarks(String url) {

        List<Bookmark> bookmarkList = findBookmarkByURL(url);


        if (bookmarkList == null || bookmarkList.isEmpty()) {
            throw new IllegalArgumentException("no such bookmark found");
        }

        if (bookmarkList.get(0).getUrl().toString().equals(url)) {
            this.bookmarkedURLs.remove(0);
        }

    }

    public void sortBookmarksByRatingDesc() {
        bookmarkedURLs.sort((s1, s2) -> Integer.compare(s2.getRating(), s1.getRating()));
    }

    public void sortBookmarksByDatetime() {
        Collections.sort(bookmarkedURLs);
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
