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
     * Get the product of the destination coordinates.
     * @param filePath Path to data file.
     * @return the product of the destination coordinates.
     */
    public static int getProduct(String filePath) {
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

    /**
     * Get the product of the destination coordinates with aiming applied.
     * @param filePath Path to data file.
     * @return the product of the destination coordinates with aiming applied.
     */
    public static int getAimProduct(String filePath) {
        int horizontal = 0;
        int depth = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            int aim = 0;
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.split(" ");
                if (lineSplit[0].equals("forward")) {
                    horizontal += Integer.parseInt(lineSplit[1]);
                    depth += aim * Integer.parseInt(lineSplit[1]);
                } else if (lineSplit[0].equals("down")) {
                    aim += Integer.parseInt(lineSplit[1]);
                } else if (lineSplit[0].equals("up")) {
                    aim -= Integer.parseInt(lineSplit[1]);
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
