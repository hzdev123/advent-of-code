package main;

/**
 * Main class to solve advent of code day 13 2021
 * @author Hoa Truong
 *
 */
public class Main {

    public static void main(String args[]) {
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_13_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_13_data_task.txt";

        System.out.println("Advent of code 2021 day 13-1 solved: "
            + NumberCounter.countUnique(TASK_FILE_PATH));
        System.out.println("Advent of code 2021 day 13-2 solved: "
            + NumberCounter.countOutputSum(TASK_FILE_PATH));
        System.exit(0);
    }
}
