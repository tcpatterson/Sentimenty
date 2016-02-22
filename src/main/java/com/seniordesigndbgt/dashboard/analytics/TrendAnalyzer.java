package com.seniordesigndbgt.dashboard.analytics;

import com.seniordesigndbgt.dashboard.model.Press;

import java.text.BreakIterator;
import java.util.HashMap;
import java.util.Map;

public class TrendAnalyzer {

    public String algorithm = "x mentions in y time";
    public TrendAnalyzer() {}

    public void analyzeCurrentTrends() {
        //TODO
    }

    public void findNewTrends() {
        //TODO
    }

    public Map<String, Integer> getWordMap(String text){
        Map<String, Integer> hashMap = new HashMap<String, Integer>();
        String removeCharacters = ".,!?"; //Remove these characters from the text
        for (int i = 0; i < removeCharacters.length(); i++){
            text = text.replace(String.valueOf(removeCharacters.charAt(i)), "");
        }

        String[] splitArray = text.split(" ");


        for (String currWord : splitArray) {
//            System.out.println(currWord);
            if (hashMap.containsKey(currWord)) {
                int currCount = hashMap.get(currWord);
                hashMap.put(currWord, currCount + 1);
            }
            else {
                hashMap.put(currWord, 1);
            }
        }
        return hashMap;
    }

}
