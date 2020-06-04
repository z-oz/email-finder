package edu.depaul.email;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ListWriterTest {
    @Test
    @DisplayName("Verifies expected output result")
    public void writeListTest() throws IOException {
        OutputStream stream = new FileOutputStream("writeListTestOut.txt");
        ListWriter listwriter = new ListWriter(stream);
        Collection<String> collection = new ArrayList<>();
        String URL = "https://www.amazon.com";
        String URL2 = "https://www.ebay.com";
        collection.add(URL);
        collection.add(URL2);
        listwriter.writeList(collection);
        Set<String> lines = new HashSet<>();
        BufferedReader br = new BufferedReader(new FileReader("writeListTestOut.txt"));
        String line = br.readLine();
        while (line != null) {
            lines.add(line);
            line = br.readLine();
        }
        assertTrue(lines.contains(URL) && lines.contains(URL2));
    }

}
