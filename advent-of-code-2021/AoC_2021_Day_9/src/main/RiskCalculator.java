package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Represents a risk calculator
 * @author Hoa Truong
 *
 */
public class RiskCalculator {

    /**
     * Gives the sum of risk levels based on height map
     * @param filePath Path to input data file.
     * @return int the sum of risk levels
     */
    public static int getSum(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            //Setup the indata
            String line = "";
            int mapXlength = 0;
            ArrayList<Integer> digits = new ArrayList<Integer>();
            while ((line = br.readLine()) != null) {
//                System.out.println("LINE: " + line);
                String[] digit = line.split("");
                if (mapXlength  == 0) {
                    mapXlength  = digit.length;
                }
                for (int digitIdx = 0; digitIdx < digit.length; digitIdx++) {
                    digits.add(Integer.parseInt(digit[digitIdx]));
                }
            }
//            System.out.println("LINE2: " + Arrays.toString(digits.toArray()));
//            System.out.println("mapYlength: " + mapXlength + "\n");

            //Process the indata
            int riskLevelSum = 0;
            TreeMap<Integer, Integer> lowPointMap = new TreeMap<Integer, Integer>();
            for (int listIdx = 0; listIdx < digits.size(); listIdx++) {
                int currentDigit = digits.get(listIdx);
                if (isLowPoint(digits, currentDigit, listIdx, mapXlength)) {
                    System.out.println("lowPoint[" + listIdx + "]: " + currentDigit + "\n");
                    lowPointMap.put(listIdx, currentDigit);
                    riskLevelSum += currentDigit + 1;
                }
            }
            System.out.println("lowPointMap:\n    " + Arrays.toString(lowPointMap.entrySet().toArray()));
            return riskLevelSum;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static int getAboveDigit(ArrayList<Integer> digits, int listIdx, int mapXlength) {
        if (listIdx > mapXlength - 1) {
//        	  System.out.println("listIdx: " + listIdx);
//            System.out.println("  Above digit: " + digits.get(listIdx - mapXlength));
            return digits.get(listIdx - mapXlength);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private static int getBelowDigit(ArrayList<Integer> digits, int listIdx, int mapXlength) {
        if (listIdx < digits.size() - mapXlength) {
//            System.out.println("listIdx: " + listIdx);
//            System.out.println("  Below digit: " + digits.get(listIdx + mapXlength));
            return digits.get(listIdx + mapXlength);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private static int getLeftDigit(ArrayList<Integer> digits, int listIdx, int mapXlength) {
        if (listIdx > 0
            && listIdx % mapXlength != 0) {
//            System.out.println("listIdx: " + listIdx);
//            System.out.println("  Left digit: " + digits.get(listIdx -1));
            return digits.get(listIdx - 1);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private static int getRightDigit(ArrayList<Integer> digits, int listIdx, int mapXlength) {
        if (listIdx < digits.size() - 1
            && listIdx % mapXlength != (mapXlength - 1)) {
//            System.out.println("listIdx: " + listIdx);
//            System.out.println("  Right digit: " + digits.get(listIdx + 1));
            return digits.get(listIdx + 1);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private static boolean isLowPoint(ArrayList<Integer> digits, int currentDigit, int listIdx, int mapXlength) {
        int above = getAboveDigit(digits, listIdx, mapXlength);
        int below = getBelowDigit(digits, listIdx, mapXlength);
        int left = getLeftDigit(digits, listIdx, mapXlength);
        int right = getRightDigit(digits, listIdx, mapXlength);
        return currentDigit < above
            && currentDigit < below
            && currentDigit < left
            && currentDigit < right;
    }
}
