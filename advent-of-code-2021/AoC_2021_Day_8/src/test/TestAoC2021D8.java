package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.FuelCounter;

public class TestAoC2021D8 {

    public static void main(String[] args) throws Exception {
        System.out.println("Testing advent of code 2021 Day 8");
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_8_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_8_data_task.txt";

        testDay81Example(EXAMPLE_FILE_PATH);
        testDay81Task(TASK_FILE_PATH);
        testDay82Example(EXAMPLE_FILE_PATH);
        testDay82Task(TASK_FILE_PATH);

        System.out.println("Testing advent of code 2021 Day 8 done");
    }

    @Test
    private static void testDay81Example(String filePath) {
        assertEquals(37, FuelCounter.getLowestCost(filePath, false));
    }

    @Test
    private static void testDay81Task(String filePath) {
        assertEquals(357353, FuelCounter.getLowestCost(filePath, false));
    }

    @Test
    private static void testDay82Example(String filePath) {
        assertEquals(168, FuelCounter.getLowestCost(filePath, true));
    }

    @Test
    private static void testDay82Task(String filePath) {
        assertEquals(104822130, FuelCounter.getLowestCost(filePath, true));
    }
}
