package JavaCollections;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LearnMap {
    public static void main(String[] args) {
//        HashMap all operations are O(1)
//        TreeMap all operation are O(logN)

        Map<String,Integer> mp = new HashMap<>();                       // random order
//        Map<String,Integer> mp = new TreeMap<>();                   // stores the keys in sorted order

        mp.put("One",1);
        mp.put("Two",2);
        mp.put("Three",3);

        mp.put("Two",23);            // mp.put() overwrites the prev key value

        if(!mp.containsKey("Two")){
            mp.put("Two",2);
        }

        mp.putIfAbsent("Two",5);      // mp.putIfAbsent() does not overwrite the prev value

        System.out.println(mp);

//        Iterate over map

        System.out.println(mp.entrySet());          // returns the set of all the entries in your maps
        System.out.println(mp.keySet());            // returns the set of all keys in the map
        System.out.println(mp.values());            // returns the list of all values in your map

        System.out.println(mp.containsKey("Two"));      // checks if this key is present
        System.out.println(mp.containsValue(3));       // checks if this value is present


//        A map entry (key-value pair).
//        here Entry represent an entry of the entry-set view of a map.
        for(Map.Entry<String, Integer> e: mp.entrySet()){       // iterate over each entry and print it
            System.out.println(e);
            System.out.println(e.getKey());
            System.out.println(e.getValue());
        }

//        iterating over keys
        for(String key: mp.keySet()){
            System.out.print(key + " ");
        }
        System.out.println();

        System.out.println(mp.remove("Four"));              // remove the key value pair(removes the key value pair if it exists, or does nothing)

        System.out.println(mp);

        System.out.println(mp.isEmpty());               // check if map is empty

        mp.clear();

    }
}
