package pt.ipp.isep.dei.examples.tdd.basic.domain;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class BookmarksTest {

    @Test
    public void checkIfURLCanBeBookmarked() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url = "https://google.com";

        //when
        bookmarks.addURLtoBookmarks(url);

        //then
        assertEquals(url,bookmarks.getBookmarkedURLs().get(0).getUrl().toString());
    }

    @Test
    public void checkIfExceptionIsThrownwhenInvalidURLisEntered(){
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url = "google.com";

        //then
        assertThrows(MalformedURLException.class, ()->{
            //when
            bookmarks.addURLtoBookmarks(url);
        });
    }

    @Test
    public void checkIfTagCanBeAddedToExistingBookmarkedURL() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url = "https://google.com";
        String tag = "google";

        //when
        bookmarks.addURLtoBookmarks(url);
        bookmarks.addTagToExistingBookmark(tag, url);

        //then
        assertEquals(tag, bookmarks.getBookmarkedURLs().get(0).getTag());
    }

    @Test
    public void checkIfURLWithTagCanBeAdded() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url = "https://google.com";
        String tag = "google";

        //when
        bookmarks.addURLtoBookmarks(url, tag);

        //then
        assertEquals(url,bookmarks.getBookmarkedURLs().get(0).getUrl().toString());
        assertEquals(tag,bookmarks.getBookmarkedURLs().get(0).getTag());
    }

    @Test
    public void checkIfExistingBookmarksCanBeFoundByURL() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url = "https://google.com";

        //when
        bookmarks.addURLtoBookmarks(url);
        List<Bookmark> bookmarkList = bookmarks.findBookmarkByURL(url);
        
        //then
        assertEquals(url, bookmarkList.get(0).getUrl().toString());
        
    }

    @Test
    public void checkIfNonExistingBookmarksThrowsException() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url = "https://google.com";

        //when
        bookmarks.addURLtoBookmarks(url);

        //then
        assertThrows(IllegalArgumentException.class, ()->
                //when
                bookmarks.findBookmarkByURL("url")
                );

    }


    @Test
    public void checkIfRatingIsIncreasedWhenDuplicateURLisBookmarked() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url = "https://google.com";

        //when
        bookmarks.addURLtoBookmarks(url);
        bookmarks.increaseRatingForExistingBookmark(bookmarks.getBookmarkedURLs());

        assertEquals(1, bookmarks.getBookmarkedURLs().get(0).getRating());

    }

    @Test
    public void checkIfRatingIsNotIncreasedWhenDuplicateURLisNotPresent() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url = "https://google.com";

        //when
        //bookmarks.addURLtoBookmarks(url);
        bookmarks.increaseRatingForExistingBookmark(bookmarks.getBookmarkedURLs());

        assertTrue(bookmarks.getBookmarkedURLs().isEmpty());

    }
}