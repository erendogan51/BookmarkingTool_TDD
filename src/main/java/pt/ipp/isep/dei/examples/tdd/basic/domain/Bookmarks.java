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
        Bookmark url = Bookmark
                .builder()
                .url(new URL(urlAsString))
                .build();
        this.bookmarkedURLs.add(url);
    }

    public void addURLtoBookmarks(String urlAsString, String tag) throws MalformedURLException {
        Bookmark url = Bookmark
                .builder()
                .url(new URL(urlAsString))
                .tag(tag)
                .build();
        this.bookmarkedURLs.add(url);
    }

    public void addTagToExistingBookmark(String tag, String url){
        findBookmarkByURL(url).get(0).setTag(tag);
    }

    public List<Bookmark> findBookmarkByURL(String url) {
        List<Bookmark> bookmarkList = this.bookmarkedURLs
                .stream()
                .filter(u -> u.getUrl().toString().equals(url))
                .collect(Collectors.toList());

        if (bookmarkList.isEmpty()) throw new IllegalArgumentException("URL was not found");

        return bookmarkList;
    }
}
