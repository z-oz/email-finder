package edu.depaul.email;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class PageCrawlerTest {
    private StorageService storageservice;
    private PageCrawler pagecrawler;
    String goodURL = "https://cdm.depaul.edu";
    String badURL = "abc";

    @BeforeEach
    public void setup() {
        storageservice = mock(StorageService.class);
        pagecrawler = new PageCrawler(storageservice);
    }

    @Test
    @DisplayName("Recognize bad constructor")
    public void badConstructorTest() {
        try {
            new PageCrawler(storageservice, -2);
            fail();
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    @DisplayName("Avoids endless loops")
    public void infiniteLoopTest() {
        pagecrawler.crawl(goodURL);
        assertTimeout(ofSeconds(5), () -> {
        });
    }

    @Test
    @DisplayName("Should recognize when target size is met")
    public void targetSizeTest() {
        StorageService storageservice = mock(StorageService.class);
        PageCrawler pagecrawler2 = new PageCrawler(storageservice, 2);
        pagecrawler2.crawl(goodURL);
        assertEquals(2, pagecrawler2.getEmails().size());
    }

    @Test
    @DisplayName("Should recognize when max size is met")
    public void maxSizeTest() {
        pagecrawler.crawl(goodURL);
        assertTrue(50 >= pagecrawler.getEmails().size());
    }

    @Test
    @DisplayName("Test getGoodLink")
    public void getGoodLinksTest() {
        pagecrawler.crawl(goodURL);
        assertTrue(pagecrawler.getGoodLinks().contains(goodURL));
    }

    @Test
    @DisplayName("Test getBadLink")
    public void getBadLinksTest() {
        pagecrawler.crawl(badURL);
        assertTrue(pagecrawler.getBadLinks().contains(badURL));
    }

}
