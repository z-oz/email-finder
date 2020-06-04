package edu.depaul.email;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailFinderTest {
    private EmailFinder finder;

    @BeforeEach
    public void setup() {
        finder = new EmailFinder();
    }

    @Test
    @DisplayName("Verify output files")
    public void outputTest() {
        String[] args = {"file://"};
        finder.run(args);
        File emailFile = new File("email.txt");
        assertTrue(emailFile.exists());
        File goodlinksFile = new File("good-links.txt");
        assertTrue(goodlinksFile.exists());
        File badlinksFile = new File("badlinks.txt");
        assertTrue(badlinksFile.exists());
    }

    @Test
    @DisplayName("Should recognize a missing argument")
    public void missingArgumentTest() {
        String[] args = {};
        assertThrows(IllegalArgumentException.class, () -> finder.run(args));
    }

    @Test
    @DisplayName("Should recognize the max email limit")
    public void limitTest() {
        String[] args = {"https://cdm.depaul.edu", "2", "6", "11"};
        File emailFile = new File("email.txt");
        finder.main(args);
        assertTrue(Integer.parseInt(args[1]) >= emailFile.length());
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/goodWebsites.csv", numLinesToSkip = 1)
    @DisplayName("Test URLs in file")
    public void runFromFileTest(String URL) {
        String[] args = {"temp"};
        args[0] = URL;
        File goodlinksFile = new File("good-links.txt");
        finder.main(args);
        assertTrue(1 <= goodlinksFile.length()); // provided link is already good
    }

}
