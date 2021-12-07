package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents a fish counter
 * @author Hoa Truong
 *
 */
public class FuelCounter {

    /**
     * Get the lowest cost 
     * @param filePath Path to input data file.
     * @return int the lowest cost
     */
    public static Integer getLowestCost(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            HashMap<Integer, Integer> crabs = null;
            while ((line = br.readLine()) != null) {
                System.out.println("LINE: " + line);
                crabs = loadCrabMap(line);
            }
            return getLowestCost(crabs);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.valueOf(-1);
    }

    private static int getLowestCost(HashMap<Integer, Integer> crabs) {
        return -1;
    }

    private static HashMap<Integer, Integer> loadCrabMap(String line) {
        HashMap<Integer, Integer> crabs = new HashMap<Integer, Integer>();
        String[] crabsPositions = line.split(",");
        for (int i = 0; i < crabsPositions.length; i++) {
            int crabPosition= Integer.parseInt(crabsPositions[i]);
            if (crabs.containsKey(crabPosition)) {
                crabs.put(crabPosition, crabs.get(crabPosition) + 1);
            } else {
                crabs.put(crabPosition, 1);
            }
        }
        System.out.println("Crab Map Loaded" + Arrays.toString(crabs.entrySet().toArray()));
        return crabs;
    }
}
