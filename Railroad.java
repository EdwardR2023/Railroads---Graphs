/* Edward Rosales
   Dr. Steinberg
   COP3503 Fall 2023
   Programming Assignment 5
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Railroad {
    private int numTracks;// i didn't use this since it didn't seem necessary because of the format of the files
    private String fileName;
    private List<Edge> edges;

    // Constructor
    public Railroad(int numTracks, String fileName) {
        this.numTracks = numTracks;
        this.fileName = fileName;
        edges = new ArrayList<>();
        //calls the file reading function
        readTracksFromFile();
    }

    // Method to read from the file
    private void readTracksFromFile() {
    	
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\s+");
                String source = parts[0];
                String destination = parts[1];
                int cost = Integer.parseInt(parts[2]);
                edges.add(new Edge(source, destination, cost));
                
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Kruskal's Algorithm implementation to build the railroad
    private List<Edge> kruskalAlgorithm() {
        Collections.sort(edges, Comparator.comparingInt(Edge::getCost)); // Sort edges by cost
        Map<String, String> parent = new HashMap<>(); // Store parents of vertices
        List<Edge> minSpanningTree = new ArrayList<>();

        // Create disjoint sets
        for (int i = 0; i < edges.size(); i++) {
            Edge edge = edges.get(i);
            
            String sourceParent = find(parent, edge.getSource());
            String destParent = find(parent, edge.getDestination());
           
            // Check if adding this edge will create a cycle
            if (!sourceParent.equals(destParent)) {
                String source = edge.getSource();
                String destination = edge.getDestination();
                
                // Rearrange vertices lexicographically if necessary
                if (source.compareTo(destination) > 0) {
                    String temp = source;
                    source = destination;
                    destination = temp;
                }
             // Add the edge with vertices in lexicographical order to the minimum spanning tree
                minSpanningTree.add(new Edge(source, destination, edge.getCost()));
             // operation to merge the sets of source and destination vertices
                union(parent, sourceParent, destParent);
            }
        }

        return minSpanningTree;
    }

    // Find operation for disjoint sets
    private String find(Map<String, String> parent, String vertex) {
    	
        if (!parent.containsKey(vertex)) {
            parent.put(vertex, vertex);
            return vertex;
        }

        // Path compression: Update the parent of each traversed node directly to the root
        String root = vertex;
        while (!root.equals(parent.get(root))) {
            root = parent.get(root);
        }
        
        // Apply path compression to set the parent of each traversed node directly to the root
        String currentNode = vertex;
        while (!currentNode.equals(root)) {
            String nextNode = parent.get(currentNode);
            parent.put(currentNode, root);
            currentNode = nextNode;
        }

        return root;
    }


    // Union operation for disjoint sets
    private void union(Map<String, String> parent, String vertex1, String vertex2) {
        parent.put(vertex1, vertex2);
    }

    // Method to build the railroad and return the result as a string
    public String buildRailroad() {
        List<Edge> minSpanningTree = kruskalAlgorithm();
        if (minSpanningTree == null) {
            return "Error in building the railroad.";
        }

        StringBuilder result = new StringBuilder();
        int totalCost = 0;

        //forms the ToString
        for (Edge edge : minSpanningTree) {
            result.append(edge.getSource()).append("---").append(edge.getDestination())
                    .append("\t$").append(edge.getCost()+ "\n");
            totalCost += edge.getCost();
        }
        result.append("The cost of the railroad is $").append(totalCost + ".");
        return result.toString();
    }

    // Helper class to represent an edge between vertices
    private static class Edge {
        private String source;
        private String destination;
        private int cost;

        public Edge(String source, String destination, int cost) {
            this.source = source;
            this.destination = destination;
            this.cost = cost;
        }

        public String getSource() {
            return source;
        }

        public String getDestination() {
            return destination;
        }

        public int getCost() {
            return cost;
        }
    }
}
