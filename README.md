**Railroad Builder**
The Railroad Network Builder is a Java program designed to construct cost-effective railroad networks connecting various destinations. It utilizes Kruskal's Algorithm to find the minimum spanning tree of a graph representing the railroad network, ensuring that all destinations are connected with the least possible cost.

**Overview**
The Railroad Network Builder reads destination and cost information from a file and constructs the railroad network using Kruskal's Algorithm. It then outputs the connections between destinations along with their associated costs, providing a comprehensive overview of the constructed railroad and its total cost. The Railroad Network Builder Driver provides multiple test cases to verify the correctness of the implemented solution.

**Features**
File Input: Reads destination and cost information from a file, allowing for easy customization of the railroad network.
Kruskal's Algorithm: Utilizes Kruskal's Algorithm to find the minimum spanning tree of the railroad network, ensuring an optimal and cost-effective solution.
Output Formatting: Outputs the connections between destinations along with their associated costs, providing clear and concise information about the constructed railroad.

**Usage**
Compilation: Compile both the Railroad.java and RailroadDriver.java files.

javac Railroad.java
javac RailroadDriver.java

Output: The program will display the constructed railroad networks along with their associated costs for each test case.

**Input Format**
The input file should contain lines representing connections between destinations and their associated costs. 
Each line should follow the format:
[Source] [Destination] [Cost]
