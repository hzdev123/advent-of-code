package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.FishCounter;

public class TestAoC2021D7 {

    public static void main(String[] args) throws Exception {
        System.out.println("Testing advent of code 2021 Day 7");
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_7_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_7_data_task.txt";

        testDay61Example(EXAMPLE_FILE_PATH);
        testDay61Task(TASK_FILE_PATH);
        testDay62Example(EXAMPLE_FILE_PATH);
        testDay62Task(TASK_FILE_PATH);

        System.out.println("Testing advent of code 2021 Day 7 done");
    }

    @Test
    private static void testDay71Example(String filePath) {
        assertEquals(BigInteger.valueOf(5934), FishCounter.count(filePath, 80));
    }

    @Test
    private static void testDay71Task(String filePath) {
        assertEquals(BigInteger.valueOf(352195), FishCounter.count(filePath, 80));
    }

    @Test
    private static void testDay72Example(String filePath) {
        assertEquals(BigInteger.valueOf(26984457539L), FishCounter.count(filePath, 256));
    }

    @Test
    private static void testDay72Task(String filePath) {
        assertEquals(BigInteger.valueOf(1600306001288L), FishCounter.count(filePath, 256));
    }
}
