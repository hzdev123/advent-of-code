package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Calculates positions
 * @author Hoa Truong
 *
 */
public class PositionCalculator {

    /**
     * Get the simple product of the destination coordinates.
     * @param filePath Path to data file.
     * @return the simple product of the destination coordinates.
     */
    public static int getSimpleProduct(String filePath) {
        int horizontal = 0;
        int depth = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.split(" ");
                if (lineSplit[0].equals("forward")) {
                    horizontal += Integer.parseInt(lineSplit[1]);
                } else if (lineSplit[0].equals("down")) {
                    depth += Integer.parseInt(lineSplit[1]);
                } else if (lineSplit[0].equals("up")) {
                    depth -= Integer.parseInt(lineSplit[1]);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return horizontal * depth;
    }
}
