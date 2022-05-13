package pt.ipp.isep.dei.examples.tdd.basic.domain;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class BookmarksTest {

    @Test
    public void checkIfURLCanBeBookmarked() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url = "https://google.com";

        //When
        bookmarks.addURLtoBookmarks(url);

        //Then
        assertEquals(url,bookmarks.getBookmarkedURLs().get(0).getUrl().toString());
    }

    @Test
    public void checkIfExceptionIsThrownWhenInvalidURLisEntered(){
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url = "google.com";

        //Then
        assertThrows(MalformedURLException.class, ()->{
            //When
            bookmarks.addURLtoBookmarks(url);
        });
    }

    @Test
    public void checkIfTagCanBeAddedToExistingBookmarkedURL() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url = "https://google.com";
        String tag = "google";

        //When
        bookmarks.addURLtoBookmarks(url);
        bookmarks.addTagToExistingBookmark(tag, url);

        //Then
        assertEquals(tag, bookmarks.getBookmarkedURLs().get(0).getTag());
    }

    @Test
    public void checkIfURLWithTagCanBeAdded() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url = "https://google.com";
        String tag = "google";

        //When
        bookmarks.addURLtoBookmarks(url, tag);

        //Then
        assertEquals(url,bookmarks.getBookmarkedURLs().get(0).getUrl().toString());
        assertEquals(tag,bookmarks.getBookmarkedURLs().get(0).getTag());
    }
}