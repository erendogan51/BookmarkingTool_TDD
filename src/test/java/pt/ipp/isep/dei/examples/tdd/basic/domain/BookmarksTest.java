package pt.ipp.isep.dei.examples.tdd.basic.domain;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


class BookmarksTest {

    @Test
    public void checkIfURLCanBeBookmarked() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
        String url = "https://google.com";

        //when
        bookmarks.addURLtoBookmarks(url, null);

        //then
        assertEquals(url, bookmarks.getBookmarkedURLs().get(0).getUrl().toString());
    }

    @Test
    public void checkIfExceptionIsThrownwhenInvalidURLisEntered() {
        //given
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
        String url = "google.com";

        //then
        assertThrows(MalformedURLException.class, () -> {
            //when
            bookmarks.addURLtoBookmarks(url, null);
        });
    }

    @Test
    public void checkIfBookmarkIsNotAddedWhenItExists() throws MalformedURLException {
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
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
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
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
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
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
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
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
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
        String url = "https://google.com";

        //when
        bookmarks.addURLtoBookmarks(url, null);

        //then
        assertNull(bookmarks.findBookmarkByURL(""));

    }


    @Test
    public void checkIfRatingIsIncreasedWhenDuplicateURLisBookmarked() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
        String url = "https://google.com";

        //when
        bookmarks.addURLtoBookmarks(url, null);

        //then
        assertEquals(0, bookmarks.getBookmarkedURLs().get(0).getRating());

    }

    @Test
    public void checkIfRatingIsNotIncreasedWhenDuplicateURLisNotPresent() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
        String url = "https://google.com";

        //when
        //bookmarks.addURLtoBookmarks(url);
        bookmarks.increaseRatingForExistingBookmark(null);

        //then
        assertTrue(bookmarks.getBookmarkedURLs().isEmpty());

    }


    @Test
    public void checkIfRatingIsIncreasedWhileAddingANewBookmarkThatExists() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
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
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
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
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
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
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
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
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
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
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
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
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
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
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
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
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
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
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
        String url1 = "https://google.com";

        //when
        bookmarks.addURLtoBookmarks(url1, "tag1");

        //then
        assertNotNull(bookmarks.getBookmarkedURLs().get(0).getLocalDateTime());
    }

    @Test
    public void checkIfBookmarksCanBeSortedByRating() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
        String url1 = "https://google.com";
        String url2 = "https://mail.google.com";
        String url3 = "https://notes.google.com";

        //when
        bookmarks.addURLtoBookmarks(url1, "tag1");
        bookmarks.addURLtoBookmarks(url1, "tag1");
        bookmarks.addURLtoBookmarks(url1, "tag1");

        bookmarks.addURLtoBookmarks(url3, "tag1");

        bookmarks.addURLtoBookmarks(url2, "tag1");
        bookmarks.addURLtoBookmarks(url2, "tag1");


        bookmarks.sortBookmarksByRatingDesc();


        //then
        assertEquals(2, bookmarks.getBookmarkedURLs().get(0).getRating());
        assertEquals(1, bookmarks.getBookmarkedURLs().get(1).getRating());
        assertEquals(0, bookmarks.getBookmarkedURLs().get(2).getRating());
    }

    @Test
    public void checkIfBookmarksCanBeSortedByDateTime() throws MalformedURLException {
        //given
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
        String url1 = "https://google.com";
        String url2 = "https://mail.google.com";
        String url3 = "https://notes.google.com";

        //when
        bookmarks.addURLtoBookmarks(url1, "tag1");
        bookmarks.addURLtoBookmarks(url2, "tag1");
        bookmarks.addURLtoBookmarks(url3, "tag1");

        //when
        bookmarks.getBookmarkedURLs()
                .get(0)
                .setLocalDateTime(
                        bookmarks
                                .getBookmarkedURLs()
                                .get(0)
                                .getLocalDateTime().plusMinutes(10)
                );

        bookmarks.getBookmarkedURLs()
                .get(1)
                .setLocalDateTime(
                        bookmarks
                                .getBookmarkedURLs()
                                .get(1)
                                .getLocalDateTime().plusMinutes(4)
                );

        for (Bookmark bookmark : bookmarks.getBookmarkedURLs()) {
            System.out.println(bookmark.getLocalDateTime());
        }

        bookmarks.sortBookmarksByDatetime();

        for (Bookmark bookmark : bookmarks.getBookmarkedURLs()) {
            System.out.println(bookmark.getLocalDateTime());
        }


        //then
        assertTrue(bookmarks.getBookmarkedURLs().get(0).getLocalDateTime().isBefore(
                bookmarks.getBookmarkedURLs().get(1).getLocalDateTime()
        ));
        assertTrue(bookmarks.getBookmarkedURLs().get(1).getLocalDateTime().isBefore(
                bookmarks.getBookmarkedURLs().get(2).getLocalDateTime()
        ));

    }


    @Test
    public void checkIfBookmarksAreAddedToTheCorrectUser() throws MalformedURLException {
        //given
        Bookmarks bookmarks1 = new Bookmarks("user1", new ArrayList<>());
        Bookmarks bookmarks2 = new Bookmarks("user2", new ArrayList<>());
        String url1 = "https://google.com";
        String url2 = "https://mail.google.com";
        String url3 = "https://notes.google.com";

        //when
        bookmarks1.addURLtoBookmarks(url1, "tag1");
        bookmarks1.addURLtoBookmarks(url3, "tag1");
        bookmarks1.addURLtoBookmarks(url2, "tag1");


        //then
        assertNotEquals(bookmarks1, bookmarks2);
    }


    @Test
    public void checkIfExceptionIsThrownWhenIllegalPathIsGiven() throws MalformedURLException {
        //given
        Bookmarks bookmarks1 = new Bookmarks("user1", new ArrayList<>());
        String url1 = "https://google.com";
        String url2 = "https://mail.google.com";
        String url3 = "https://notes.google.com";

        //when
        bookmarks1.addURLtoBookmarks(url1, "tag1");
        bookmarks1.addURLtoBookmarks(url3, "tag1");
        bookmarks1.addURLtoBookmarks(url2, "tag1");


        //then
        assertThrows(NullPointerException.class, () -> {
            bookmarks1.backupToJSON(null);
        });
    }

    @Test
    public void checkIfExceptionIsThrownWhenNoOwnerIsSpecified() {

        //then
        assertThrows(IllegalArgumentException.class, () -> {
            new Bookmarks(null, new ArrayList<>());
        });
    }

    @Test
    public void checkIfBookmarksCanBeBackupedToAFile() throws IOException {
        //given
        String path = "bookmark_backups.json";
        Bookmarks bookmarks1 = new Bookmarks("user1", new ArrayList<>());
        BufferedReader br;
        String url1 = "https://google.com";
        String url2 = "https://mail.google.com";
        String url3 = "https://notes.google.com";


        //when
        bookmarks1.addURLtoBookmarks(url1, "tag1");
        bookmarks1.addURLtoBookmarks(url2, "tag1");
        bookmarks1.addURLtoBookmarks(url3, "tag1");
        bookmarks1.backupToJSON("bookmark_backups.json");
        bookmarks1.removeURLfromBookmarks(url1);
        bookmarks1.removeURLfromBookmarks(url2);
        bookmarks1.removeURLfromBookmarks(url3);

        bookmarks1.getBookmarkedURLs().forEach(System.out::println);

        assertTrue(bookmarks1.getBookmarkedURLs().isEmpty());

        bookmarks1.restoreBookmarksFromFile(path);

        bookmarks1.getBookmarkedURLs().forEach(System.out::println);

        //then
        assertFalse(bookmarks1.getBookmarkedURLs().isEmpty());
    }

    @Disabled
    @Test
    public void checkIfBackupCanBeReadToBookmarks() throws IOException {
        //given
        String path = "bookmark_backups.json";
        Bookmarks bookmarks1 = new Bookmarks("user1", new ArrayList<>());
        BufferedReader br = new BufferedReader(new FileReader(path));
        String url1 = "https://google.com";
        String line = null;
        StringBuilder stringBuffer = new StringBuilder();


        //when
        bookmarks1.addURLtoBookmarks(url1, "tag1");
        //bookmarks1.restoreBookmarksFromFile("bookmark_backups.json");

        while ((line = br.readLine()) != null) {
            stringBuffer.append(line);
        }

        //then
        assertEquals(url1, stringBuffer.toString());
    }



    /*
    @Disabled
    @Test
    public void checkIfAddedBookmarkIsBeingAssociatedWithOtherBookmarksFromSameDomain() throws IOException, URISyntaxException {
        //given
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
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
        Bookmarks bookmarks = new Bookmarks("user1", new ArrayList<>());
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