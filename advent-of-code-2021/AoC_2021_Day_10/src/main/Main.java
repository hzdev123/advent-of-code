package main;

/**
 * Main class to solve advent of code day 10 2021
 * @author Hoa Truong
 *
 */
public class Main {

    public static void main(String args[]) {
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_10_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_10_data_task.txt";

        SyntaxChecker sc = new SyntaxChecker();

        System.out.println("Advent of code 2021 day 10-1 solved: "
            + sc.getSyntaxErrorScoreSum(EXAMPLE_FILE_PATH));
        System.out.println("Advent of code 2021 day 10-2 solved: "
            + sc.getIncompletesMiddleScore(EXAMPLE_FILE_PATH));
        System.exit(0);
    }
}
