package main;

/**
 * Main class to solve advent of code day 3 2021
 * @author Hoa Truong
 *
 */
public class Main {

    public static void main(String args[]) {
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_3_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_3_data_task.txt";

        System.out.println("Advent of code 2021 day 3-1 solved: "
            + RateProductCalculator.getPowerConsumption(TASK_FILE_PATH));

//      Current solution does not solve TASK_FILE_PATH. Only solves EXAMPLE_FILE_PATH
//        O2: 585
//        CO2: 3375
//        1974375 too low
        System.out.println("Advent of code 2021 day 3-2 solved: "
            + RateProductCalculator.getLifeSupport(TASK_FILE_PATH));
        System.exit(0);
    }
}
