package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Represents a number counter
 * @author Hoa Truong
 *
 */
public class NumberCounter {

    /**
     * Count the occurrence of numbers with unique number of segments
     * @param filePath Path to input data file.
     * @return int numbers with unique number of segments
     */
    public static int countUnique(String filePath) {
        ArrayList<String[]> fourDigits = getFourDigits(filePath);
        return getUniqueSegmentDigitAmount(fourDigits);
    }

    /**
     * Count the sum of four digits output
     * @param filePath Path to input data file.
     * @return int the sum of four digits output
     */
    public static int countOutputSum(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            int outputSum = 0;
            while ((line = br.readLine()) != null) {
//                System.out.println("LINE: " + line);
                String[] tenDigits = line.split("\\|")[0].trim().split(" ");
                String[] fourDigits = line.split("\\|")[1].trim().split(" ");
                outputSum += getDecryptedNumber(tenDigits, fourDigits);
            }
            return outputSum;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static int getDecryptedNumber(String[] tenDigits, String[] fourDigits) {
//        System.out.println("     " + Arrays.toString(tenDigits) + " | " + Arrays.toString(fourDigits));
//        1 - lenght 2
//        7 - lenght 3
//        4 - lenght 4
//        8 - lenght 7
//    length = 5
//        2 - has 3-acd  common with 3 + 4
//        3 - has 2-cf   common with 1
//        5 - has 4-abdf common with 3 + 4
//    length = 6
//        0 - has 3-acf   common with 7
//        6 - has 2-af    common with 7
//        9 - has 5-abcdf common with 7 + 4
        ArrayList<TreeSet<Character>> tenDgts = getTenDigitsList(tenDigits);
        ArrayList<TreeSet<Character>> fourDgts = getFourDigitsList(fourDigits);
//        System.out.println("     " + Arrays.toString(tenDgts.toArray()) + " | " + Arrays.toString(fourDgts.toArray()));
        int outputSum = 0;
        int nbrOfDecrypted = 0;
        HashMap<Integer, TreeSet<Character>> digitMap = new HashMap<Integer, TreeSet<Character>>();
        for (int tenListIdx = 0; tenListIdx < 10; tenListIdx++) {
            if (nbrOfDecrypted == 4) {
                break;
            }
            TreeSet<Character> encryptedSet = tenDgts.get(tenListIdx);
            //Determine digit
            if (encryptedSet.size() == 2) {
                digitMap.put(1, encryptedSet);
                outputSum = accumulateIfContains(encryptedSet, fourDgts, 1, outputSum);
            } else if (encryptedSet.size() == 3) {
                digitMap.put(7, encryptedSet);
                outputSum = accumulateIfContains(encryptedSet, fourDgts, 7, outputSum);
            } else if (encryptedSet.size() == 4) {
                digitMap.put(4, encryptedSet);
                outputSum = accumulateIfContains(encryptedSet, fourDgts, 4, outputSum);
            } else if (encryptedSet.size() == 7) {
                digitMap.put(8, encryptedSet);
                outputSum = accumulateIfContains(encryptedSet, fourDgts, 8, outputSum);
            } else if (encryptedSet.size() == 5) {
                TreeSet<Character> interSeven = new TreeSet<Character>();
                interSeven.addAll(digitMap.get(7));
                interSeven.retainAll(encryptedSet);
                if (interSeven.size() == 3) {
                    digitMap.put(3, encryptedSet);
                    outputSum = accumulateIfContains(encryptedSet, fourDgts, 3, outputSum);
                } else if(interSeven.size() == 2) {
                    TreeSet<Character> interSevenFour = new TreeSet<Character>();
                    interSevenFour.addAll(digitMap.get(7));
                    interSevenFour.addAll(digitMap.get(4));
                    interSevenFour.retainAll(encryptedSet);
                    if (interSevenFour.size() == 3) {
                        digitMap.put(2, encryptedSet);
                        outputSum = accumulateIfContains(encryptedSet, fourDgts, 2, outputSum);
                    } else if(interSevenFour.size() == 4) {
                        digitMap.put(5, encryptedSet);
                        outputSum = accumulateIfContains(encryptedSet, fourDgts, 5, outputSum);
                    } else {
                        System.out.println("Len 5 Impossible: intersectionSize: "
                            + interSevenFour.size());
                    }
                }
            } else if (encryptedSet.size() == 6) {
                TreeSet<Character> sevenSet = digitMap.get(7);
                TreeSet<Character> interSeven = new TreeSet<Character>();
                interSeven.addAll(digitMap.get(7));
                interSeven.retainAll(encryptedSet);
                if (interSeven.size() == 2) {
                    digitMap.put(6, encryptedSet);
                    outputSum = accumulateIfContains(encryptedSet, fourDgts, 6, outputSum);
                } else if(interSeven.size() == 3) {
                    TreeSet<Character> interSevenFour = new TreeSet<Character>();
                    interSevenFour.addAll(digitMap.get(7));
                    interSevenFour.addAll(digitMap.get(4));
                    interSevenFour.retainAll(encryptedSet);
                    if (interSevenFour.size() == 4) {
                        digitMap.put(0, encryptedSet);
                        outputSum = accumulateIfContains(encryptedSet, fourDgts, 0, outputSum);
                    } else if(interSevenFour.size() == 5) {
                        digitMap.put(9, encryptedSet);
                        outputSum = accumulateIfContains(encryptedSet, fourDgts, 9, outputSum);
                    } else {
                        System.out.println("Len 6 Impossible: intersectionSize: "
                            + interSevenFour.size());
                    }
                }
            } else {
                System.out.println("Outer Impossible: encryptedDigit length: "
                    + encryptedSet.size());
            }

        }
//        System.out.println("digitMap: " + Arrays.toString(digitMap.entrySet().toArray()));
//        System.out.println("outputSum: " + outputSum);
        return outputSum;
    }

    private static int accumulateIfContains(TreeSet<Character> encryptedSet, ArrayList<TreeSet<Character>> fourDgts, int decryptedNumber, int outputSum) {
        for (int fourDigitIdx = 0; fourDigitIdx < 4; fourDigitIdx++) {
            if (fourDgts.get(fourDigitIdx).equals(encryptedSet)) {
                outputSum += decryptedNumber * (int) Math.pow(10, 3 - fourDigitIdx);
            }
        }
        return outputSum;
    }

    private static TreeSet<Character> toSet(String digit) {
        TreeSet<Character> set = new TreeSet<Character>();
        for(char c : digit.toCharArray()) {
            set.add(c);
        }
        return set;
    }

    private static ArrayList<TreeSet<Character>> getTenDigitsList(String[] tenDigits) {
        ArrayList<String> tenDgts = new ArrayList<String>();
        Collections.addAll(tenDgts, tenDigits);
        Collections.sort(tenDgts, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        ArrayList<TreeSet<Character>> tenSetDgts = new ArrayList<TreeSet<Character>>();
        for (int i = 0; i < tenDgts.size(); i++) {
            tenSetDgts.add(toSet(tenDgts.get(i)));
        }
        return tenSetDgts;
    }

    private static ArrayList<TreeSet<Character>> getFourDigitsList(String[] fourDigits) {
        ArrayList<TreeSet<Character>> fourDgts = new ArrayList<TreeSet<Character>>();
        for (int i = 0; i < fourDigits.length; i++) {
            TreeSet<Character> fourDigitSet = toSet(fourDigits[i]);
            fourDgts.add(fourDigitSet);
        }
        return fourDgts;
    }

    private static ArrayList<String[]> getFourDigits(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            HashMap<Integer, Integer> positionMap = null;
            ArrayList<String[]> fourDigits = new ArrayList<String[]>();
            while ((line = br.readLine()) != null) {
//                System.out.println("LINE: " + line);
                String[] fourDigit = line.split("\\|")[1].trim().split(" ");
                fourDigits.add(fourDigit);
            }
            return fourDigits;
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int getUniqueSegmentDigitAmount(ArrayList<String[]> fourDigits) {
        int uniqueSum = 0;
        for (int listIdx = 0; listIdx < fourDigits.size(); listIdx++) {
            String[] fourDigit = fourDigits.get(listIdx);
            for (int digitIdx = 0; digitIdx < 4; digitIdx++) {
                if (uniqueSegmentSize(fourDigit[digitIdx].length())) {
                    uniqueSum++;
                }
            }
        }
        return uniqueSum;
    }

    private static boolean uniqueSegmentSize(int segmentSize) {
        return segmentSize == 2
            || segmentSize == 3
            || segmentSize == 4
            || segmentSize == 7;
    }
}
