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
 * Represents a dot counter
 * @author Hoa Truong
 *
 */
public class DotCounter {
    private int xMaxLength = 0;
    private int yMaxLength = 0;
    private List<Integer> dotsIdxs = null;
    private List<Integer> newDotsIdxs = null;
    private List<String> foldCmds = null;
    private List<Integer> foldLineIdxs = null;

    public DotCounter() {
        dotsIdxs = new ArrayList<Integer>();
        newDotsIdxs = new ArrayList<Integer>();
        foldCmds = new ArrayList<String>();
        foldLineIdxs = new ArrayList<Integer>();
    }

    /**
     * Count the sum of four digits output
     * @param filePath Path to input data file.
     * @return int the sum of four digits output
     */
    public int countDots(String filePath) {
        loadDots(filePath);
        //printDots();
        dofoldings();
        return -1;
    }

    private void dofoldings() {
        for (int i = 0; i < 1; i++) {
            String foldCmd = foldCmds.get(i);
            System.out.println("fold[" + i + "]: " + foldCmd);
            int foldLine = Integer.parseInt(foldCmd.split("=")[1]);
            System.out.println("fold[" + i + "]: " + foldLine);
            if (foldCmd.contains("y=")) {    //fold up
                foldUp(foldLine);
            } else {                         // fold left
                foldLeft(foldLine);
            }
        }
    }

    private void loadXFoldLineIdxs(int foldLine, int foldLineStartIdx, int foldLineEndIdx) {
        for (int i = foldLineStartIdx; i < foldLineEndIdx; i++) {
            foldLineIdxs.add(i);
        }
    }

    private void foldUp(int foldLine) {
        System.out.println("foldUp: " + foldLine);
        int foldLineStartIdx = foldLine * (xMaxLength + 1);
        int foldLineEndIdx = foldLine * (xMaxLength + 1) + xMaxLength + 1;
        loadXFoldLineIdxs(foldLine, foldLineStartIdx, foldLineEndIdx);
        printDots();
        foldDotsUp(foldLine, foldLineStartIdx, foldLineEndIdx);
        //        printFoldedUp(foldLine * (xMaxLength + 1));
        //        printDots();

//        foldCmds.clear();
//        yMaxLength = yMaxLength/2;
    }

    private void foldDotsUp(int foldLine, int foldLineStartIdx, int foldLineEndIdx) {
        int bottomHalfIdx = foldLine * (xMaxLength + 1) + xMaxLength + 2;
        int endIdx = (xMaxLength + 1) * (yMaxLength + 1);
        for (int mapIdx = bottomHalfIdx; mapIdx < endIdx; mapIdx++) {
            if (dotsIdxs.contains(mapIdx)) {
                int dotIdxIdx = dotsIdxs.indexOf(mapIdx);
                int dotIdx = dotsIdxs.get(dotIdxIdx);

                int newDotIdx = getNewDotIdx(dotIdx, foldLineStartIdx, foldLineEndIdx);
                newDotsIdxs.add(newDotIdx);
                dotsIdxs.remove(dotIdxIdx);

                System.out.println("dotIdx " + dotIdx + ": transforms to " + newDotIdx);
            }
        }
        Collections.sort(newDotsIdxs);
        printFoldedUp(foldLine * (xMaxLength + 1));
    }

    //TODO:get correct x index
    private int getNewDotIdx(int dotIdx, int foldLineStartIdx, int foldLineEndIdx) {
        System.out.println("  getNewDotIdx: " + dotIdx);
        int dotIdxToFoldLineEnd = dotIdx - foldLineEndIdx;
        int newDotIdx = foldLineStartIdx - dotIdxToFoldLineEnd; //TODO: here
        System.out.println("  getNewDotIdx[" + dotIdx + "]: " + newDotIdx);
        return newDotIdx;
    }

    private void printFoldedUp(int endIdx) {
        System.out.println("printFoldedUp: " + endIdx);
        int x = 0;
        for (int i = 0; i < endIdx; i++) {
            x++;
            if (dotsIdxs.contains(i)) {
                System.out.print("#");
            } else if (newDotsIdxs.contains(i)) {
                System.out.print("x");
            } else {
                System.out.print(".");
            }
            if (x == xMaxLength + 1) {
                System.out.println("");
                x = 0;
            }
        }
    }

    private void foldLeft(int foldLine) {
        System.out.println("foldLeft: " + foldLine);
    }

    private void loadDots(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            getXLenght(filePath);
            while ((line = br.readLine()) != null) {
                System.out.println("LINE: " + line);
                if (line.contains(",")) {
                    String[] coordinates = line.split(",");
                    int x = Integer.parseInt(coordinates[0]);
                    int y = Integer.parseInt(coordinates[1]);
                    int dotIdx = xyToDotsIdx(x,y);
                    dotsIdxs.add(dotIdx);
                } else if (line.contains("fold along")) {
                    String[] foldLine = line.split(" ");
                    foldCmds.add(foldLine[2]);
                }
            }
            Collections.sort(dotsIdxs);
            System.out.println("dotsIdxs: " + Arrays.toString(dotsIdxs.toArray()));
            System.out.println("foldCmds: " + Arrays.toString(foldCmds.toArray()));
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printDots() {
        int x = 0;
        for (int i = 0; i < (xMaxLength + 1) * (yMaxLength + 1); i++) {
            x++;
            if (foldLineIdxs.contains(i)) {
                System.out.print("-");
            } else if (dotsIdxs.contains(i)) {
                System.out.print("#");
            } else {
                System.out.print(".");
            }
            if (x == 11) {
                System.out.println("");
                x = 0;
            } 
        }
    }

    private int xyToDotsIdx(int x, int y) {
        return y * xMaxLength + y + x;
    }

    private void getXLenght(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.contains(",")) {
                	String[] coordinates = line.split(",");
                    int xLength = Integer.parseInt(coordinates[0]);
                    if (xLength > xMaxLength) {
                        xMaxLength = xLength;
                    }
                    int yLength = Integer.parseInt(coordinates[1]);
                    if (yLength > yMaxLength) {
                        yMaxLength = yLength;
                    }
                }
            }
            System.out.println("xMaxLength: " + xMaxLength);
            System.out.println("yMaxLength: " + yMaxLength);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
