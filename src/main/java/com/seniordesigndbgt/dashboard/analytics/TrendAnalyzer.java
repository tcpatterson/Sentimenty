package com.seniordesigndbgt.dashboard.analytics;

import com.seniordesigndbgt.dashboard.model.Press;

import java.text.BreakIterator;
import java.util.*;

public class TrendAnalyzer {

    private String algorithm = "x mentions in y time, iff x >= threshold z";
    private Map<String, Integer> longFrequencyMap;
    private Map<String, Integer> shortFrequencyMap;
    private List<Map.Entry<String, Integer>> topThree;
    private List<Map.Entry<String, Integer>> newTrends;
    private static final int THRESHOLD = 4;

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
        //Only doing short frequency map for now
        longFrequencyMap = sortFrequencyMapByValue(longFrequencyMap);
        shortFrequencyMap = sortFrequencyMapByValue(shortFrequencyMap);
//        printFrequencyMap(shortFrequencyMap);
        topThree = get3MaxValues(shortFrequencyMap);
        newTrends = new LinkedList<Map.Entry<String, Integer>>();
        for (int i = 0; i < topThree.size(); i++){
            if (topThree.get(i).getValue() >= THRESHOLD)
                newTrends.add(topThree.get(i));
        }

//        for (int i = 0; i < newTrends.size(); i++){
//            System.out.println(newTrends.get(i).getKey() + "=" + newTrends.get(i).getValue());
//        }

    }

    

    public void refreshLongMap(){
        longFrequencyMap = new LinkedHashMap<String, Integer>();
    }

    public void refreshShortMap(){
        shortFrequencyMap = new LinkedHashMap<String, Integer>();
    }

    public Map<String, Integer> getLongFrequencyMap() {
        return longFrequencyMap;
    }

    public Map<String, Integer> getShortFrequencyMap() {
        return shortFrequencyMap;
    }

    public static <K, V extends Comparable<? super V>> List<Map.Entry<K,V>> get3MaxValues(Map<K,V> map){
        List <Map.Entry<K,V>>  topThree = new LinkedList<Map.Entry<K, V>>();
        Iterator it = map.entrySet().iterator();
        for (int i = 0; i < 3; i++) {
            topThree.add((Map.Entry)it.next());
        }
        return topThree;
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

    private static <K, V extends Comparable<? super V>> Map<K, V> sortFrequencyMapByValue(Map<K,V> map) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    private void printFrequencyMap(Map<String,Integer> map){
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

}
