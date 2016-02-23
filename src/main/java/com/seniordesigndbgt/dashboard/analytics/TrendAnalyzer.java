package com.seniordesigndbgt.dashboard.analytics;

import com.seniordesigndbgt.dashboard.model.Press;

import java.text.BreakIterator;
import java.util.*;

public class TrendAnalyzer {

    private String algorithm = "x mentions in y time";
    private Map<String, Integer> longFrequencyMap;
    private Map<String, Integer> shortFrequencyMap;

    public TrendAnalyzer() {}

    private static TrendAnalyzer singleton;

    public static TrendAnalyzer getInstance(){
        if (singleton == null)
            singleton = new TrendAnalyzer();
        return singleton;
    }
    //TODO Need to separate already analyzed from text that needs analysis
    public void analyzeCurrentTrends() {
        //TODO
    }

    public void findNewTrends() {
        //TODO
        //Sort the new hashMap
        //Check for threshold
        //
    }

    public void refreshLongMap(){
        longFrequencyMap = new LinkedHashMap<String, Integer>();
    }

    public void refreshShortMap(){
        shortFrequencyMap = new LinkedHashMap<String, Integer>();
    }

    public void updateFrequencyMap(String text){
        //Sanitize input
        String removeCharacters = ".,!?"; //Remove these characters from the text
        for (int i = 0; i < removeCharacters.length(); i++){
            text = text.replace(String.valueOf(removeCharacters.charAt(i)), "");
        }

        String[] splitArray = text.split(" ");


        for (String currWord : splitArray) {
//            System.out.println(currWord);
            if (longFrequencyMap.containsKey(currWord)) {
                int currCount = longFrequencyMap.get(currWord);
                longFrequencyMap.put(currWord, currCount + 1);
            } else {
                longFrequencyMap.put(currWord, 1);
            }

            if (shortFrequencyMap.containsKey(currWord)) {
                int currCount = shortFrequencyMap.get(currWord);
                shortFrequencyMap.put(currWord, currCount + 1);
            } else {
                shortFrequencyMap.put(currWord, 1);
            }
        }
    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortFrequencyMapByValue(Map<K,V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

}
