package pt.ipp.isep.dei.examples.tdd.basic.domain;

import lombok.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


@AllArgsConstructor
@Getter
public class Bookmarks {

    private final ArrayList<Bookmark> bookmarkedURLs;

    public void addURLtoBookmarks(String urlAsString) throws MalformedURLException {
        Bookmark url = new Bookmark(new URL(urlAsString));
        this.bookmarkedURLs.add(url);
    }
}
