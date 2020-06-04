package edu.depaul.email;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static edu.depaul.email.StorageService.StorageType.GOODLINKS;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class StorageServiceTest {
    @Test
    @DisplayName("Test Storage Service")
    public void storageServiceTest() throws IOException {
        StorageService storageservice = new StorageService();
        storageservice.addLocation(GOODLINKS, "good-links.txt");
        List<String> URLs = Arrays.asList("https://www.ebay.com",
                                          "https://www.amazon.com",
                                          "https://www.cdm.depaul.edu",
                                          "https://www.harpercollege.edu");

        storageservice.storeList(GOODLINKS, URLs);
        BufferedReader br = new BufferedReader(new FileReader("good-links.txt"));
        while (br.readLine() != null) {
            assertTrue(URLs.contains(br.readLine()));
        }
    }

    @Test
    @DisplayName("Test bad path")
    public void storageServiceBadPathTest() throws IOException {
        StorageService storageservice = new StorageService();
        storageservice.addLocation(GOODLINKS, "/non-existent-path");
        List<String> URLs = Arrays.asList("https://www.ebay.com",
                                          "https://www.amazon.com",
                                          "https://www.cdm.depaul.edu",
                                          "https://www.harpercollege.edu");
        try {
            storageservice.storeList(GOODLINKS, URLs);
        } catch (Exception e) {
            assertThrows(EmailFinderException.class, () -> storageservice.storeList(GOODLINKS, URLs));
        }
    }

}
