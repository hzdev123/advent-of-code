package main;

/**
 * Main class to solve advent of code day 6 2021
 * @author Hoa Truong
 *
 */
public class Main {

    public static void main(String args[]) {
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_6_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_6_data_task.txt";

        System.out.println("Advent of code 2021 day 6-1 solved: "
            + FishCounter.count(TASK_FILE_PATH, 80));
        System.out.println("Advent of code 2021 day 6-2 solved: "
            + FishCounter.count(EXAMPLE_FILE_PATH, 256));
        System.exit(0);
    }
}
