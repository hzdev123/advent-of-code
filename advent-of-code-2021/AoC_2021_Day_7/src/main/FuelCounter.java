package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Represents a fuel counter
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
            Map<Integer, Integer> positionMap = null;
            while ((line = br.readLine()) != null) {
//                System.out.println("LINE: " + line);
                positionMap = loadPositionMap(line);
            }
            Map<Integer, Integer> costMap = getCostMap(positionMap, variableCost);
            return getLowestCost(costMap);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static Map<Integer, Integer> getCostMap(Map<Integer, Integer> positionMap, boolean variableCost) {
        Map<Integer, Integer> costMap = new HashMap<Integer, Integer>();
        List<Integer> positionKeys = null;
        List<Integer> crabKeys = null;
        if (variableCost) {
            //VariableCost true:
            //    consider keySet all elements from min to max
            positionKeys = getAllElementsFromKeys(positionMap);
            crabKeys = getKeys(positionMap);;
        } else {
            //VariableCost false:
            //    only consider keySet
            positionKeys = getKeys(positionMap);
            crabKeys = positionKeys;
        }
        for (int positionIdx = 0; positionIdx < positionKeys.size(); positionIdx++) {
            //Only calculate moveCost for positions with crab
            int costSum = 0;
            int position = positionKeys.get(positionIdx);
            int moveCostSum = 0;
//            System.out.println("Position: " + position);
            for (int otherPositionIdx = 0; otherPositionIdx < crabKeys.size(); otherPositionIdx++) {
                int otherPosition = crabKeys.get(otherPositionIdx);
                if (position != otherPosition) {
                    int moveCost = getCost(position, otherPosition, variableCost);
//                    System.out.println(
//                        "    Moving to [" + position + "]: " + otherPosition + " + -> " + position + " = " + moveCost);
                    int nbrOfCrabs = positionMap.get(otherPosition);
//                  System.out.println("Nbr of Crabs at Position: " + otherPosition + " -> " + nbrOfCrabs);
                    moveCostSum += nbrOfCrabs * moveCost;
                }
            }
//            System.out.println("    Position: " + position + " costs " + moveCostSum);
            costMap.put(position, moveCostSum);
        }
//        System.out.println("costMap Created" + Arrays.toString(costMap.entrySet().toArray()));
        return costMap;
    }

    private static List<Integer> getAllElementsFromKeys(Map<Integer, Integer> map) {
        List<Integer> elements = new ArrayList<Integer>();
        List<Integer> keys = new ArrayList<Integer>(map.keySet());
        Collections.sort(keys);
//        System.out.println("Sorted keys: " + Arrays.toString(keys.toArray()));
        for (int i = keys.get(0); i < keys.get(keys.size() - 1) + 1; i++) {
            elements.add(i);
        }
//        System.out.println("Elements: " + Arrays.toString(elements.toArray()));
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

    private static List<Integer> getKeys(Map<Integer, Integer> map) {
        return new ArrayList<Integer>(map.keySet());
    }

    private static int getLowestCost(Map<Integer, Integer> costMap) {
        int lowestCost = Integer.MAX_VALUE;
        for (Integer position : costMap.keySet()) {
            int currentCost = costMap.get(position);
            if (currentCost < lowestCost) {
                lowestCost = currentCost;
            }
        }
        return lowestCost;
    }

    private static Map<Integer, Integer> loadPositionMap(String line) {
        Map<Integer, Integer> crabs = new HashMap<Integer, Integer>();
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
