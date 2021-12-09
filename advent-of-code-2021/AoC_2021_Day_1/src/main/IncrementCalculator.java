package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Calculator which calculates integer increments
 * @author Hoa Truong
 *
 */
public class IncrementCalculator {

    /**
     * Counts the number of times the current integer sum of the three previous
     * is larger than the previous sum
     * @param filePath File path containing a series of integers.
     * @return the number of times current integer is larger than previous
     */
    public static int countEveryThree(String filePath) {
        int nbrOfIncrease = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            int prev = -1;
            List<Integer> ints = new ArrayList<Integer>();
            while ((line = br.readLine()) != null) {
                ints.add(Integer.parseInt(line.trim()));
            }
            for (int i = 0; i < ints.size(); i++) {
                if (i > 1) {
                    int curr = ints.get(i) + ints.get(i - 1) + ints.get(i - 2);
                    if (prev != -1
                        && curr > prev) {
                        nbrOfIncrease++;
                    }
                    prev = curr;
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nbrOfIncrease;
    }

    /**
     * Counts the number of times the current integer is larger than the previous
     * @param filePath File path containing a series of integers.
     * @return the number of times current integer is larger than previous
     */
    public static int countEveryOne(String filePath) {
        int nbrOfIncrease = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            int prev = -1;
            while ((line = br.readLine()) != null) {
                int curr = Integer.parseInt(line.trim());
                if (prev != -1
                	&& curr > prev) {
                    nbrOfIncrease++;
                }
                prev = curr;
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nbrOfIncrease;
    }
}
