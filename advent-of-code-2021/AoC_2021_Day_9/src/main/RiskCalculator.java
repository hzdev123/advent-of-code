package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;

/**
 * Represents a risk calculator
 * @author Hoa Truong
 *
 */
public class RiskCalculator {
    private int mapXlength = -1;
    private List<Integer> digits = null;
    private Map<Integer, Integer> lowPointMap = null;

    public RiskCalculator() {
        digits = new ArrayList<Integer>();
        lowPointMap = new TreeMap<Integer, Integer>();
    }

    /**
     * Gives the product of the three largest basin size
     * @param filePath Path to input data file.
     * @return int the product of the three largest basin size
     */
    public int getBasinProduct(String filePath) {
        Map<Integer, Integer> lowPointMap = getLowPointMap(filePath);
//        System.out.println("mapXlength: " + mapXlength);
//        System.out.println("digits: " + Arrays.toString(digits.toArray()));
//        System.out.println("lowPointMap: " + Arrays.toString(lowPointMap.entrySet().toArray()));

        List<Set<Integer>> basins = new ArrayList<Set<Integer>>();
        for (int lowPointMapIdx : lowPointMap.keySet()) {
            int lowPointValue = digits.get(lowPointMapIdx);
//            System.out.println("lowPoint: " + lowPointMapIdx + " -> " + lowPointValue);
            Set<Integer> basinIdxs =  new TreeSet<Integer>();
            checkAdjacent(lowPointMapIdx, basinIdxs);
            basins.add(basinIdxs);
//            System.out.println("basin[" + lowPointMapIdx + "]:\n    " + Arrays.toString(basinIdxs.toArray()) + ": " + basinIdxs.size());
//            System.out.println("end recursion\n\n\n\n\n\n\n\n");
        }
        return getLargestBasinProduct(basins, 3);
    }

    private int getLargestBasinProduct(List<Set<Integer>> basins, int nbr) {
//        System.out.println("basins:\n    " + Arrays.toString(basins.toArray()));
        List<Integer> largestBasinsSize = new ArrayList<Integer>();
        for (int basinIdx = 0; basinIdx < basins.size(); basinIdx++) {
            Set<Integer> basin = basins.get(basinIdx);
            int basinSize = basin.size();
            if (largestBasinsSize.size() < 3) {
                largestBasinsSize.add(basinSize);
            } else {
                int min = Collections.min(largestBasinsSize);
                if (basinSize > min) {
                    int replaceIdx = largestBasinsSize.indexOf(min);
                    largestBasinsSize.set(replaceIdx, basinSize);
                }
            }
        }
        int basinSizeProduct = 1;
//        System.out.println("largest basins:\n    " + Arrays.toString(largestBasinsSize .toArray()));
        for (int i = 0; i < nbr; i++) {
            basinSizeProduct *= largestBasinsSize.get(i);
        }
        return basinSizeProduct;
    }

    private void checkAdjacent(int lowPointMapIdx, Set<Integer> basinIdxs) {
        basinIdxs.add(lowPointMapIdx);
        int currentVal = digits.get(lowPointMapIdx);
        int above = getAboveDigit(lowPointMapIdx);
        int below = getBelowDigit(lowPointMapIdx);
        int left = getLeftDigit(lowPointMapIdx);
        int right = getRightDigit(lowPointMapIdx);
//        System.out.println("  \nchecking[" + lowPointMapIdx + "]: " + currentVal);
//        System.out.println("    above [" + lowPointMapIdx+ "]: " + above);
//        System.out.println("    below [" + lowPointMapIdx+ "]: " + below);
//        System.out.println("    left  [" + lowPointMapIdx+ "]: " + left);
//        System.out.println("    right [" + lowPointMapIdx+ "]: " + right);
        if (above < 9
            && currentVal < above) {
            checkAdjacent(lowPointMapIdx - mapXlength, basinIdxs);
        }
        if (below < 9
            && currentVal < below) {
            checkAdjacent(lowPointMapIdx + mapXlength, basinIdxs);
        }
        if (left < 9
            && currentVal < left) {
            checkAdjacent(lowPointMapIdx - 1, basinIdxs);
        }
        if (right < 9
            && currentVal < right) {
            checkAdjacent(lowPointMapIdx + 1, basinIdxs);
        }
        return;
    }

    /**
     * Gives the sum of risk levels based on height map
     * @param filePath Path to input data file.
     * @return int the sum of risk levels
     */
    public int getSum(String filePath) {
        int riskLevelSum = 0;
        Map<Integer, Integer> lowPointMap = getLowPointMap(filePath);
        for (int lowPointMapIdx : lowPointMap.keySet()) {
            riskLevelSum += lowPointMap.get(lowPointMapIdx) + 1;
        }
        return riskLevelSum;
    }

    private Map<Integer, Integer> getLowPointMap(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            //Setup the indata
            String line = "";
            int mapXlength = 0;
            this.digits = new ArrayList<Integer>();
            while ((line = br.readLine()) != null) {
//                System.out.println("LINE: " + line);
                String[] digit = line.split("");
                if (mapXlength == 0) {
                    mapXlength = digit.length;
                    this.mapXlength = digit.length;
                }
                for (int digitIdx = 0; digitIdx < digit.length; digitIdx++) {
                    digits.add(Integer.parseInt(digit[digitIdx]));
                }
            }
//            System.out.println("LINE2: " + Arrays.toString(digits.toArray()));
//            System.out.println("mapYlength: " + mapXlength + "\n");

            //Process the indata
            this.lowPointMap = new TreeMap<Integer, Integer>();
            for (int listIdx = 0; listIdx < digits.size(); listIdx++) {
                int currentDigit = digits.get(listIdx);
                if (isLowPoint(currentDigit, listIdx)) {
//                    System.out.println("lowPoint[" + listIdx + "]: " + currentDigit + "\n");
                    lowPointMap.put(listIdx, currentDigit);
                }
            }
            return lowPointMap;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getAboveDigit(int listIdx) {
        if (listIdx > mapXlength - 1) {
//        	  System.out.println("listIdx: " + listIdx);
//            System.out.println("  Above digit: " + digits.get(listIdx - mapXlength));
            return digits.get(listIdx - mapXlength);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private int getBelowDigit(int listIdx) {
        if (listIdx < digits.size() - mapXlength) {
//            System.out.println("listIdx: " + listIdx);
//            System.out.println("  Below digit: " + digits.get(listIdx + mapXlength));
            return digits.get(listIdx + mapXlength);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private int getLeftDigit(int listIdx) {
        if (listIdx > 0
            && listIdx % mapXlength != 0) {
//            System.out.println("listIdx: " + listIdx);
//            System.out.println("  Left digit: " + digits.get(listIdx -1));
            return digits.get(listIdx - 1);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private int getRightDigit(int listIdx) {
        if (listIdx < digits.size() - 1
            && listIdx % mapXlength != (mapXlength - 1)) {
//            System.out.println("listIdx: " + listIdx);
//            System.out.println("  Right digit: " + digits.get(listIdx + 1));
            return digits.get(listIdx + 1);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private boolean isLowPoint(int currentDigit, int listIdx) {
        return currentDigit < getAboveDigit(listIdx)
            && currentDigit < getBelowDigit(listIdx)
            && currentDigit < getLeftDigit(listIdx)
            && currentDigit < getRightDigit(listIdx);
    }
}
