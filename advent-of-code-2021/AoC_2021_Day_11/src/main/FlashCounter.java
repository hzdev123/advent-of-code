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
 * Represents a flash counter
 * @author Hoa Truong
 *
 */
public class FlashCounter {

    /**
     * Get the number of flashes after n steps
     * @param filePath Path to input data file.
     * @param nbrOfSteps number of steps to iterate.
     * @return int the number of flashes after n steps
     */
    public static int getNbrOfFlashes(String filePath, int nbrOfsteps) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            Map<Integer, Integer> positionMap = null;
            while ((line = br.readLine()) != null) {
                System.out.println("LINE: " + line);
            }
            return 0;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
