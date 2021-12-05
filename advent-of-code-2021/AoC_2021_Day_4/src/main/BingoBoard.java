package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.StringBuilder;

/**
 * Represents a bingo board
 * @author Hoa Truong
 *
 */
public class BingoBoard {
    List<String> nonMarkedSquares = null;
    ArrayList<Integer> markedSquaresIndex = null;
    List<String> squares = null;

    /**
     * Creates a bingo board based on the input.
     * @param input space separated numbers
     */
    public BingoBoard(String input) {
        markedSquaresIndex = new ArrayList<Integer>();
        squares = new ArrayList<String>();
        //System.out.println("Bingo Board input: " + input);
        String[] numbers = input.split(" ");
        for (int i = 0; i < numbers.length; i++) {
            if (!numbers[i].equals("")) {
                squares.add(numbers[i]);
            }
        }
        nonMarkedSquares = new ArrayList<String>();
        nonMarkedSquares.addAll(squares);
    }

    /**
     * Marks the bingo board with the given number.
     * @param number the number to mark the bingo board with
     */
    public void mark(String number) {
        if (squares.contains(number)) {
            int markedIndex = squares.indexOf(number);
            markedSquaresIndex.add(markedIndex);
            squares.set(markedIndex, "X");
//            System.out.println("Marking: " + number);
//            System.out.println(toString());
        }
    }

    /**
     * Checks if the bingo board has bingo.
     * @param boolean returns true if it is bingo
     */
    public boolean checkBingo() {
        if (markedSquaresIndex.size() > 4) {
            System.out.println("checkBingo: " + Arrays.toString(markedSquaresIndex.toArray()));
            for (int i = 0; i < markedSquaresIndex.size(); i++) {
                int currentIndex = markedSquaresIndex.get(i);
                if (checkHorizontalBingo(markedSquaresIndex, currentIndex)
                    || checkVerticalBingo(markedSquaresIndex, currentIndex)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkHorizontalBingo(ArrayList<Integer> markedSquaresIndex, int index) {
        ArrayList<Integer> horizontalIndexes = getHorizontalIndexes(index);
        for (int checkIdxPos = 0; checkIdxPos < 5; checkIdxPos++) {
            if (!markedSquaresIndex.contains(horizontalIndexes.get(checkIdxPos))) {
                return false;
            }
        }
        return true;
    }

    private boolean checkVerticalBingo(ArrayList<Integer> markedSquaresIndex, int index) {
        ArrayList<Integer> verticalIndexes = getVerticalIndexes(index);
        for (int checkIdxPos = 0; checkIdxPos < 5; checkIdxPos++) {
            if (!markedSquaresIndex.contains(verticalIndexes.get(checkIdxPos))) {
                return false;
            }
        }
        return true;
    }

    private ArrayList<Integer> getHorizontalIndexes(int index) {
        ArrayList<Integer> horizontalIndexes = new ArrayList<Integer>();
        int horizontalIndex = index - index % 5;
        for (int i = 0; i < 5; i++) {
            horizontalIndexes.add(horizontalIndex);
            horizontalIndex++;
        }
//        System.out.println("Horizontal for [" + index + "]: " + Arrays.toString(horizontalIndexes.toArray()));
        return horizontalIndexes;
    }

    private ArrayList<Integer> getVerticalIndexes(int index) {
        ArrayList<Integer> verticalIndexes = new ArrayList<Integer>();
        int verticalIndex = index % 5;
        for (int i = 0; i < 5; i++) {
            verticalIndexes.add(verticalIndex);
            verticalIndex += 5;
        }
//        System.out.println("Vertical for [" + index + "]: " + Arrays.toString(verticalIndexes.toArray()));
        return verticalIndexes;
    }

    /**
     * Returns a string representation of the bingo board
     * @return a string representation of the bingo board
     */
    public String toString() {
        return printBoard(squares) + "\n\n" + printBoard(nonMarkedSquares);
    }

    private String printBoard(List<String> squares) {
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
