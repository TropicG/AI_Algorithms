import java.util.*;
import java.lang.Object;


class Node implements Comparable<Node> {

    String id; // number of the vertex 
    int h; // heuristic value (h(n))

    Map<Node, Integer> neighbors = new HashMap<>(); // all the nodes reachable from this one

    Node parent = null; // parent of the node
    int g = 0; // the total cost to reach this ndoe


    public Node(String id, int h) {
        this.id = id;
        this.h = h;
    } 

    public void addNeighbor(Node neighbor, int cost) {
        neighbors.put(neighbor, cost);
    }

    // Nodes are goign to be sorted based on least cost in priority queue
    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.h, other.h);
    }

    //used for the 
    @Override
    public boolean equals(Object other) {
        
        if(this == other) {
            return true;
        }

        if(other == null || this.getClass() != other.getClass()) {
            return false;
        }

        Node otherNode = (Node) other;
        return Objects.equals(this.id, other.id);
    }


    //used for the hashing because we are using HashSet
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id;
    }
}

class GreedyBestFirstSearch {

    public static List<Node> findPath(Node start, Node goal) {
   
        PriorityQueue<Node> frontier = new PriorityQueue<>(); // storing all Nodes that need to be checked    
        Set<Node> frontierSet = new HashSet<>(); // keeps track of all inserted Nodes to be checked
        Set<Node> visited = new HashSet<>(); // keeps track of already visited nodes

   
        start.g = 0; // the total cost of the start
        frontier.add(start);
        frontierSet.add(start); 

        while (!frontier.isEmpty()) {
        
            Node current = frontier.poll();

            // have we reached the goal
            if (current.equals(goal)) {
                System.out.println("Цел намерена!");
                return reconstructPath(goal);
            }

            // marked as visited 
            visited.add(current);

            // looking for the neighbors
            for (Map.Entry<Node, Integer> entry : current.neighbors.entrySet()) {
                Node neighbor = entry.getKey();
                int cost = entry.getValue();

                // if already visited
                if (visited.contains(neighbor)) {
                    continue; 
                }

                // if already marked as visited
                if (frontierSet.contains(neighbor)) {
                    continue; 
                }
            
            
                neighbor.g = current.g + cost; 
                neighbor.parent = current;
            
                frontier.add(neighbor);
                frontierSet.add(neighbor);
            }
        }

        return null; // Път не е намерен
    }

    private static List<Node> reconstructPath(Node goal) {

        List<Node> path = new ArrayList<>();
        Node current = goal;

        while(current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);

        return path;
    }
 
}
