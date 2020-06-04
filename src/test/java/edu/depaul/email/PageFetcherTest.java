package edu.depaul.email;

import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PageFetcherTest {
    private PageFetcher pagefetcher;
    String goodURL = "https://cdm.depaul.edu";
    String badURL = "file://cdm.depaul.edu";
    String invalidURL = "abc";

    @BeforeEach
    public void setup() {
        pagefetcher = new PageFetcher();
    }

    @Test
    @DisplayName("Recognizes good URL")
    public void goodURLTest() {
        try {
            Document document = pagefetcher.get(goodURL);
            assertNotNull(document);
        }
        catch (EmailFinderException e) {
            assertThrows(EmailFinderException.class, () -> pagefetcher.get(goodURL));
        }
    }

    @Test
    @DisplayName("Recognizes good URL with getString")
    public void goodURL2Test() {
        try {
            String str = pagefetcher.getString(goodURL);
            assertNotNull(str);
        }
        catch (EmailFinderException e) {
            assertThrows(EmailFinderException.class, () -> pagefetcher.getString(goodURL));
        }
    }

    @Test
    @DisplayName("Recognizes bad URL")
    public void badURLTest() {
        try {
            Document document = pagefetcher.get(badURL);
            fail();
        }
        catch (EmailFinderException e) {
            assertThrows(EmailFinderException.class, () -> pagefetcher.get(badURL));
        }
    }

    @Test
    @DisplayName("Recognizes bad URL with getString")
    public void badURL2Test() {
        try {
            String str = pagefetcher.getString(badURL);
            fail();
        }
        catch (EmailFinderException e) {
            assertThrows(EmailFinderException.class, () -> pagefetcher.getString(badURL));
        }
    }

    @Test
    @DisplayName("Recognizes invalid URL")
    public void illegalExceptionTest() {
        try {
            Document document = pagefetcher.get(invalidURL);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertThrows(EmailFinderException.class, () -> pagefetcher.get(invalidURL));
        }
    }

    @Test
    @DisplayName("Recognizes invalid URL with getString")
    public void illegalException2Test() {
        try {
            String str = pagefetcher.getString(invalidURL);
            fail();
        }
        catch (IllegalArgumentException e) {
            assertThrows(EmailFinderException.class, () -> pagefetcher.getString(invalidURL));
        }
    }

}
