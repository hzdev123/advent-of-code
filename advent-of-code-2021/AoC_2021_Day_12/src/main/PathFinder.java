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
    private Map<String, List<String>> neighboursMap = null;
    private List<String> visitedNodes = null;
    private List<String> paths = null;

    public PathFinder() {
        neighboursMap = new HashMap<String, List<String>>();
        visitedNodes = new ArrayList<String>();
        paths = new ArrayList<String>();
    }

    /**
     * Gives the number of distinct paths
     * @param filePath Path to input data file.
     * @return int the number of distinct paths
     */
    public int getNbrOfPaths(String filePath) {
        loadNeighboursMap(filePath);
        printNeighboursMap();
        findPaths("start");
        return -1;
    }

    private void findPaths(String node) {
        System.out.println("node: " + node);
        int i = 0;
        for (String neighbour : neighboursMap.get(node)) {
            i++;
            System.out.println("    neighbour[" + i + "]: " + neighbour);
            if (visitedNodes.contains(neighbour)) {
	            visitedNodes.add(neighbour);
	            findPaths(neighbour);
            }
        }
    }
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
