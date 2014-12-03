package com.sankar.rdp.RDP190Intermediate;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class WordScanner {
    
    private Set<String> words = new HashSet<>();
    
    public void accept(String word) {
        words.add(word);
    }
    
    public void acceptAll(Iterator<String> words) {
        while(words.hasNext()) 
            accept(words.next());
    }
    
    public int highestPartCount() {
        int highestPartCount = 0;
        
        for(String word : words) {
            int partCount = 0;
            
            for(String part : scanParts(word))
                if (words.contains(part))
                    partCount += 1;
            
            if (partCount > highestPartCount) {
                highestPartCount = partCount;
            }
        }
        
        return highestPartCount;
    }
    
    public String highestPartCountWord() {
        int highestPartCount = 0;
        String highestPartCountWord = null;
        
        for(String word : words) {
            int partCount = 0;
            
            for(String part : scanParts(word))
                if (words.contains(part))
                    partCount += 1;
            
            if (partCount > highestPartCount) {
                highestPartCount = partCount;
                highestPartCountWord = word;
            }
        }
        
        return highestPartCountWord;
    }
    
    static Set<String> scanParts(String word) {
        Set<String> parts = new HashSet<>();
        
        for(int targetLength = 2; targetLength <= word.length(); targetLength++)
            for(int start_indx = 0; start_indx + targetLength <= word.length(); start_indx++)
                parts.add(word.substring(start_indx, start_indx + targetLength));
        
        return parts;
    }

}
