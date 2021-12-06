package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.FishCounter;

public class TestAoC2021D6 {

    public static void main(String[] args) throws Exception {
        System.out.println("Testing advent of code 2021 Day 6");
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_6_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_6_data_task.txt";

        testDay61Example(EXAMPLE_FILE_PATH);
        testDay61Task(TASK_FILE_PATH);
        testDay62Example(EXAMPLE_FILE_PATH);
        testDay62Task(TASK_FILE_PATH);

        System.out.println("Testing advent of code 2021 Day 6 done");
    }

    @Test
    private static void testDay61Example(String filePath) {
        assertEquals(5934, FishCounter.count(filePath, 80));
    }

    @Test
    private static void testDay61Task(String filePath) {
        assertEquals(352195, FishCounter.count(filePath, 80));
    }

    @Test
    private static void testDay62Example(String filePath) {
        assertEquals(12, FishCounter.count(filePath, 256));
    }

    @Test
    private static void testDay62Task(String filePath) {
        assertEquals(21698, FishCounter.count(filePath, 256));
    }
}
