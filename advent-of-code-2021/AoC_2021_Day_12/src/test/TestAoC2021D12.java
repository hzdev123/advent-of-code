package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.RiskCalculator;

public class TestAoC2021D12 {

    public static void main(String[] args) throws Exception {
        System.out.println("Testing advent of code 2021 Day 12");
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_12_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_12_data_task.txt";

        testDay121Example(EXAMPLE_FILE_PATH, rc);
        testDay121Task(TASK_FILE_PATH, rc);
        testDay122Example(EXAMPLE_FILE_PATH, rc);
        testDay122Task(TASK_FILE_PATH, rc);

        System.out.println("Testing advent of code 2021 Day 12 done");
    }

    @Test
    private static void testDay121Example(String filePath, RiskCalculator rc) {
        assertEquals(-1, rc.getSum(filePath));
    }

    @Test
    private static void testDay121Task(String filePath, RiskCalculator rc) {
        assertEquals(-1, rc.getSum(filePath));
    }

    @Test
    private static void testDay122Example(String filePath, RiskCalculator rc) {
        assertEquals(-1, rc.getBasinProduct(filePath));
    }

    @Test
    private static void testDay122Task(String filePath, RiskCalculator rc) {
        assertEquals(-1, rc.getBasinProduct(filePath));
    }
}
