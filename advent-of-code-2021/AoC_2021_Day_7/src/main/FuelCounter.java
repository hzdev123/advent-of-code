package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
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
        ArrayList<Integer> keys = null;
        if (variableCost) {
            //VariableCost true:
            //    consider keySet all elements from min to max
            keys = getAllElementsFromKeys(positionMap);
        } else {
            //VariableCost false:
            //    only consider keySet
            keys = getKeys(positionMap);
        }
        for (int positionIdx = 0; positionIdx < keys.size(); positionIdx++) {
            //Only calculate moveCost for positions with crab
            int position = keys.get(positionIdx);
            if (!variableCost
                || positionMap.containsKey(position)) {
                int costSum = 0;
                int moveCostSum = 0;
                System.out.println("Position: " + position);
                for (int otherPositionIdx = 0; otherPositionIdx < keys.size(); otherPositionIdx++) {
                    int otherPosition = keys.get(otherPositionIdx);
                    if (position != otherPosition) {
                        int moveCost = getCost(position, otherPosition, variableCost);
                        System.out.println(
                            "    Moving to [" + position + "]: " + otherPosition + " + -> " + position + " = " + moveCost);
                        int nbrOfCrabs = 0;
                        if (variableCost) {
                            nbrOfCrabs = positionMap.get(position);
                        } else {
                            nbrOfCrabs = positionMap.get(otherPosition);
                        }
                        System.out.println("Nbr of Crabs at Position: " + otherPosition + " -> " + nbrOfCrabs);
                        moveCostSum += nbrOfCrabs * moveCost;
                    }
                }
                System.out.println("    Position: " + position + " costs " + moveCostSum);
                costMap.put(position, moveCostSum);
            }
        }
        System.out.println("costMap Created" + Arrays.toString(costMap.entrySet().toArray()));
        return costMap;
    }

    private static ArrayList<Integer> getAllElementsFromKeys(HashMap<Integer, Integer> map) {
        ArrayList<Integer> elements = new ArrayList<Integer>();
        ArrayList<Integer> keys = new ArrayList<Integer>(map.keySet());
        Collections.sort(keys);
//        System.out.println("Sorted keys: " + Arrays.toString(keys.toArray()));
        for (int i = keys.get(0); i < keys.get(keys.size() - 1) + 1; i++) {
            elements.add(i);
        }
        System.out.println("Elements: " + Arrays.toString(elements.toArray()));
        return elements;
    }

    private static int getCost(int position, int otherPosition, boolean variableCost) {
        int distance = Math.abs(position - otherPosition);
        if (variableCost) {
            return getVariableCost(distance);
        } else {
            return distance;
        }
    }

    private static int getVariableCost(int distance) {
//        System.out.println("Distance: " + distance);
        int costSum = 0;
        for (int i = 1; i < distance + 1; i++) {
            costSum += i;
        }
//        System.out.println("Sum: " + costSum);
        return costSum;
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
