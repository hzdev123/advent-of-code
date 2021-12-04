package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.StringBuilder;

/**
 * Calculates epsilon and gamma rates product
 * @author Hoa Truong
 *
 */
public class RateProductCalculator {
	
    /**
     * Get the product of the gamma and epsilon rate.
     * @param filePath Path to data file.
     * @return the product of the gamma and epsilon rate.
     */
    public static int getPowerConsumption(String filePath){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            ArrayList<String> digits = new ArrayList<String>();
            int digitsLenght = 0;
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.split("");
                digitsLenght = lineSplit.length;
                for (int i = 0; i < digitsLenght; i++) {
                    digits.add(lineSplit[i]);
                }
            }
            return getEpsilonGammaProduct(digits, digitsLenght);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Get the product of the O2 and CO2 rates.
     * @param filePath Path to data file.
     * @return the product of the O2 and CO2 rates.
     */
    public static int getLifeSupport(String filePath){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            ArrayList<String> digits = new ArrayList<String>();
            int digitsLenght = 0;
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.split("");
                digitsLenght = lineSplit.length;
                for (int i = 0; i < digitsLenght; i++) {
                    digits.add(lineSplit[i]);
                }
            }
            return getO2CO2Product(digits, digitsLenght);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static int getO2CO2Product(ArrayList<String> digits, int sequenceSize) {
        return 0;
    }

    private static int getEpsilonGammaProduct(ArrayList<String> digits, int sequenceSize) {
        StringBuilder gammaRate = new StringBuilder();
        for (int i = 0; i < sequenceSize; i++) {
            int zero = 0;
            int one = 0;
            for (int j = i; j < digits.size(); j += sequenceSize) {
                if (digits.get(j).equals("0")) {
                    zero++;
                } else {
                    one++;
                }
            }
            if (zero > one) {
                gammaRate.append("0");
            } else {
                gammaRate.append("1");
            }
        }
        String epsilonRate = gammaRate.toString()
            .replace("0", "x")
            .replace("1", "0")
            .replace("x", "1");
        return Integer.parseInt(gammaRate.toString(), 2)
            * Integer.parseInt(epsilonRate.toString(), 2);
    }
}