package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.SyntaxChecker;

public class TestAoC2021D10 {

    public static void main(String[] args) throws Exception {
        System.out.println("Testing advent of code 2021 Day 10");
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_10_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_10_data_task.txt";

        SyntaxChecker sc = new SyntaxChecker();

        testDay101Example(EXAMPLE_FILE_PATH, sc);
        testDay101Task(TASK_FILE_PATH, sc);
        testDay102Example(EXAMPLE_FILE_PATH, sc);
        testDay102Task(TASK_FILE_PATH, sc);

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
        assertEquals(-1, sc.getSyntaxErrorScoreSum(filePath));
    }

    @Test
    private static void testDay102Task(String filePath, SyntaxChecker sc) {
        assertEquals(-1, sc.getSyntaxErrorScoreSum(filePath));
    }
}
