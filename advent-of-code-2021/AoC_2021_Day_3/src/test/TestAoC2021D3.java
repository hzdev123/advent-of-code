package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.RateProductCalculator;

public class TestAoC2021D3 {

    public static void main(String[] args) throws Exception {
        System.out.println("Testing advent of code 2021 Day 3");
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_3_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_3_data_task.txt";

        testDay31Example(EXAMPLE_FILE_PATH);
        testDay31Task(TASK_FILE_PATH);
//        testDay32Example(EXAMPLE_FILE_PATH);
//        testDay32Task(TASK_FILE_PATH);

        System.out.println("Testing advent of code 2021 Day 3 done");
    }

    @Test
    private static void testDay31Example(String filePath) {
        assertEquals(198, RateProductCalculator.getRateProduct(filePath));
    }

    @Test
    private static void testDay31Task(String filePath) {
        assertEquals(2583164, RateProductCalculator.getRateProduct(filePath));
    }

//    @Test
//    private static void testDay32Example(String filePath) {
//        assertEquals(955, RateProductCalculator.getRateProduct(filePath));
//    }
//
//    @Test
//    private static void testDay32Task(String filePath) {
//        assertEquals(955, RateProductCalculator.getRateProduct(filePath));
//    }
}
