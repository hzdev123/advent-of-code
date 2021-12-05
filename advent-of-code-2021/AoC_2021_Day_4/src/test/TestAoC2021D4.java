package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

import main.BingoGame;

public class TestAoC2021D4 {

    public static void main(String[] args) throws Exception {
        System.out.println("Testing advent of code 2021 Day 4");
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_4_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_4_data_task.txt";

        testDay41Example(EXAMPLE_FILE_PATH);
        testDay41Task(TASK_FILE_PATH);
        testDay42Example(EXAMPLE_FILE_PATH);
        testDay42Task(TASK_FILE_PATH);

        System.out.println("Testing advent of code 2021 Day 4 done");
    }

    @Test
    private static void testDay41Example(String filePath) {
        assertEquals(4512, BingoGame.play(filePath));
    }

    @Test
    private static void testDay41Task(String filePath) {
        assertEquals(6592, BingoGame.play(filePath));
    }

    @Test
    private static void testDay42Example(String filePath) {
        assertEquals(4512, BingoGame.play(filePath));
    }

    @Test
    private static void testDay42Task(String filePath) {
        assertEquals(6592, BingoGame.play(filePath));
    }
}
