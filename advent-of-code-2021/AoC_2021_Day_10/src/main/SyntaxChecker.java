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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * Represents a syntax checker
 * @author Hoa Truong
 *
 */
public class SyntaxChecker {
    private Stack<String> stack = null;
	private Set<String> openSet = null;
    private Set<String> closeSet = null;
    private Map<String, Integer> errorScore = null;
    private Map<String, String> getOpen = null;

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
        errorScore = new HashMap<String, Integer>();
        errorScore.put(">", 25137);
        errorScore.put("}", 1197);
        errorScore.put("]", 57);
        errorScore.put(")", 3);
        System.out.println("errorScore: " + Arrays.toString(errorScore.entrySet().toArray()));
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
        for (int i = 0; i < chars.length; i++) {
            String currChar = chars[i];
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
                    return errorScore.get(currChar);
                }
            }
        }
        stack.setSize(0);
        return 0;
    }
}
