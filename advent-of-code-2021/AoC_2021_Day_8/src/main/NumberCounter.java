package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Represents a fish counter
 * @author Hoa Truong
 *
 */
public class NumberCounter {

    /**
     * Count the occurrence of numbers with unique number of segments
     * @param filePath Path to input data file.
     * @return int numbers with unique number of segments
     */
    public static int countUnique(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            HashMap<Integer, Integer> positionMap = null;
            ArrayList<String[]> fourDigits = new ArrayList<String[]>();
            while ((line = br.readLine()) != null) {
//                System.out.println("LINE: " + line);
                String[] fourDigit = line.split("\\|")[1].trim().split(" ");
                fourDigits.add(fourDigit);
            }
            return getUniqueSegmentDigitAmount(fourDigits);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static int getUniqueSegmentDigitAmount(ArrayList<String[]> fourDigits) {
        int uniqueSum = 0;
        for (int listIdx = 0; listIdx < fourDigits.size(); listIdx++) {
            String[] fourDigit = fourDigits.get(listIdx);
            for (int digitIdx = 0; digitIdx < 4; digitIdx++) {
//                System.out.println("    DIGIT["+digitIdx+"]   : " + fourDigit[digitIdx]);
                if (uniqueSegmentSize(fourDigit[digitIdx].length())) {
                    uniqueSum++;
                }
            }
        }
        return uniqueSum;
    }

    private static boolean uniqueSegmentSize(int segmentSize) {
        return segmentSize == 2
            || segmentSize == 3
            || segmentSize == 4
            || segmentSize == 7;
    }
}
