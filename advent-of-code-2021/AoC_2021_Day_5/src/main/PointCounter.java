package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.lang.StringBuilder;

/**
 * Represents a point counter
 * @author Hoa Truong
 *
 */
public class PointCounter {
	
    /**
     * Counts the points along the lines
     * @param filePath Path to input data file.
     * @param onlyStraight  only consider straight lines
     * @return int number of points where lines overlap
     */
    public static int count(String filePath, boolean onlyStraight){
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            Map<String, Integer> points = new HashMap<String, Integer>();
            while ((line = br.readLine()) != null) {
                String[] lineSplit = line.split(" -> ");
                String[] startXY = lineSplit[0].split(",");
                String[] endXY = lineSplit[1].split(",");
                //Only straigh
                if (startXY[0].equals(endXY[0])
                    || startXY[1].equals(endXY[1])) {
                    handleStraight(startXY, endXY, points);
                } else if(!onlyStraight) {
                    handleDiagonal(startXY, endXY, points);
                }
            }
            return getNumberOfOverlappings(points);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static void handleDiagonal(String[] startXY, String[] endXY, Map<String, Integer> points) {
//        System.out.println("Diagonal");
//        System.out.println(
//            "(" + startXY[0] + "," + startXY[1] + ") -> (" + endXY[0] + "," + endXY[1] + ")");
        int fromY = Integer.parseInt(startXY[1]);
        int fromX = Integer.parseInt(startXY[0]);
        int endX = Integer.parseInt(endXY[0]);
        int endY = Integer.parseInt(endXY[1]);

        if (endX < fromX
            && endY < fromY) {
            //Clock direction: 1 -> 7
            for (int i = 0; i < fromX - endX + 1; i++) {
                updateMap(fromX - i, fromY - i, points);
            }
        } else if(endX > fromX
            && endY > fromY) {
            //Clock direction: 7 -> 1
            for (int i = 0; i < endX - fromX + 1; i++) {
                updateMap(fromX + i, fromY + i, points);
            }
        } else if(endX < fromX
            && endY > fromY) {
            //Clock direction: 5 -> 11
            for (int i = 0; i < fromX - endX + 1; i++) {
                updateMap(fromX - i, fromY + i, points);
            }
        } else if(endX > fromX
            && endY < fromY) {
            //Clock direction: 11 -> 5 TODO:FIX
            for (int i = 0; i < endX - fromX + 1; i++) {
                updateMap(fromX + i, fromY -i, points);
            }
        } else {
            System.out.println("Impossible");
        }
    }

    private static void handleStraight(String[] startXY, String[] endXY, Map<String, Integer> points) {
//        System.out.println("Straight");
//        System.out.println(
//            "(" + startXY[0] + "," + startXY[1] + ") -> (" + endXY[0] + "," + endXY[1] + ")");
        if (startXY[0].equals(endXY[0])) {
            //Increment Y
            int fromY = Integer.parseInt(startXY[1]);
            int endY = Integer.parseInt(endXY[1]);
            if (endY > fromY) {
                for (int i = fromY; i < endY + 1; i++) {
                    updateMap(startXY[0], Integer.toString(i), points);
                }
            } else {
                for (int i = endY; i < fromY + 1; i++) {
                    updateMap(startXY[0], Integer.toString(i), points);
                }
            }
        } else {
            //Increment X
            int fromX = Integer.parseInt(startXY[0]);
            int endX = Integer.parseInt(endXY[0]);
            if (endX > fromX) {
                for (int i = fromX; i < endX + 1; i++) {
                    updateMap(Integer.toString(i), startXY[1], points);
                }
            } else {
                for (int i = endX; i < fromX + 1; i++) {
                    updateMap(Integer.toString(i), startXY[1], points);
                }
            }
        }
    }

    private static void updateMap(int x, int y, Map<String, Integer> points) {
//        System.out.println("Update with (" + x + "," + y + ")");
        updateMap(Integer.toString(x), Integer.toString(y), points);
    }

    private static void updateMap(String x, String y, Map<String, Integer> points) {
//        System.out.println("Update with (" + x + "," + y + ")");
        String coordPair = x + "," + y;
        Object val = points.get(coordPair);
        if (val == null) {
            points.put(coordPair, 1);
        } else {
            int convVal = (Integer) val + 1;
            points.put(coordPair, convVal);
        }
    }

    private static int getNumberOfOverlappings(Map<String, Integer> points) {
        int largerThanOne = 0;
        for (String coordPair : points.keySet()) {
//            System.out.println(coordPair + " -> " + points.get(coordPair));
            if (points.get(coordPair) > 1) {
                largerThanOne++;
            }
        }
        return largerThanOne;
    }
}
