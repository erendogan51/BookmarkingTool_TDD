package pt.ipp.isep.dei.examples.tdd.basic.domain;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookmarkTest {

    @Test
    public void checkIfCompareToReturnZeroWhenDateTimeIsNotPresent() throws MalformedURLException {
        //given
        Bookmark bookmark1 = new Bookmark(
                new URL("https://google.com"),
                "tag1",
                0,
                new HashSet<String>(),
                null);

        Bookmark bookmark2 = new Bookmark(
                new URL("https://google.com"),
                "tag1",
                0,
                new HashSet<String>(),
                null);

        //when
        int actual = bookmark1.compareTo(bookmark2);

        //then
        assertEquals(0, actual);

    }

}