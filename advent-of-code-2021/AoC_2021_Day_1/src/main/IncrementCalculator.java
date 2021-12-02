package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Calculator which calculates integer increments
 * @author Hoa Truong
 *
 */
public class IncrementCalculator {
		
	/**
	 * Counts the number of times the current integer is larger than the previous
	 * @param filePath File path containing a series of integers.
	 * @return the number of times current integer is larger than previous
	 */
	public static int countEvery(String filePath) {
        int nbr_of_increase = 0;
    	try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
    	    String line = "";
    	    int prev = -1;
    	    while ((line = br.readLine()) != null) {
        	    int curr = Integer.parseInt(line.trim());
        	    if (prev != -1
        	    	&& curr > prev) {
        	    	nbr_of_increase++;
        	    }
        	    prev = curr;
    	    }
    	} catch (FileNotFoundException e) {
    		System.err.println("File not found: " + filePath);
    		e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return nbr_of_increase;		
	}
}
