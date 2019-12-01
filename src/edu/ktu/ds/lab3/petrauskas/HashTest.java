package edu.ktu.ds.lab3.petrauskas;

import edu.ktu.ds.lab3.utils.HashMap;
import edu.ktu.ds.lab3.utils.Ks;

import java.util.Random;
import java.util.Set;

public class HashTest
{
    private HashMap<String, String> hashMap;
    private Random rnd;

    public HashTest()
    {
        hashMap = new HashMap<>();

        rnd = new Random();
        rnd.setSeed(2017);
    }

    public void run()
    {
        Ks.oun("Hash Table test starting...");
        initialize();

        Ks.oun("Number of empties: " + hashMap.numberOfEmpties());

        var keys = hashMap.keySet();
        printKeys(keys);
    }

    private void initialize()
    {
        hashMap.put("Petrauskas", "Vytenis");
        hashMap.put("Pilinkus", "Martynas");
        hashMap.put("Blockis", "Martynas");
        hashMap.putIfAbsent("Blockis", "Laurynas");

        print("Initial elements:");
    }

    private void print(String header)
    {
        Ks.oun(header);
        System.out.println(hashMap.toString());
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
