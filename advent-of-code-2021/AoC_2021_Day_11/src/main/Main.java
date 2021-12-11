package main;

/**
 * Main class to solve advent of code day 11 2021
 * @author Hoa Truong
 *
 */
public class Main {

    public static void main(String args[]) {
        String EXAMPLE_FILE_PATH = "data/aoc_2021_day_11_data_example.txt";
        String TASK_FILE_PATH = "data/aoc_2021_day_11_data_task.txt";

        FlashCounter fc = new FlashCounter();

//        System.out.println("Advent of code 2021 day 11-1 solved: "
//            + fc.getNbrOfFlashes(TASK_FILE_PATH));
        System.out.println("Advent of code 2021 day 11-2 solved: "
            + fc.getSynchronizedFlashIteration(TASK_FILE_PATH));
        System.exit(0);
    }
}
