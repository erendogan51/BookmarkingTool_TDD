package pt.ipp.isep.dei.examples.tdd.basic.domain;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class BookmarksTest {

    @Test
    public void checkIfURLCanBeBookmarked() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url = "https://google.com";

        //when
        bookmarks.addURLtoBookmarks(url, null);

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
            bookmarks.addURLtoBookmarks(url, null);
        });
    }

    @Test
    public void checkIfBookmarkIsNotAddedWhenItExists() throws MalformedURLException {
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url = "https://google.com";


        //when
        bookmarks.addURLtoBookmarks(url, null);
        bookmarks.addURLtoBookmarks(url, null);

        //then
        assertEquals(1, bookmarks.getBookmarkedURLs().get(0).getRating());

    }


    @Test
    public void checkIfTagCanBeAddedToExistingBookmarkedURL() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url = "https://google.com";
        String tag = "google";

        //when
        bookmarks.addURLtoBookmarks(url, null);
        bookmarks.addTagToExistingBookmark(url, tag);

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
        bookmarks.addURLtoBookmarks(url, null);
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
        bookmarks.addURLtoBookmarks(url, null);

        //then
        assertNull(bookmarks.findBookmarkByURL(""));

    }


    @Test
    public void checkIfRatingIsIncreasedWhenDuplicateURLisBookmarked() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url = "https://google.com";

        //when
        bookmarks.addURLtoBookmarks(url, null);

        //then
        assertEquals(0, bookmarks.getBookmarkedURLs().get(0).getRating());

    }

    @Test
    public void checkIfRatingIsNotIncreasedWhenDuplicateURLisNotPresent() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url = "https://google.com";

        //when
        //bookmarks.addURLtoBookmarks(url);
        bookmarks.increaseRatingForExistingBookmark(bookmarks.getBookmarkedURLs());

        //then
        assertTrue(bookmarks.getBookmarkedURLs().isEmpty());

    }


    @Test
    public void checkIfRatingIsIncreasedWhileAddingANewBookmarkThatExists() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url = "https://google.com";

        //when
        bookmarks.addURLtoBookmarks(url, null);
        bookmarks.addURLtoBookmarks(url, null);

        //then
        assertEquals(1, bookmarks.getBookmarkedURLs().size());
        assertEquals(1, bookmarks.getBookmarkedURLs().get(0).getRating());
    }

    @Test
    public void checkIfAmountOfSecureURLsCanBeDetermined() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        int actual = 0;
        String url1 = "https://google.com";
        String url2 = "https://orf.at";
        String url3 = "http://orf.at";

        //when
        bookmarks.addURLtoBookmarks(url1, null);
        actual = bookmarks.getNumberOfSecureURLsInBookmarksList();

        //then
        assertEquals(1, actual);

        //when
        bookmarks.addURLtoBookmarks(url2, null);
        actual = bookmarks.getNumberOfSecureURLsInBookmarksList();

        //then
        assertEquals(2, actual);

        //when
        bookmarks.addURLtoBookmarks(url3, null);
        actual = bookmarks.getNumberOfSecureURLsInBookmarksList();

        //then
        assertEquals(2, actual);

    }

    @Test
    public void checkIfBookmarksCanBeFiltersByOneKeyWord() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url1 = "https://google.com";
        String url2 = "https://mail.google.com";
        Set<String> keywords = new HashSet<>();

        //when
        bookmarks.addURLtoBookmarks(url1, "tag1");
        bookmarks.addURLtoBookmarks(url2, "tag2");

        keywords.add("tag1");

        Set<Bookmark> actual = bookmarks.filterBookmarksByKeyWords(keywords);

        //then
        assertEquals(url1, actual.iterator().next().getUrl().toString());

    }

    @Test
    public void checkIfBookmarksCanBeFiltersByMultipleKeyWord() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url1 = "https://google.com";
        String url2 = "https://mail.google.com";
        Set<String> keywords = new HashSet<>();
        Set<Bookmark> bookmarkSet = new HashSet<>();

        //when
        bookmarks.addURLtoBookmarks(url1, "tag1");
        bookmarks.addURLtoBookmarks(url2, "tag2");
        bookmarkSet.add(bookmarks.findBookmarkByURL(url1).get(0));
        bookmarkSet.add(bookmarks.findBookmarkByURL(url2).get(0));

        bookmarks.findBookmarkByURL(url2).get(0).addKeyWord("keyword2");

        keywords.add("tag1");
        keywords.add("keyword2");

        Set<Bookmark> actual = bookmarks.filterBookmarksByKeyWords(keywords);

        //then
        assertTrue(actual.containsAll(bookmarkSet));

    }

    @Test
    public void checkIfTagCanBeRemovedFromBookmark() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url1 = "https://google.com";
        String url2 = "https://mail.google.com";

        //when
        bookmarks.addURLtoBookmarks(url1, "tag1");
        bookmarks.addURLtoBookmarks(url2, "tag2");
        bookmarks.removeKeywordFromBookmark(url1, "tag1");

        //then
        assertNull(bookmarks.getBookmarkedURLs().get(0).getTag());
    }

    @Test
    public void checkIfNonMatchingTagIsNotRemoved() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url1 = "https://google.com";
        String url2 = "https://mail.google.com";

        //when
        bookmarks.addURLtoBookmarks(url1, "tag1");
        bookmarks.addURLtoBookmarks(url2, "tag2");

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            //when
            bookmarks.removeKeywordFromBookmark(url1, "tag2");
        });
    }

    @Test
    public void checkIfDesiredBookMarkExistBeforeRemovingTag() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url1 = "https://google.com";
        String url2 = "https://mail.google.com";

        //when
        bookmarks.addURLtoBookmarks(url1, "tag1");

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            //when
            bookmarks.removeKeywordFromBookmark(url2, "tag2");
        });
    }

    @Test
    public void checkIfBookmarkCanBeRemoved() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url1 = "https://google.com";
        String url2 = "https://mail.google.com";

        //when
        bookmarks.addURLtoBookmarks(url1, "tag1");
        bookmarks.addURLtoBookmarks(url2, "tag2");
        bookmarks.removeURLfromBookmarks(url1);

        //then
        assertEquals(url2, bookmarks.getBookmarkedURLs().get(0).getUrl().toString());
    }

    @Test
    public void checkIfDesiredBookmarkExistsBeforeDeleting() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url1 = "https://google.com";
        String url2 = "https://mail.google.com";

        //when
        bookmarks.addURLtoBookmarks(url1, "tag1");


        //then
        assertThrows(IllegalArgumentException.class, () -> {
            bookmarks.removeURLfromBookmarks(url2);
        });
    }

    @Test
    public void checkIfLocalDateTimeIsAddedWhenAddingANewBookmark() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url1 = "https://google.com";
        String url2 = "https://mail.google.com";

        //when
        bookmarks.addURLtoBookmarks(url1, "tag1");

        //then
        assertNotNull(bookmarks.getBookmarkedURLs().get(0).getLocalDateTime());
    }





    /*
    @Disabled
    @Test
    public void checkIfAddedBookmarkIsBeingAssociatedWithOtherBookmarksFromSameDomain() throws IOException, URISyntaxException {
        //given
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url1 = "https://google.com";
        String url2 = "https://mail.google.com";

        //when
        bookmarks.addURLtoBookmarks(url1, null);
        bookmarks.addURLtoBookmarks(url2, null);

        bookmarks.associateURLWithExistingBookmarks(url1);

        assertFalse(false);

    }

    @Disabled
    @Test
    public void checkIfDomainCanBeExtractedFromURL() throws MalformedURLException {
        Bookmarks bookmarks = new Bookmarks(new ArrayList<>());
        String url1 = "https://www.google.com";
        String url2 = "https://www.mail.google.com";

        //when
        bookmarks.addURLtoBookmarks(url1, null);
        bookmarks.addURLtoBookmarks(url2, null);

        String actual1 = bookmarks.getDomainOfURL(url1);
        String actual2 = bookmarks.getDomainOfURL(url2);


        System.out.println(actual1);
        System.out.println(actual2);

        assertEquals("google.com", actual1);
        assertEquals("google.com", actual2);

    }

     */
}