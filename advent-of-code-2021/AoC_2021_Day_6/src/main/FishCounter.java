package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
                System.out.println("LINE: " + line);
                fishes = loadFishes(line);
            }
            for (int i = 0; i < days; i++) {
                System.out.println("Day[" + i + "]: " );
                System.out.println("Day[" + i + "]: " + Arrays.toString(fishes.toArray()));
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
            return fishes.size();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static ArrayList<Integer> loadFishes(String line) {
        ArrayList<Integer> fishes = new ArrayList<Integer>();
        String[] fishTimers = line.split(",");
        for (int i = 0; i < fishTimers.length; i++) {
            fishes.add(Integer.parseInt(fishTimers[i]));
        }
        return fishes;
    }
}
