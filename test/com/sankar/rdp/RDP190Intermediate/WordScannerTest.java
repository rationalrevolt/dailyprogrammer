package com.sankar.rdp.RDP190Intermediate;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

import org.junit.Test;

public class WordScannerTest {
    
    @Test public void
    breaks_words_into_parts_correctly1() {
        String word = "abcd"; // ab bc cd abc bcd abcd
        
        Set<String> parts = WordScanner.scanParts(word);
        
        assertEquals(6, parts.size());
    }
    
    @Test public void
    breaks_words_into_parts_correctly2() {
        String word = "aaaa"; // aa aaa aaaa
        
        Set<String> parts = WordScanner.scanParts(word);
        
        assertEquals(3, parts.size());
    }
    
    @Test public void
    finds_highest_part_count_word_correctly() {
        WordScanner scanner = new WordScanner();
        
        for(String word : new String[]{"aa", "aabb", "ab", "abb", "bb", "bbc", "bbab"})
            scanner.accept(word);
        
        assertEquals("aabb", scanner.highestPartCountWord());
        assertEquals(5, scanner.highestPartCount());
    }
    
    @Test public void
    finds_highest_part_count_word_correctly2() throws IOException {
        WordScanner scanner = new WordScanner();
        
        try(BufferedReader r = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("rdp190intermediate.txt")))) {
            String word;
            while((word = r.readLine()) != null)
                scanner.accept(word);
        }
        
        assertEquals("ethylenediaminetetraacetates", scanner.highestPartCountWord());
        assertEquals(36, scanner.highestPartCount());
    }

}
