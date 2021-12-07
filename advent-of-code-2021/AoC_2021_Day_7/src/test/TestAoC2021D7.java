package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.FuelCounter;

public class TestAoC2021D7 {

    public static void main(String[] args) throws Exception {
        System.out.println("Testing advent of code 2021 Day 7");
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_7_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_7_data_task.txt";

        testDay71Example(EXAMPLE_FILE_PATH);
        testDay71Task(TASK_FILE_PATH);
        testDay72Example(EXAMPLE_FILE_PATH);
        testDay72Task(TASK_FILE_PATH);

        System.out.println("Testing advent of code 2021 Day 7 done");
    }

    @Test
    private static void testDay71Example(String filePath) {
        assertEquals(37, FuelCounter.getLowestCost(filePath));
    }

    @Test
    private static void testDay71Task(String filePath) {
        assertEquals(-1, FuelCounter.getLowestCost(filePath));
    }

    @Test
    private static void testDay72Example(String filePath) {
        assertEquals(-1, FuelCounter.getLowestCost(filePath));
    }

    @Test
    private static void testDay72Task(String filePath) {
        assertEquals(-1, FuelCounter.getLowestCost(filePath));
    }
}
