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
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Represents a syntax checker
 * @author Hoa Truong
 *
 */
public class SyntaxChecker {

	
//	stack add size/2 pop
	
    /**
     * Returns the syntax error score
     * @param filePath Path to input data file.
     * @return int the syntax error score
     */
    public static int getSyntaxErrorScore(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println("LINE: " + line);
            }
            return -1;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
