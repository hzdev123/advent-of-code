package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.PointCounter;

public class TestAoC2021D5 {

    public static void main(String[] args) throws Exception {
        System.out.println("Testing advent of code 2021 Day 5");
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_5_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_5_data_task.txt";

        testDay51Example(EXAMPLE_FILE_PATH);
        testDay51Task(TASK_FILE_PATH);
        testDay52Example(EXAMPLE_FILE_PATH);
        testDay52Task(TASK_FILE_PATH);

        System.out.println("Testing advent of code 2021 Day 5 done");
    }

    @Test
    private static void testDay51Example(String filePath) {
        assertEquals(5, PointCounter.count(filePath, true));
    }

    @Test
    private static void testDay51Task(String filePath) {
        assertEquals(5294, PointCounter.count(filePath, true));
    }

    @Test
    private static void testDay52Example(String filePath) {
        assertEquals(12, PointCounter.count(filePath, false));
    }

    @Test
    private static void testDay52Task(String filePath) {
        assertEquals(21698, PointCounter.count(filePath, false));
    }
}
