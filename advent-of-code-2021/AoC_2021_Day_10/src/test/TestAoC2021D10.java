package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import java.math.BigInteger;

import main.SyntaxChecker;

public class TestAoC2021D10 {

    public static void main(String[] args) throws Exception {
        System.out.println("Testing advent of code 2021 Day 10");
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_10_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_10_data_task.txt";

        SyntaxChecker sc1 = new SyntaxChecker();
        SyntaxChecker sc2 = new SyntaxChecker();
        SyntaxChecker sc3 = new SyntaxChecker();

        testDay101Example(EXAMPLE_FILE_PATH, sc1);
        testDay101Task(TASK_FILE_PATH, sc1);
        testDay102Example(EXAMPLE_FILE_PATH, sc2);
        testDay102Task(TASK_FILE_PATH, sc3);

        System.out.println("Testing advent of code 2021 Day 10 done");
    }

    @Test
    private static void testDay101Example(String filePath, SyntaxChecker sc) {
        assertEquals(26397, sc.getSyntaxErrorScoreSum(filePath));
    }

    @Test
    private static void testDay101Task(String filePath, SyntaxChecker sc) {
        assertEquals(319233, sc.getSyntaxErrorScoreSum(filePath));
    }

    @Test
    private static void testDay102Example(String filePath, SyntaxChecker sc) {
        assertEquals(26397, sc.getSyntaxErrorScoreSum(filePath));
        assertEquals(BigInteger.valueOf(288957), sc.getIncompletesMiddleScore(filePath));
    }

    @Test
    private static void testDay102Task(String filePath, SyntaxChecker sc) {
        assertEquals(319233, sc.getSyntaxErrorScoreSum(filePath));
        assertEquals(BigInteger.valueOf(1118976874), sc.getIncompletesMiddleScore(filePath));
    }
}
