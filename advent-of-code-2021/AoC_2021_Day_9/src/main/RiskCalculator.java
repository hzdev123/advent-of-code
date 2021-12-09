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
            String line = "";
            int riskLevelSum = 0;
            while ((line = br.readLine()) != null) {
                System.out.println("LINE: " + line);
            }
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
