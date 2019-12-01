package edu.ktu.ds.lab3.petrauskas;

import edu.ktu.ds.lab3.demo.Timekeeper;
import edu.ktu.ds.lab3.gui.ValidationException;
import edu.ktu.ds.lab3.utils.HashMap;
import edu.ktu.ds.lab3.utils.Ks;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Benchmark
{
    private HashMap<String, String> ktuMap;
    private java.util.HashMap<String, String> javaMap;

    private final Timekeeper timeKeeper;
    private final int[] COUNTS = {100, 500, 1000, 2000};

    private Random rnd;

    public Benchmark()
    {
        ktuMap = new HashMap<>();
        javaMap = new java.util.HashMap<>();

        timeKeeper = new Timekeeper(COUNTS);

        rnd = new Random();
        rnd.setSeed(2019);
    }

    private List<String> readFile()
    {
        List<String> lines = Collections.emptyList();
        try
        {
            lines = Files.readAllLines(Paths.get("data/zodynas.txt"), StandardCharsets.UTF_8);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return lines;
    }

    private void initializeMaps(List<String> text)
    {
        ktuMap = new HashMap<>();
        javaMap = new java.util.HashMap<>();
        for (var word: text)
        {
            ktuMap.put(word, word);
            javaMap.put(word, word);
        }
    }

    private List<String> initializeRandom(List<String> full, int count)
    {
        Collections.shuffle(full);
        return full.subList(0, count);
    }

    private void benchmark() throws InterruptedException
    {
        try
        {
            for (var count : COUNTS)
            {
                var words = readFile();
                initializeMaps(words);
                var randomWords = initializeRandom(words, count);
                timeKeeper.startAfterPause();

                timeKeeper.start();

                for (var word: randomWords)
                {
                    ktuMap.containsValue(word);
                }
                timeKeeper.finish("contKtu");

                for (var word: randomWords)
                {
                    javaMap.containsValue(word);
                }
                timeKeeper.finish("contJava");

                for (var word: randomWords)
                {
                    ktuMap.remove(word);
                }
                timeKeeper.finish("remKtu");

                for (var word: randomWords)
                {
                    ktuMap.remove(word);
                }
                timeKeeper.finish("remJava");

                timeKeeper.seriesFinish();
            }
            timeKeeper.logResult("");
        }
        catch (ValidationException e)
        {
            timeKeeper.logResult(e.getMessage());
        }
    }

    private void run()
    {
        try
        {
            benchmark();
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        catch (Exception ex)
        {
            ex.printStackTrace(System.out);
        }
    }

    public static void main(String... args)
    {
        Ks.oun("Benchmark starts!");
        new Benchmark().run();
        Ks.oun("Benchmark ends!");
    }
}
