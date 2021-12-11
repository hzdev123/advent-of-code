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
    int nbrfOfFlashes = 0;
    List<Integer> flashedIdx = null;

    public FlashCounter() {
        octopuses = new ArrayList<Integer>();
        flashedIdx = new ArrayList<Integer>();
    }

    /**
     * Get the number of flashes after n steps
     * @param filePath Path to input data file.
     */
    public int getNbrOfFlashes(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = br.readLine()) != null) {
//                System.out.println("LINE: " + line);
                loadOctopuses(line);
            }
            for (int i = 0; i < 100; i++) {
                System.out.println("Step: " + i);
                simulateOctopusesFlashes();
                printOctopuses();
                flashedIdx.clear();
            }
            octopuses.clear();
            return nbrfOfFlashes;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Resets the flash counter
     */
    public void reset() {
        nbrfOfFlashes = 0;
        octopuses.clear();
        flashedIdx.clear();
    }

    private void printOctopuses() {
        int x = 0;
        for (int m = 0; m < octopuses.size(); m++) {
            int octopusValue = octopuses.get(m);
            if (octopusValue == 0) {
                System.out.print(". ");
            } else {
                System.out.print(octopusValue + " ");
            }
            x++;
            if (x == 10) {
                x = 0;
                System.out.print("\n");
            }
        }
        System.out.println("#: "+ nbrfOfFlashes + "\n");
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

    private void simulateOctopusesFlashes() {
        for (int octopusIdx = 0; octopusIdx < octopuses.size(); octopusIdx++) {
            visitOctopus(octopusIdx);
        }
    }

    private void visitOctopus(int octopusIdx) {
        if (octopusIdx == -1
            || flashedIdx.contains(octopusIdx)) {
            return;
        }
        int octopusValue = octopuses.get(octopusIdx);
//        System.out.println("Octopuses[" + octopusIdx + "]: " + octopusValue);
        int visitedOctopusValue = octopusValue + 1;
        if (visitedOctopusValue == 10) {
//          System.out.println("FLASH OctopusesIdx: " + octopusIdx);
//          System.out.println("    other aboveLeftIdx:  " + getAboveLeftIdx(octopusIdx));
//          System.out.println("    other aboveIdx:      " + getAboveIdx(octopusIdx));
//          System.out.println("    other aboveRightIdx: " + getAboveRightIdx(octopusIdx));
//          System.out.println("    other rightIdx:      " + getRightIdx(octopusIdx));
//          System.out.println("    other belowRightsIdx:" + getBelowRightIdx(octopusIdx));
//          System.out.println("    other belowIdx:      " + getBelowIdx(octopusIdx));
//          System.out.println("    other belowLeftIdx:  " + getBelowLeftIdx(octopusIdx));
//          System.out.println("    other leftIdx:       " + getLeftIdx(octopusIdx));
            flashedIdx.add(octopusIdx);
            nbrfOfFlashes++;
            octopuses.set(octopusIdx, 0);
            visitOctopus(getAboveLeftIdx(octopusIdx));
            visitOctopus(getAboveIdx(octopusIdx));
            visitOctopus(getAboveRightIdx(octopusIdx));
            visitOctopus(getRightIdx(octopusIdx));
            visitOctopus(getBelowRightIdx(octopusIdx));
            visitOctopus(getBelowIdx(octopusIdx));
            visitOctopus(getBelowLeftIdx(octopusIdx));
            visitOctopus(getLeftIdx(octopusIdx));
        } else {
            octopuses.set(octopusIdx, visitedOctopusValue);
        }
    }

    private int getAboveRightIdx(int listIdx) {
        if (listIdx > mapXlength - 1
            && listIdx % mapXlength != (mapXlength - 1)) { 		//DUPLIVCATED???
//                System.out.println("listIdx: " + listIdx);
//                System.out.println("  Above digit: " + octopuses.get(listIdx - mapXlength));
                return listIdx - mapXlength + 1;
            } else {
                return -1;
            }
    }

    private int getAboveLeftIdx(int listIdx) {
        if (listIdx > mapXlength - 1
            && listIdx > 0
            && listIdx % mapXlength != 0) {
//            System.out.println("listIdx: " + listIdx);
//            System.out.println("  Above left: " + octopuses.get(listIdx - mapXlength));
            return listIdx - mapXlength - 1;
        } else {
            return -1;
        }
    }

    private int getBelowRightIdx(int listIdx) {
        if (listIdx < octopuses.size() - mapXlength
            && listIdx < octopuses.size() - 1
            && listIdx % mapXlength != (mapXlength - 1)
            ) {
//            System.out.println("listIdx: " + listIdx);
//            System.out.println("  Below right: " + octopuses.get(listIdx + mapXlength));
            return listIdx + mapXlength + 1;
        } else {
            return -1;
        }
    }

    private int getBelowLeftIdx(int listIdx) {
        if (listIdx < octopuses.size() - mapXlength
            && listIdx > 0
            && listIdx % mapXlength != 0) {
//            System.out.println("listIdx: " + listIdx);
//            System.out.println("  Below left: " + octopuses.get(listIdx + mapXlength));
            return listIdx + mapXlength - 1;
        } else {
            return -1;
        }
    }

    private int getAboveIdx(int listIdx) {
        if (listIdx > mapXlength - 1) {
//            System.out.println("listIdx: " + listIdx);
//            System.out.println("  Above digit: " + octopuses.get(listIdx - mapXlength));
            return listIdx - mapXlength;
        } else {
            return -1;
        }
    }

    private int getBelowIdx(int listIdx) {
        if (listIdx < octopuses.size() - mapXlength) {
//            System.out.println("listIdx: " + listIdx);
//            System.out.println("  Below digit: " + octopuses.get(listIdx + mapXlength));
            return listIdx + mapXlength;
        } else {
            return -1;
        }
    }

    private int getLeftIdx(int listIdx) {
        if (listIdx > 0
            && listIdx % mapXlength != 0) {
//            System.out.println("listIdx: " + listIdx);
//            System.out.println("  Left digit: " + octopuses.get(listIdx -1));
            return listIdx - 1;
        } else {
            return -1;
        }
    }

    private int getRightIdx(int listIdx) {
        if (listIdx < octopuses.size() - 1
            && listIdx % mapXlength != (mapXlength - 1)) {
//            System.out.println("listIdx: " + listIdx);
//            System.out.println("  Right digit: " + octopuses.get(listIdx + 1));
            return listIdx + 1;
        } else {
            return -1;
        }
    }
}
