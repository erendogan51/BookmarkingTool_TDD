package pt.ipp.isep.dei.examples.tdd.basic.domain;

import lombok.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@AllArgsConstructor
@Getter
public class Bookmarks {

    private final List<Bookmark> bookmarkedURLs;

    public void addURLtoBookmarks(String urlAsString) throws MalformedURLException {
        Bookmark url = new Bookmark(new URL(urlAsString));
        this.bookmarkedURLs.add(url);
    }

    public void addURLtoBookmarks(String urlAsString, String tag) throws MalformedURLException {
        Bookmark url = new Bookmark(new URL(urlAsString), tag);
        this.bookmarkedURLs.add(url);
    }

    public void addTagToExistingBookmark(String tag, String url){
        findBookmarkByURL(url).get(0).setTag(tag);
    }

    public List<Bookmark> findBookmarkByURL(String url) {
        return this.bookmarkedURLs
                .stream()
                .filter(u -> u.getUrl().toString().equals(url))
                .collect(Collectors.toList());
    }
}
