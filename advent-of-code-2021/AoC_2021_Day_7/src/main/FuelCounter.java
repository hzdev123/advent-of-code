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
            HashMap<Integer, Integer> positionMap = null;
            while ((line = br.readLine()) != null) {
                System.out.println("LINE: " + line);
                positionMap = loadPositionMap(line);
            }
            HashMap<Integer, Integer> costMap = getCostMap(positionMap);
            return getLowestCost(costMap);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.valueOf(-1);
    }

    private static HashMap<Integer, Integer> getCostMap(HashMap<Integer, Integer> positionMap) {
        HashMap<Integer, Integer> costMap = new HashMap<Integer, Integer>();
        //For every position, count the cost of moving to it from all other position
//    	Iterate keySet()
//    	TreeMap
//    	positionMap: K -> V: position -> number of crabs
//    	costMap: K -> V: position -> cost for all crabs to move here
//
//
//    	For every position in positionMap,
//    		count the cost for moving into it,
//    			iterate positionMap keySet()
//    			calclate diff between current position/key and value
//    			costSum += currentPosition - referencePosition
//
//    			save as k-v positionMap -> position -> cost for all ...
//
//    	Iterate through costMap and pick lowest cost
        return costMap;
    }

    private static int getLowestCost(HashMap<Integer, Integer> costMap) {
        int lowestCost = Integer.MAX_VALUE;
        for (Integer position : costMap.keySet()) {
            int currentCost = costMap.get(position);
            if (currentCost > lowestCost) {
                lowestCost = currentCost;
            }
        }
        return lowestCost;
    }

    private static HashMap<Integer, Integer> loadPositionMap(String line) {
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
