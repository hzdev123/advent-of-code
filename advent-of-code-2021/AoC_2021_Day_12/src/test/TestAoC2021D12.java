package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.PathFinder;

public class TestAoC2021D12 {

    public static void main(String[] args) throws Exception {
        System.out.println("Testing advent of code 2021 Day 12");
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_12_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_12_data_task.txt";

        PathFinder pf = new PathFinder();

        testDay121Example(EXAMPLE_FILE_PATH, pf);
        testDay121Task(TASK_FILE_PATH, pf);
        testDay122Example(EXAMPLE_FILE_PATH, pf);
        testDay122Task(TASK_FILE_PATH, pf);

        System.out.println("Testing advent of code 2021 Day 12 done");
    }

    @Test
    private static void testDay121Example(String filePath, RiskCalculator rc) {
        assertEquals(10, pf.getNbrOfPaths(filePath));
    }

    @Test
    private static void testDay121Task(String filePath, RiskCalculator rc) {
        assertEquals(-1, pf.getNbrOfPaths(filePath));
    }

    @Test
    private static void testDay122Example(String filePath, RiskCalculator rc) {
        assertEquals(-1, pf.getNbrOfPaths(filePath));
    }

    @Test
    private static void testDay122Task(String filePath, RiskCalculator rc) {
        assertEquals(-1, pf.getNbrOfPaths(filePath));
    }
}
