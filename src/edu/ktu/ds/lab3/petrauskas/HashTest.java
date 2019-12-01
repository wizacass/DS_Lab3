package edu.ktu.ds.lab3.petrauskas;

import edu.ktu.ds.lab3.utils.HashMap;
import edu.ktu.ds.lab3.utils.Ks;

import java.util.Random;
import java.util.Set;

public class HashTest
{
    private HashMap<String, String> hashMap;
    private HashMap<String, String> otherMap;
    private Random rnd;

    public HashTest()
    {
        hashMap = new HashMap<>();
        otherMap = new HashMap<>();

        rnd = new Random();
        rnd.setSeed(2017);
    }

    public void run()
    {
        Ks.oun("Hash Table test starting...");
        initialize();
        initializeSecond();

        Ks.oun("Number of empties: " + hashMap.numberOfEmpties());

        var keys = hashMap.keySet();
        printKeys(keys);

        hashMap.putAll(otherMap);
        print("Map after merging:", hashMap);
    }

    private void initialize()
    {
        hashMap.put("Petrauskas", "Vytenis");
        hashMap.put("Pilinkus", "Martynas");
        hashMap.put("Blockis", "Martynas");
        hashMap.putIfAbsent("Blockis", "Laurynas");

        print("Initial elements:", hashMap);
    }

    private void initializeSecond()
    {
        otherMap.put("Petrauskas", "Jonas");
        otherMap.put("Jusas", "Vacius");

        print("Initial elements:", otherMap);
    }

    private void print(String header, HashMap<String, String> map)
    {
        Ks.oun(header);
        System.out.println(map.toString());
    }

    private void printKeys(Set<String> keys)
    {
        Ks.oun("Keys:");
        for (var key :keys)
        {
            System.out.println(key.toString());
        }
    }

    public static void main(String... args)
    {
        new HashTest().run();
        Ks.oun("Hash Table test complete!");
    }
}
