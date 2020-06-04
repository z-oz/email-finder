package edu.depaul.email;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PageParserTest {
    @Test
    @DisplayName("Document without <a>")
    public void documentTest() {
        String html = "<html><title>E-Mail Finder Project</title></html>";
        Document doc = Jsoup.parse(html);
        assertEquals("E-Mail Finder Project", doc.title());
    }

    @Test
    @DisplayName("Document with one <a>")
    public void documentTest2() {
        String html = "<html><a href=\"https://www.w3schools.com\">Visit W3Schools.com!</a></html>";
        Document doc = Jsoup.parse(html);
        Elements element = doc.select("a");
        assertEquals("https://www.w3schools.com", element.attr("href"));
    }

    @Test
    @DisplayName("Document with multiple <a>")
    public void documentTest3() {
        String html = "<html><a href=\"https://www.w3schools.com\">Visit W3Schools.com!</a>" +
                            "<a href=\"https://www.wikipedia.org\"Wikipedia!</a>" +
                            "<a href=\"https://cdm.depaul.edu\"!</a>" +
                            "<a href=\"https://www.github.com\"!</a>" +
                        "</html>";
        Document doc = Jsoup.parse(html);
        Elements element = doc.select("a");
        for (int i = 0; i < element.size(); i++) {
            assertTrue(doc.html().toString().contains(element.eachAttr("href").get(i)));
        }
    }

}
