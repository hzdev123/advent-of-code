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
     * Get the product of the gamma and epsilon rate.
     * @param filePath Path to data file.
     * @return the product of the gamma and epsilon rate.
     */
    public static int play(String filePath){
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
            System.out.println("Marking numbers: " + markingNbrs.toString());
            for (int i = 0; i < bingoBoards.size(); i++) {
                System.out.println(bingoBoards.get(i).toString() + "\n");
            }

            //Play Bingo
            // Calling out one markingNumber at a time
            for (int i = 0; i < markingNumbers.size(); i++) {
                System.out.println("\nCurrent marking number[" + i + "]: " + markingNumbers.get(i));
                for (int j = 0; j < bingoBoards.size(); j++) {
                    BingoBoard bingoBoard = bingoBoards.get(j);
                    bingoBoard.mark(markingNumbers.get(i));
                    if (bingoBoard.checkBingo()) {
                        System.out.println("\nBingo for");
                        System.out.println(bingoBoard.toString());
                        return 1;
                    }
                }
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

    private static ArrayList<String> getMarkingNumbers(String line) {
        ArrayList<String> markingNumbers = new ArrayList<String>();
        Collections.addAll(markingNumbers, line.split(",")); 
        return markingNumbers;
    }
}