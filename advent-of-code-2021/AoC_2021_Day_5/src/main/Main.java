package main;

/**
 * Main class to solve advent of code day 5 2021
 * @author Hoa Truong
 *
 */
public class Main {

    public static void main(String args[]) {
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_5_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_5_data_task.txt";

        System.out.println("Advent of code 2021 day 5-1 solved: "
            + PointCounter.count(TASK_FILE_PATH, true));
        System.out.println("Advent of code 2021 day 5-2 solved: "
            + PointCounter.count(TASK_FILE_PATH, false));
        System.exit(0);
    }
}
