package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.FlashCounter;

public class TestAoC2021D11 {

    public static void main(String[] args) throws Exception {
        System.out.println("Testing advent of code 2021 Day 11");
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_11_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_11_data_task.txt";

        FlashCounter fc = new FlashCounter();

        testDay111Example(EXAMPLE_FILE_PATH, fc);
        fc.reset();
        testDay111Task(TASK_FILE_PATH, fc);
        fc.reset();
        testDay112Example(EXAMPLE_FILE_PATH, fc);
        fc.reset();
        testDay112Task(TASK_FILE_PATH, fc);

        System.out.println("Testing advent of code 2021 Day 11 done");
    }

    @Test
    private static void testDay111Example(String filePath, FlashCounter fc) {
        assertEquals(1656, fc.getNbrOfFlashes((filePath)));
    }

    @Test
    private static void testDay111Task(String filePath, FlashCounter fc) {
        assertEquals(1571, fc.getNbrOfFlashes((filePath)));
    }

    @Test
    private static void testDay112Example(String filePath, FlashCounter fc) {
        assertEquals(-1, fc.getNbrOfFlashes((filePath)));
    }

    @Test
    private static void testDay112Task(String filePath, FlashCounter fc) {
        assertEquals(-1, fc.getNbrOfFlashes((filePath)));
    }
}
