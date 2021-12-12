package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.NumberCounter;

public class TestAoC2021D13 {

    public static void main(String[] args) throws Exception {
        System.out.println("Testing advent of code 2021 Day 13");
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_13_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_13_data_task.txt";

        testDay131Example(EXAMPLE_FILE_PATH);
        testDay131Task(TASK_FILE_PATH);
        testDay132Example(EXAMPLE_FILE_PATH);
        testDay132Task(TASK_FILE_PATH);

        System.out.println("Testing advent of code 2021 Day 13 done");
    }

    @Test
    private static void testDay131Example(String filePath) {
        assertEquals(-1, NumberCounter.countUnique(filePath));
    }

    @Test
    private static void testDay131Task(String filePath) {
        assertEquals(-1, NumberCounter.countUnique(filePath));
    }

    @Test
    private static void testDay132Example(String filePath) {
        assertEquals(-1, NumberCounter.countOutputSum(filePath));
    }

    @Test
    private static void testDay132Task(String filePath) {
        assertEquals(-1, NumberCounter.countOutputSum(filePath));
    }
}
