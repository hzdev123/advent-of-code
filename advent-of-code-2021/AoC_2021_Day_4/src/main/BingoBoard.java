package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;

/**
 * Represents a bingo board
 * @author Hoa Truong
 *
 */
public class BingoBoard {
    List<Integer> markedSquaresIndex = null;
    List<String> squares = null;

    /**
     * Numnbers are space separated
     */
    public BingoBoard(String input) {
        markedSquaresIndex = new ArrayList<Integer>();
        squares = new ArrayList<String>();
        System.out.println("Bingo Board input: " + input);
        String[] numbers = input.split(" ");
        for (int i = 0; i < numbers.length; i++) {
            if (!numbers[i].equals("")) {
                squares.add(numbers[i]);
            }
        }
    }

    public void markBoard(String number) {
        //Mark if number is in board
        //checkBingo();
        //Horizontal bingo check
        //Vertical bingo check
    }
    
    private void checkBingo() {
    	
    }
	
    public String toString() {
        StringBuilder board = new StringBuilder();
        for (int i = 1; i < squares.size() + 1; i++) {
            if (i > 1
                && i == squares.size()
                && i % 5 == 0) {
                board.append(squares.get(i - 1));
            } else if (i > 1
                && i % 5 == 0) {
                board.append(squares.get(i - 1) + "\n");
            } else {
                board.append(squares.get(i - 1) + " ");
            }
        }
        return board.toString();
    }
}
