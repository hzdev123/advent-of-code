package main;

/**
 * Main class to solve advent of code day 12 2021
 * @author Hoa Truong
 *
 */
public class Main {

    public static void main(String args[]) {
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_12_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_12_data_task.txt";

        PathFinder pf = new PathFinder();

        System.out.println("Advent of code 2021 day 12-1 solved: "
            + pf.getNbrOfPaths(EXAMPLE_FILE_PATH));
//        System.out.println("Advent of code 2021 day 12-2 solved: "
//            + pf.getNbrOfPaths(TASK_FILE_PATH));
        System.exit(0);
    }
}
