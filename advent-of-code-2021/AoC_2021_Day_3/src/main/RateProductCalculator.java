package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
            List<String> digits = new ArrayList<String>();
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
            List<String> digits = new ArrayList<String>();
            int digitsLenght = 0;
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.split("");
                digitsLenght = lineSplit.length;
                for (int i = 0; i < digitsLenght; i++) {
                    digits.add(lineSplit[i]);
                }
            }
            List<String> digitsCopy = new ArrayList<String>();
            digitsCopy.addAll(digits);
            int O2 = getO2GeneratorRate(digits, digitsLenght);
            int CO2 = getCO2ScrubberRate(digitsCopy, digitsLenght);
            System.out.println("O2: " + O2);
            System.out.println("CO2: " + CO2);
            return O2 * CO2;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    //TODO: Once getO2GeneratorRate and getCO2ScrubberRate solves TASK_FILE_PATH,
    //      refactor into one method.
    private static int getO2GeneratorRate(List<String> digits, int sequenceSize) {
        List<Integer> zerosIndexInDigits = new ArrayList<Integer>();
        List<Integer> onesIndexInDigits = new ArrayList<Integer>();
        for (int currentBit = 0; currentBit < sequenceSize; currentBit++) {
            StringBuilder xc = new StringBuilder();
            for (int b = 0; b < digits.size(); b++) {
                xc.append(digits.get(b));
            }
            System.out.println("O2-New-Iteration------------------------------");
            System.out.println("Current bit position: " + (currentBit + 1) + "");
            System.out.println("Current binary sequence: " + xc.toString());
            System.out.println("Current digits size: " + digits.size());
            //Track indexes of ones and zero bits
            for (int j = currentBit; j < digits.size(); j += sequenceSize) {
                if (digits.get(j).equals("0")) {
                    zerosIndexInDigits.add(j);
                } else {
                    onesIndexInDigits.add(j);
                }
            }
            for (int b = 0; b < zerosIndexInDigits.size(); b++) {
                System.out.println("Zeros Idx: " + zerosIndexInDigits.get(b));
            }
            for (int b = 0; b < onesIndexInDigits.size(); b++) {
                System.out.println("Ones Idx: " + onesIndexInDigits.get(b));
            }
            //Only one sequence left
            if (digits.size() == sequenceSize) {
                StringBuilder sb = new StringBuilder();
                for (String digit : digits) {
                    sb.append(digit);
                }
                System.out.println("Return from one sequence: " + Integer.parseInt(sb.toString(), 2));
                return Integer.parseInt(sb.toString(), 2);
            } else if (digits.size() == 2 * sequenceSize) {
                //Choose sequence with one if only two sequences left
                StringBuilder sb = new StringBuilder();
                int startIndex = 0;
                //TODO: common for O2 and CO2 method, refactor
                //One in first or last sequence
                if (onesIndexInDigits.get(0) >= sequenceSize) {
                    startIndex = sequenceSize;
                }
                System.out.println("startIdx: " + startIndex);
                for (int seqIdx = startIndex; seqIdx < startIndex + sequenceSize; seqIdx++) {
                    sb.append(digits.get(seqIdx));
                }
                System.out.println("Return from two sequences: " + Integer.parseInt(sb.toString(), 2));
                return Integer.parseInt(sb.toString(), 2);
            }

            //TODO: common for O2 and CO2 method, > for CO2, z for O2
            //Remove minority sequence from list
            if (zerosIndexInDigits.size() < onesIndexInDigits.size()) {
                for (int a = zerosIndexInDigits.size() - 1; a > -1; a--) {
                    int zeroIdx = zerosIndexInDigits.get(a) - currentBit;
                    System.out.println("Removing zeroes Idx: " + zeroIdx );
                    for (int j = 0; j < sequenceSize; j++) {
                        System.out.println("Removing Zeros: " + digits.remove(zeroIdx));
                    }
                }
            } else {
                for (int a = onesIndexInDigits.size() - 1; a > -1; a--) {
                    int onesIdx = onesIndexInDigits.get(a) - currentBit;
                    System.out.println("Removing Ones Idx: " + onesIdx );
                    for (int j = 0; j < sequenceSize; j++) {
                        System.out.println("Removing Ones: " + digits.remove(onesIdx));
                    }
                }
            }
            zerosIndexInDigits.clear();
            onesIndexInDigits.clear();
        }
        return -1;
    }

    //TODO: Once getO2GeneratorRate and getCO2ScrubberRate solves TASK_FILE_PATH,
    //      refactor into one method.
    private static int getCO2ScrubberRate(List<String> digits, int sequenceSize) {
        List<Integer> zerosIndexInDigits = new ArrayList<Integer>();
        List<Integer> onesIndexInDigits = new ArrayList<Integer>();
        for (int currentBit = 0; currentBit < sequenceSize; currentBit++) {
            StringBuilder xc = new StringBuilder();
            for (int b = 0; b < digits.size(); b++) {
                xc.append(digits.get(b));
            }
            System.out.println("CO2-New-Iteration------------------------------");
            System.out.println("Current bit position: " + (currentBit + 1) + "");
            System.out.println("Current binary sequence: " + xc.toString());
            System.out.println("Current digits size: " + digits.size());
            //Track indexes of ones and zero bits
            for (int j = currentBit; j < digits.size(); j += sequenceSize) {
                if (digits.get(j).equals("0")) {
                    zerosIndexInDigits.add(j);
                } else {
                    onesIndexInDigits.add(j);
                }
            }
            for (int b = 0; b < zerosIndexInDigits.size(); b++) {
                System.out.println("Zeros Idx: " + zerosIndexInDigits.get(b));
            }
            for (int b = 0; b < onesIndexInDigits.size(); b++) {
                System.out.println("Ones Idx: " + onesIndexInDigits.get(b));
            }
            //Only one sequence left
            if (digits.size() == sequenceSize) {
                StringBuilder sb = new StringBuilder();
                for (String digit : digits) {
                    sb.append(digit);
                }
                System.out.println("Return from one sequence: " + Integer.parseInt(sb.toString(), 2));
                return Integer.parseInt(sb.toString(), 2);
            } else if (digits.size() == 2 * sequenceSize) {
                //Choose sequence with one if only two sequences left
                StringBuilder sb = new StringBuilder();
                int startIndex = 0;
                //One in first or last sequence
                //TODO: common for O2 and CO2 method, refactor
                if (zerosIndexInDigits.get(0) >= sequenceSize) {
                    startIndex = sequenceSize;
                }
                System.out.println("Start Idx: " + startIndex);
                for (int seqIdx = startIndex; seqIdx < startIndex + sequenceSize; seqIdx++) {
                    sb.append(digits.get(seqIdx));
                }
                System.out.println("Return from two sequences: " + Integer.parseInt(sb.toString(), 2));
                return Integer.parseInt(sb.toString(), 2);
            }
            //Remove majority sequence from list
            //TODO: common for O2 and CO2 method, > for CO2, z for O2
            if (zerosIndexInDigits.size() > onesIndexInDigits.size()) {
                for (int a = zerosIndexInDigits.size() - 1; a > -1; a--) {
                    int zeroIdx = zerosIndexInDigits.get(a) - currentBit;
                    System.out.println("Removing zeroes Idx: " + zeroIdx );
                    for (int j = 0; j < sequenceSize; j++) {
                        System.out.println("Removing Zeros: " + digits.remove(zeroIdx));
                    }
                }
            } else {
                for (int a = onesIndexInDigits.size() - 1; a > -1; a--) {
                    int onesIdx = onesIndexInDigits.get(a) - currentBit;
                    System.out.println("Removing ones Idx: " + onesIdx );
                    for (int j = 0; j < sequenceSize; j++) {
                        System.out.println("Removing Ones: " + digits.remove(onesIdx));
                    }
                }
            }
            zerosIndexInDigits.clear();
            onesIndexInDigits.clear();
        }
        return -1;
    }

    private static int getEpsilonGammaProduct(List<String> digits, int sequenceSize) {
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
