package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import java.math.BigInteger;

/**
 * Represents a syntax checker
 * @author Hoa Truong
 *
 */
public class SyntaxChecker {
    private Stack<String> stack = null;
	private Set<String> openSet = null;
    private Set<String> closeSet = null;
    private Map<String, Integer> errorMap= null;
    private Map<String, String> getOpen = null;
    private List<String> incompleteLines = null;
    private Map<String, BigInteger> incompleteMap = null;
    private Stack<String> lineStack = null;
    private Stack<String> closingStack = null;
    private Map<String, String> getClosing = null;

    public SyntaxChecker() {
        stack = new Stack<String>();
        openSet = new HashSet<String>();
        openSet.add("<");
        openSet.add("{");
        openSet.add("[");
        openSet.add("(");
//        System.out.println("openSet: " + Arrays.toString(openSet.toArray()));
        closeSet = new HashSet<String>();
        closeSet.add(">");
        closeSet.add("}");
        closeSet.add("]");
        closeSet.add(")");
//        System.out.println("closeSet: " + Arrays.toString(closeSet.toArray()));
        getOpen = new HashMap<String, String>();
        getOpen.put(">", "<");
        getOpen.put("}", "{");
        getOpen.put("]", "[");
        getOpen.put(")", "(");
//        System.out.println("getOpen: " + Arrays.toString(getOpen.entrySet().toArray()));
        errorMap = new HashMap<String, Integer>();
        errorMap.put(">", 25137);
        errorMap.put("}", 1197);
        errorMap.put("]", 57);
        errorMap.put(")", 3);
//        System.out.println("errorScore: " + Arrays.toString(errorScore.entrySet().toArray()));
        incompleteLines = new ArrayList<String>();
        incompleteMap= new HashMap<String, BigInteger>();
        incompleteMap.put(">", BigInteger.valueOf(4));
        incompleteMap.put("}", BigInteger.valueOf(3));
        incompleteMap.put("]", BigInteger.valueOf(2));
        incompleteMap.put(")", BigInteger.valueOf(1));
//        System.out.println("incompleteMap: " + Arrays.toString(incompleteMap.entrySet().toArray()));
        lineStack = new Stack<String>();
        closingStack = new Stack<String>();
        getClosing = new HashMap<String, String>();
        getClosing.put("<", ">");
        getClosing.put("{", "}");
        getClosing.put("[", "]");
        getClosing.put("(", ")");
//        System.out.println("getClosing: " + Arrays.toString(getClosing.entrySet().toArray()));

}

    /**
     * Returns the incomplete lines middle score
     * @param filePath Path to input data file.
     * @return int the incomplete lines middle score
     */
    public BigInteger getIncompletesMiddleScore(String filePath) {
//      System.out.println("incomplete lines: " + Arrays.toString(incompleteLines.toArray()));
        List<BigInteger> incompleteScores = new ArrayList<BigInteger>();
        for (int i = 0; i < incompleteLines.size(); i++) {
//            System.out.println("incomplete line: " + incompleteLines.get(i));
            incompleteScores.add(
                getIncompleteLineScore(incompleteLines.get(i)));
        }
//        System.out.println("incompleteScores: " + Arrays.toString(incompleteScores.toArray()));
        Collections.sort(incompleteScores);
        return incompleteScores.get(incompleteScores.size()/2);
    }

    private BigInteger getIncompleteLineScore(String line) {
//        System.out.println("getIncompleteScore line: " + line);
        BigInteger incompleteScore = BigInteger.valueOf(0);
        fillLineStack(line);
        while (lineStack.size() > 0) {
            String currChar = lineStack.pop();
            if (closeSet.contains(currChar)) {
                closingStack.push(currChar);
            } else {
                if (closingStack.size() > 0) {
                    String correctClosing = getClosing.get(currChar);
                    String topClosing = closingStack.pop();
                    if (!correctClosing.equals(topClosing)) {
//                        System.out.println("non matching score: " + currChar);
                        incompleteScore = updateIncompleteScore(currChar, incompleteScore);
                    }
                } else {
//                    System.out.println("empty close stack score: " + currChar);
                    incompleteScore = updateIncompleteScore(currChar, incompleteScore);
                }
            }
        }
        lineStack.clear();
        closingStack.clear();
//        System.out.println("getIncompleteScore line: " + line + " -> " + incompleteScore);
        return incompleteScore;
    }

    private BigInteger updateIncompleteScore(String currChar, BigInteger currentIncompleteScore) {
//        System.out.println("updateIncompleteScore start: " + currChar + " -> " + currentIncompleteScore);
        String correctClosing = getClosing.get(currChar);
        BigInteger product = BigInteger.valueOf(5)
            .multiply(currentIncompleteScore);
        return product.add(incompleteMap.get(correctClosing));
    }

    private void fillLineStack(String line) {
        String[] chars = line.split("");
        for (int charIdx = 0; charIdx < chars.length; charIdx++) {
            lineStack.push(chars[charIdx]);
        }
//        System.out.println("filled lineStack: " + Arrays.toString(lineStack.toArray()));
    }

    /**
     * Returns the syntax error score
     * @param filePath Path to input data file.
     * @return int the syntax error score
     */
    public int getSyntaxErrorScoreSum(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            int syntaxErrorScoreSum = 0;
            while ((line = br.readLine()) != null) {
//                System.out.println("LINE: " + line);
                syntaxErrorScoreSum += getSyntaxErrorScore(line);
            }
//            System.out.println("incomplete lines: " + Arrays.toString(incompleteLines.toArray()));
            return syntaxErrorScoreSum;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private int getSyntaxErrorScore(String line) {
//        System.out.println("getSyntaxErrorScore line: " + line);
        String[] chars = line.split("");
//        System.out.println("CHARS: " + Arrays.toString(chars));
        for (int charIdx = 0; charIdx < chars.length; charIdx++) {
            String currChar = chars[charIdx];
//            System.out.println("currChar: " + currChar);
            if (openSet.contains(currChar)) {
//                System.out.println("openSet has: " + currChar);
                stack.push(currChar);
            } else {
//                System.out.println("closeSet has: " + currChar);
                String correctOpen = getOpen.get(currChar);
//                System.out.println("open for " + currChar + ": " + correctOpen);
//                System.out.println("stack: " + Arrays.toString(stack.toArray()));
                String stackTop = stack.pop();
                if (!stackTop.equals(correctOpen)) {
                    return errorMap.get(currChar);
                }
            }
        }
//        System.out.println("incomplete line: " + line);
        incompleteLines.add(line);
        stack.clear();
        return 0;
    }
}
