package pt.ipp.isep.dei.examples.tdd.basic.domain;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


class BookmarksTest {

    @Test
    public void checkIfURLCanBeBookmarked() throws MalformedURLException {
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());

        //given
        String url = "https://google.com";

        //then
        bookmarks.addURLtoBookmarks(url);

        //Then
        assertEquals(url,bookmarks.getBookmarkedURLs().get(0).getUrl().toString());
    }
}