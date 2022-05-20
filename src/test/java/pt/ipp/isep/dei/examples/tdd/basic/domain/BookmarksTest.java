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
        assertEquals(url, bookmarks.getBookmarkedURLs().get(0).getUrl().toString());
    }

    @Test
    public void checkIfExceptionIsThrownwhenInvalidURLisEntered() {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url = "google.com";

        //then
        assertThrows(MalformedURLException.class, () -> {
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
        assertEquals(url, bookmarks.getBookmarkedURLs().get(0).getUrl().toString());
        assertEquals(tag, bookmarks.getBookmarkedURLs().get(0).getTag());
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
    public void checkIfNonExistingBookmarksReturnsNull() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url = "https://google.com";

        //when
        bookmarks.addURLtoBookmarks(url);

        //then
        assertNull(bookmarks.findBookmarkByURL(""));

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


    @Test
    public void checkIfRatingIsIncreasedWhileAddingANewBookmarkThatExists() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url = "https://google.com";

        //when
        bookmarks.addURLtoBookmarks(url);
        bookmarks.addURLtoBookmarks(url);

        assertEquals(1, bookmarks.getBookmarkedURLs().size());
        assertEquals(1, bookmarks.getBookmarkedURLs().get(0).getRating());
    }

    @Test
    public void checkIfAmountOfSecureURLsCanBeDetermined() throws MalformedURLException {
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        int actual = 0;
        String url1 = "https://google.com";
        String url2 = "https://orf.at";
        String url3 = "http://orf.at";

        //when
        bookmarks.addURLtoBookmarks(url1);
        actual = bookmarks.getNumberOfSecureURLsInBookmarksList();

        //then
        assertEquals(1, actual);


        //when
        bookmarks.addURLtoBookmarks(url2);
        actual = bookmarks.getNumberOfSecureURLsInBookmarksList();

        //then
        assertEquals(2, actual);

        //when
        bookmarks.addURLtoBookmarks(url3);
        actual = bookmarks.getNumberOfSecureURLsInBookmarksList();

        //then
        assertEquals(2, actual);

    }
}