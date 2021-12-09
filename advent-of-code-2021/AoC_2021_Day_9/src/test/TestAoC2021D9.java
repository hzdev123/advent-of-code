package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.RiskCalculator;

public class TestAoC2021D9 {

    public static void main(String[] args) throws Exception {
        System.out.println("Testing advent of code 2021 Day 9");
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_9_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_9_data_task.txt";

        testDay91Example(EXAMPLE_FILE_PATH);
        testDay91Task(TASK_FILE_PATH);
        testDay92Example(EXAMPLE_FILE_PATH);
        testDay92Task(TASK_FILE_PATH);

        System.out.println("Testing advent of code 2021 Day 9 done");
    }

    @Test
    private static void testDay91Example(String filePath) {
        assertEquals(15, RiskCalculator.getSum(filePath));
    }

    @Test
    private static void testDay91Task(String filePath) {
        assertEquals(-1, RiskCalculator.getSum(filePath));
    }

    @Test
    private static void testDay92Example(String filePath) {
        assertEquals(-1, RiskCalculator.getSum(filePath));
    }

    @Test
    private static void testDay92Task(String filePath) {
        assertEquals(-1, RiskCalculator.getSum(filePath));
    }
}
