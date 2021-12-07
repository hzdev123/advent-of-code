package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.ArrayList;
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
     * @param variableCost true if the cost increases with distace.
     * @return int the lowest cost
     */
    public static int getLowestCost(String filePath, boolean variableCost) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            HashMap<Integer, Integer> positionMap = null;
            while ((line = br.readLine()) != null) {
//                System.out.println("LINE: " + line);
                positionMap = loadPositionMap(line);
            }
            HashMap<Integer, Integer> costMap = getCostMap(positionMap, variableCost);
            return getLowestCost(costMap);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.valueOf(-1);
    }

    private static HashMap<Integer, Integer> getCostMap(HashMap<Integer, Integer> positionMap, boolean variableCost) {
        HashMap<Integer, Integer> costMap = new HashMap<Integer, Integer>();
        ArrayList<Integer> keys = getKeys(positionMap);
        for (int positionIdx = 0; positionIdx < keys.size(); positionIdx++) {
            int costSum = 0;
            int position = keys.get(positionIdx);
            int moveCostSum = 0;
//            System.out.println("Position: " + position);
            for (int otherPositionIdx = 0; otherPositionIdx < keys.size(); otherPositionIdx++) {
                int otherPosition = keys.get(otherPositionIdx);
                int nbrOfCrabs = positionMap.get(otherPosition);
//                System.out.println("Nbr of Crabs at Position: " + otherPosition + " -> " + nbrOfCrabs);
                if (position != otherPosition) {
//                    System.out.println(
//                        "    Moving to [" + position + "]: " + otherPosition + " + -> " + position + " = " + Math.abs((position - otherPosition)));
                    int moveCost = getCost(position, otherPosition, variableCost);
                    moveCostSum += nbrOfCrabs * moveCost;
                }
            }
//            System.out.println("    Position: " + position + " costs " + moveCostSum);
            costMap.put(position, moveCostSum);
        }
//        System.out.println("costMap Created" + Arrays.toString(costMap.entrySet().toArray()));
        return costMap;
    }

    private static int getCost(int position, int otherPosition, boolean variableCost) {
        if (variableCost) {
            //Increase by one for each step
            return Math.abs(position - otherPosition);
        } else {
            return Math.abs(position - otherPosition);
        }
    }

    private static ArrayList<Integer> getKeys(HashMap<Integer, Integer> map) {
        return new ArrayList<Integer>(map.keySet());
    }

    private static int getLowestCost(HashMap<Integer, Integer> costMap) {
        int lowestCost = Integer.MAX_VALUE;
        for (Integer position : costMap.keySet()) {
            int currentCost = costMap.get(position);
            if (currentCost < lowestCost) {
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
//        System.out.println("positionMap Loaded" + Arrays.toString(crabs.entrySet().toArray()));
        return crabs;
    }
}
