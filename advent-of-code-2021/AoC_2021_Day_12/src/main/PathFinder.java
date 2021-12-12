package main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;

/**
 * Represents a path finder
 * @author Hoa Truong
 *
 */
public class PathFinder {
    Map<String, List<String>> neighboursMap = null;

    public PathFinder() {
        neighboursMap = new HashMap<String, List<String>>();
    }
//	HaashMap
//		K -> V: nodes -> neighbours
//
//		List<Path> distinctPaths
//		
//		List<VisiteedNodes>
//		string currentNode
//		string previousNode
//	
//	    void visitNeighbour(currNode)
//	    	getNeighBours
//	    	for (neighbour : neighbours)
//	        if (!neighbour.isVisited
//	        	|| neighbour is Capital) {
//	        	visitNeighbour
//	        } else {
//	        	createPath
//	        	if (!path.alreadyExplored) {
//	        		paths.add(path)
//	        	}
//	        		
//	        }	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    /**
     * Gives the product of the three largest basin size
     * @param filePath Path to input data file.
     * @return int the product of the three largest basin size
     */
    public int getNbrOfPaths(String filePath) {
    	loadNeighboursMap(filePath);
        printNeighboursMap();

//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            //Setup the indata
//            String line = "";
//            while ((line = br.readLine()) != null) {
//                System.out.println("LINE: " + line);
//                String[] split = line.split("-");
//                String node = split[0];
//                String neighbour = split[1];
//                if (neighboursMap.containsKey(node)) {
//                    List<String> neighbours = neighboursMap.get(node);
//                    neighbours.add(neighbour);
//                    neighboursMap.put(node, neighbours);
//                } else {
//                    List<String> neighbours = new ArrayList<String>();
//                    neighbours.add(neighbour);
//                    neighboursMap.put(node, neighbours);
//                }
//            }
//            printNeighboursMap();
//            return -1;
//        } catch (FileNotFoundException e) {
//            System.err.println("File not found: " + filePath);
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return -1;
    }

    private void loadNeighboursMap(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                System.out.println("LINE: " + line);
                String[] split = line.split("-");
                String node = split[0];
                String neighbour = split[1];
                loadNeighbour(node, neighbour);
                loadNeighbour(neighbour, node);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadNeighbour(String node, String neighbour) {
        if (neighboursMap.containsKey(node)) {
            List<String> neighbours = neighboursMap.get(node);
            neighbours.add(neighbour);
            neighboursMap.put(node, neighbours);
        } else {
            List<String> neighbours = new ArrayList<String>();
            neighbours.add(neighbour);
            neighboursMap.put(node, neighbours);
        }
    }

    private void printNeighboursMap() {
        for (String node : neighboursMap.keySet()) {
            System.out.println("    " + node + " -> " + Arrays.toString(neighboursMap.get(node).toArray()));
        }
    }
}
