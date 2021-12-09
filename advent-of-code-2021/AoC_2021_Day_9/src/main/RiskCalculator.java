package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

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
            int riskLevelSum = 0;
            String line = "";
            int mapLength = 0;
            int mapHeight = 0;
            ArrayList<Integer> mapAsList = new ArrayList<Integer>();
            while ((line = br.readLine()) != null) {
                System.out.println("LINE: " + line);
                String[] digit = line.split("");
                if (mapLength == 0) {
                    mapLength = digit.length;
                }
                for (int digitIdx = 0; digitIdx < digit.length; digitIdx++) {
                    mapAsList.add(Integer.parseInt(digit[digitIdx]));
                }
                mapHeight++;
            }
            System.out.println("LINE2: " + Arrays.toString(mapAsList.toArray()));
            System.out.println("mapLength: " + mapLength);
            System.out.println("mapHeight: " + mapHeight);

            //Process the indata


            return riskLevelSum;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
