package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.FlashCounter;

public class TestAoC2021D11 {

    public static void main(String[] args) throws Exception {
        System.out.println("Testing advent of code 2021 Day 11");
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_11_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_11_data_task.txt";

        int day_11_first_half_steps = 100;

        testDay111Example(EXAMPLE_FILE_PATH, day_11_first_half_steps);
        testDay111Task(TASK_FILE_PATH, day_11_first_half_steps);
        testDay112Example(EXAMPLE_FILE_PATH, day_11_first_half_steps);
        testDay112Task(TASK_FILE_PATH, day_11_first_half_steps);

        System.out.println("Testing advent of code 2021 Day 11 done");
    }

    @Test
    private static void testDay111Example(String filePath, int n) {
        assertEquals(-1, FlashCounter.getNbrOfFlashes((filePath, n));
    }

    @Test
    private static void testDay111Task(String filePath, int n) {
        assertEquals(-1, FlashCounter.getNbrOfFlashes((filePath, n));
    }

    @Test
    private static void testDay112Example(String filePath, int n) {
        assertEquals(-1, FlashCounter.getNbrOfFlashes((filePath, n));
    }

    @Test
    private static void testDay112Task(String filePath, int n) {
        assertEquals(-1, FlashCounter.getNbrOfFlashes((filePath, n));
    }
}
