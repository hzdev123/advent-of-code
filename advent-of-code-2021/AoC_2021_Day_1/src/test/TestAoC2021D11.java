package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.IncrementCalculator;

public class TestAoC2021D11 {

	public static void main(String[] args) throws Exception {
		System.out.println("Testing advent of code 2021 Day 1");
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_1_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_1_data_task.txt";

		testDay11Example(EXAMPLE_FILE_PATH);
		testDay11Task(TASK_FILE_PATH);
		
		System.out.println("Testing advent of code 2021 Day 1 done");
	}
	
	@Test
	private static void testDay11Example(String filePath) {
		assertEquals(7, IncrementCalculator.countEvery(filePath));
	}
	
	@Test
	private static void testDay11Task(String filePath) {
		assertEquals(1709, IncrementCalculator.countEvery(filePath));
	}
}
