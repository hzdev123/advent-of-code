package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import java.util.List;

import java.lang.StringBuilder;

/**
 * Represents a bingo game
 * @author Hoa Truong
 *
 */
public class BingoGame {
	
    /**
     * Play bingo until a winner is decided or the input is exhausted
     * @param winLast  false to count the first win, true to count the last win
     * @param filePath Path to input data file.
     * @return int the product of current number and sum of unmarked squares
     *             or -1 if there is no winner.
     */
    public static int play(String filePath, boolean winLast){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            List<String> markingNumbers = null;
            List<BingoBoard> bingoBoards = new ArrayList<BingoBoard>();
            int boardRowCounter = 0;
            StringBuilder boardBuilder = new StringBuilder();
            //Setup the bingoBoard and markingNumber from input
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.split("");
                if (line.contains(",")) {
//                    System.out.println("Ready input markers: " + line);
                    markingNumbers = getMarkingNumbers(line);
                } else if(line.contains(" ")) {
//                    System.out.println("Building board: " + line);
                    boardRowCounter++;
                    boardBuilder.append(line + " ");
                    //Create board from stored number rows
                    if (boardRowCounter == 5) {
                        bingoBoards.add(
                            new BingoBoard(boardBuilder.toString()));
                        boardBuilder.setLength(0);
                        boardRowCounter = 0;
                    }
                } 
            }
            //Check input setup
            StringBuilder markingNbrs = new StringBuilder();
            for (int i = 0; i < markingNumbers.size(); i++) {
                markingNbrs.append(markingNumbers.get(i) + " ");
            }
//            System.out.println("Marking numbers: " + markingNbrs.toString());
//            for (int i = 0; i < bingoBoards.size(); i++) {
//                System.out.println(bingoBoards.get(i).toString() + "\n");
//            }

            //Play Bingo
            // Calling out one markingNumber at a time
            for (int i = 0; i < markingNumbers.size(); i++) {
                String currentMarkingNumber = markingNumbers.get(i);
//                System.out.println("\nCurrent marking number[" + i + "]: " + currentMarkingNumber );
                for (int j = 0; j < bingoBoards.size(); j++) {
                    BingoBoard bingoBoard = bingoBoards.get(j);
                    bingoBoard.mark(currentMarkingNumber);
                    if (bingoBoard.checkBingo()
                        && winLast
                        && bingoBoards.size() > 1) {
//                      System.out.println("\nDropping for marking number: " + markingNumbers.get(i));
//	                    System.out.println(bingoBoard.toString());
                        bingoBoards.remove(j);
                        j--;
                    } else if (bingoBoard.checkBingo()
                        && ((!winLast)
                        || (winLast
                            && bingoBoards.size() == 1))) {
	                    int unMarkedSum = bingoBoard.getUnmarkedSum();
//	                    System.out.println("\nBingo for");
//	                    System.out.println(bingoBoard.toString());
//	                    System.out.println("\n" + markingNumbers.get(i) + " * " + unMarkedSum);
	                    return Integer.parseInt(currentMarkingNumber)
	                        * unMarkedSum;
                    }
                }
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

    private static ArrayList<String> getMarkingNumbers(String line) {
        ArrayList<String> markingNumbers = new ArrayList<String>();
        Collections.addAll(markingNumbers, line.split(",")); 
        return markingNumbers;
    }
}