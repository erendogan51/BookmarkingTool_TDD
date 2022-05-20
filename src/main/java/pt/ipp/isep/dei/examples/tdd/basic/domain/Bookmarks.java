package pt.ipp.isep.dei.examples.tdd.basic.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Getter
public class Bookmarks {

    private final List<Bookmark> bookmarkedURLs;

    public void addURLtoBookmarks(String urlAsString) throws MalformedURLException {

        List<Bookmark> bookmarkList = findBookmarkByURL(urlAsString);

        if (bookmarkList == null || bookmarkList.isEmpty()){
            Bookmark url = Bookmark
                    .builder()
                    .url(new URL(urlAsString))
                    .rating(0)
                    .build();
            this.bookmarkedURLs.add(url);
        }

        increaseRatingForExistingBookmark(bookmarkList);
    }

    public void addURLtoBookmarks(String urlAsString, String tag) throws MalformedURLException {

        List<Bookmark> bookmarkList = findBookmarkByURL(urlAsString);

        if (bookmarkList == null || bookmarkList.isEmpty()){
            Bookmark url = Bookmark
                    .builder()
                    .url(new URL(urlAsString))
                    .tag(tag)
                    .rating(0)
                    .build();
            this.bookmarkedURLs.add(url);
        }

        increaseRatingForExistingBookmark(bookmarkList);

    }

    public void addTagToExistingBookmark(String tag, String url){
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

    public void increaseRatingForExistingBookmark(List<Bookmark> bookmarkList){

        if (bookmarkList == null || bookmarkList.isEmpty()) return;

        bookmarkList.get(0).increaseRating();
    }
}
