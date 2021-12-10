package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.FuelCounter;

public class TestAoC2021D11 {

    public static void main(String[] args) throws Exception {
        System.out.println("Testing advent of code 2021 Day 11");
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_11_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_11_data_task.txt";

        testDay111Example(EXAMPLE_FILE_PATH);
        testDay111Task(TASK_FILE_PATH);
        testDay112Example(EXAMPLE_FILE_PATH);
        testDay112Task(TASK_FILE_PATH);

        System.out.println("Testing advent of code 2021 Day 11 done");
    }

    @Test
    private static void testDay111Example(String filePath) {
        assertEquals(-1, FuelCounter.getLowestCost(filePath, false));
    }

    @Test
    private static void testDay111Task(String filePath) {
        assertEquals(-1, FuelCounter.getLowestCost(filePath, false));
    }

    @Test
    private static void testDay112Example(String filePath) {
        assertEquals(-1, FuelCounter.getLowestCost(filePath, true));
    }

    @Test
    private static void testDay112Task(String filePath) {
        assertEquals(-1, FuelCounter.getLowestCost(filePath, true));
    }
}
