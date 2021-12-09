package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.NumberCounter;

public class TestAoC2021D10 {

    public static void main(String[] args) throws Exception {
        System.out.println("Testing advent of code 2021 Day 10");
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_10_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_10_data_task.txt";

        testDay101Example(EXAMPLE_FILE_PATH);
        testDay101Task(TASK_FILE_PATH);
        testDay102Example(EXAMPLE_FILE_PATH);
        testDay102Task(TASK_FILE_PATH);

        System.out.println("Testing advent of code 2021 Day 10 done");
    }

    @Test
    private static void testDay101Example(String filePath) {
        assertEquals(-1, NumberCounter.countUnique(filePath));
    }

    @Test
    private static void testDay101Task(String filePath) {
        assertEquals(-1, NumberCounter.countUnique(filePath));
    }

    @Test
    private static void testDay102Example(String filePath) {
        assertEquals(-1, NumberCounter.countOutputSum(filePath));
    }

    @Test
    private static void testDay102Task(String filePath) {
        assertEquals(-1, NumberCounter.countOutputSum(filePath));
    }
}
