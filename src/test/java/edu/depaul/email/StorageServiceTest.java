package edu.depaul.email;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageServiceTest {
    @Test
    @DisplayName("Test Storage Service")
    public void storageServiceTest() throws IOException {
        StorageService storageservice = new StorageService();
        storageservice.addLocation(StorageService.StorageType.GOODLINKS, "good-links.txt");
        List<String> URLs = Arrays.asList("https://www.ebay.com",
                                          "https://www.amazon.com",
                                          "https://www.cdm.depaul.edu",
                                          "https://www.harpercollege.edu");

        storageservice.storeList(StorageService.StorageType.GOODLINKS, URLs);
        BufferedReader br = new BufferedReader(new FileReader("good-links.txt"));
        while (br.readLine() != null) {
            assertTrue(URLs.contains(br.readLine()));
        }
    }

}
