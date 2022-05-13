package pt.ipp.isep.dei.examples.tdd.basic.domain;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
}