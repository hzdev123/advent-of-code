package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.math.BigInteger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    public static BigInteger count(String filePath, int days) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            TreeMap<Integer, BigInteger> fishes = null;
            while ((line = br.readLine()) != null) {
//                System.out.println("LINE: " + line);
                fishes = loadFishesMap(line);
            }
            for (int i = 0; i < days; i++) {
//                System.out.println("Day[" + i + "] ");
//              System.out.println("Day[" + i + "]: " + Arrays.toString(fishes.toArray()));
                reproduceMap(fishes);
            }
            return countFishMap(fishes);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BigInteger.valueOf(-1);
    }

    private static BigInteger countFishMap(TreeMap<Integer, BigInteger> fishes) {
        BigInteger sum = BigInteger.valueOf(0);
        for (int fishCategory : fishes.keySet()) {
            sum = sum.add(fishes.get(fishCategory));
        }
        return sum;
    }

    private static ArrayList<Integer> getFishCategories(TreeMap<Integer, BigInteger> fishes) {
        ArrayList<Integer> fishCategoriesList = new ArrayList<Integer>();
        fishCategoriesList.addAll(fishes.keySet());
        return fishCategoriesList;
    }

    private static void reproduceMap(TreeMap<Integer, BigInteger> fishes) {
        BigInteger categoryZeroNbrOfFishes = fishes.get(0);
        for (int fishCategory : getFishCategories(fishes)) {
//            System.out.println("    " + fishCategory + " -> " + fishes.get(fishCategory));
            if (fishCategory == 8) {
                BigInteger categorySixNbrOfFishes = fishes.get(6);
                fishes.put(6, categorySixNbrOfFishes.add(categoryZeroNbrOfFishes));
                fishes.put(8, categoryZeroNbrOfFishes);
            } else {
                fishes.put(fishCategory, fishes.get(fishCategory + 1));
            }
        }
    }

    private static void reproduceList(ArrayList<Integer> fishes) {
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

    private static TreeMap<Integer, BigInteger> loadFishesMap(String line) {
        TreeMap<Integer, BigInteger> fishes = new TreeMap<Integer, BigInteger>();
        for (int i = 0; i < 9; i++) {
            fishes.put(i, BigInteger.valueOf(0));
        }
        String[] fishTimers = line.split(",");
        for (int i = 0; i < fishTimers.length; i++) {
            int fishTimer = Integer.parseInt(fishTimers[i]);
            BigInteger val = fishes.get(fishTimer);
            if (val.equals(BigInteger.valueOf(0))) {
                fishes.put(fishTimer, BigInteger.valueOf(1));
            } else {
                fishes.put(fishTimer, val.add(BigInteger.valueOf(1)));
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
