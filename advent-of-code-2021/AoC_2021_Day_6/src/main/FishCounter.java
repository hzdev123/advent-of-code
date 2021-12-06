package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import java.lang.StringBuilder;

/**
 * Represents a fish counter
 * @author Hoa Truong
 *
 */
public class FishCounter {

    /**
     * Counts the points along the lines
     * @param filePath Path to input data file.
     * @param days  number of simulation days 
     * @return int number of fishes at the input day 
     */
    public static int count(String filePath, int days) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            ArrayList<Integer> fishes = null;
            while ((line = br.readLine()) != null) {
//                System.out.println("LINE: " + line);
                fishes = loadFishesList(line);
            }
            //TODO: rewrite using map where key is fishCategory 0-8 and value is the number of fish in each category
            for (int i = 0; i < days; i++) {
                System.out.println("Day[" + i + "] ");
//              System.out.println("Day[" + i + "]: " + Arrays.toString(fishes.toArray()));
                loopingCount(fishes);
            }
            return fishes.size();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static void loopingCount(ArrayList<Integer> fishes) {
        for (int fishIdx = 0; fishIdx < fishes.size(); fishIdx++) {
            int fishTimer = fishes.get(fishIdx);
            if (fishTimer == 0) {
                fishes.set(fishIdx, 6);
                fishes.add(9);
            } else {
                fishes.set(fishIdx, fishTimer - 1);
            }
        }
    }

    private static HashMap<Integer, Integer> loadFishesMap(String line) {
        HashMap<Integer, Integer> fishes = new HashMap<Integer, Integer>();
        String[] fishTimers = line.split(",");
        for (int i = 0; i < fishTimers.length; i++) {
            int fishTimer = Integer.parseInt(fishTimers[i]);
            Object val = fishes.get(fishTimer);
            if (val == null) {
                fishes.put(fishTimer, 1);
            } else {
                int convVal = (Integer) val + 1;
                fishes.put(fishTimer, convVal);
            }
        }
        return fishes;
    }

    private static ArrayList<Integer> loadFishesList(String line) {
        ArrayList<Integer> fishes = new ArrayList<Integer>();
        String[] fishTimers = line.split(",");
        for (int i = 0; i < fishTimers.length; i++) {
            fishes.add(Integer.parseInt(fishTimers[i]));
        }
        return fishes;
    }
}
