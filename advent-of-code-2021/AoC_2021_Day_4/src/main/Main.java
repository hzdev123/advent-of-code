package main;

/**
 * Main class to solve advent of code day 4 2021
 * @author Hoa Truong
 *
 */
public class Main {

    public static void main(String args[]) {
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_4_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_4_data_task.txt";

        System.out.println("Advent of code 2021 day 4-1 solved: "
            + BingoGame.play(TASK_FILE_PATH));
        System.exit(0);
    }
}
