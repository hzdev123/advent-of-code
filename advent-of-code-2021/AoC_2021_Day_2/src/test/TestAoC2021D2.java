package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.PositionCalculator;

public class TestAoC2021D2 {

    public static void main(String[] args) throws Exception {
        System.out.println("Testing advent of code 2021 Day 2");
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_2_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_2_data_task.txt";

        testDay21Example(EXAMPLE_FILE_PATH);
        testDay21Task(TASK_FILE_PATH);
        testDay22Example(EXAMPLE_FILE_PATH);
        testDay22Task(TASK_FILE_PATH);

        System.out.println("Testing advent of code 2021 Day 2 done");
    }

    @Test
    private static void testDay21Example(String filePath) {
        assertEquals(150, PositionCalculator.getProduct(filePath));
    }

    @Test
    private static void testDay21Task(String filePath) {
        assertEquals(1698735, PositionCalculator.getProduct(filePath));
    }

    @Test
    private static void testDay22Example(String filePath) {
        assertEquals(900, PositionCalculator.getAimProduct(filePath));
    }

    @Test
    private static void testDay22Task(String filePath) {
        assertEquals(1594785890, PositionCalculator.getAimProduct(filePath));
    }
}
