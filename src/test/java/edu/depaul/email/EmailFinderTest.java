package edu.depaul.email;

import edu.depaul.email.EmailFinder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmailFinderTest {
    @Test
    @DisplayName("Verify URL")
    void testURL() {
        EmailFinder finder = new EmailFinder();
        String[] str = {"file://"};
        finder.run(str);
    }
}
