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
    private int mapXlength = 0;

    List<Integer> octopuses;
    public FlashCounter() {
        octopuses = new ArrayList<Integer>();
    }

    /**
     * Get the number of flashes after n steps
     * @param filePath Path to input data file.
     */
    public int getNbrOfFlashes(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            //Load octopuses
            while ((line = br.readLine()) != null) {
                System.out.println("LINE: " + line);
                loadOctopuses(line);
            }
            //Process octopuses
            for (int i = 0; i < octopuses.size(); i++) {
                System.out.println("Octopuses[" + i + "]: " + octopuses.get(i));
            }

            octopuses.clear();
            return 0;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private void loadOctopuses(String line) {
        String[] lineSplit = line.split("");
        if (mapXlength == 0) {
            mapXlength = lineSplit.length;
        }
        for (int i = 0; i < lineSplit.length; i++) {
            octopuses.add(Integer.parseInt(lineSplit[i]));
        }
    }

    private int getAboveDigit(int listIdx, List<Integer> octopuses) {
        if (listIdx > mapXlength - 1) {
//            System.out.println("listIdx: " + listIdx);
//            System.out.println("  Above digit: " + octopuses.get(listIdx - mapXlength));
            return octopuses.get(listIdx - mapXlength);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private int getBelowDigit(int listIdx, List<Integer> octopuses) {
        if (listIdx < octopuses.size() - mapXlength) {
//            System.out.println("listIdx: " + listIdx);
//            System.out.println("  Below digit: " + octopuses.get(listIdx + mapXlength));
            return octopuses.get(listIdx + mapXlength);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private int getLeftDigit(int listIdx, List<Integer> octopuses) {
        if (listIdx > 0
            && listIdx % mapXlength != 0) {
//            System.out.println("listIdx: " + listIdx);
//            System.out.println("  Left digit: " + octopuses.get(listIdx -1));
            return octopuses.get(listIdx - 1);
        } else {
            return Integer.MAX_VALUE;
        }
    }

    private int getRightDigit(int listIdx, List<Integer> octopuses) {
        if (listIdx < octopuses.size() - 1
            && listIdx % mapXlength != (mapXlength - 1)) {
//            System.out.println("listIdx: " + listIdx);
//            System.out.println("  Right digit: " + octopuses.get(listIdx + 1));
            return octopuses.get(listIdx + 1);
        } else {
            return Integer.MAX_VALUE;
        }
    }
}
