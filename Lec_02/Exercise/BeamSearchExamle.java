import java.util.*;

class Node implements Comparable<Node> {

    String id; // the name of the vertex
    int h; // euristic value
    
    Map<Node, Integer> neighbors = new HashMap<>(); // all the neighbors 
    Node parent = null; // the parent
    int g = 0;  // to total cost to reach this node

    public Node(String id, int h) {
        this.id = id;
        this.h = h;
    }

    public void addNeighbor(Node neighbor, int cost) {
        neighbors.put(neighbor, cost);
    }

    @Override
    public int compareTo(Node other) {

        // the natural order in TreeSet is going to be based on the h(n) values, the smaller the better
        int hCompare = Integer.compare(this.h, other.h);

        //this check is important, to prevent the TreeSet thinking we have duplicates
        if (hCompare == 0) {
            return this.id.compareTo(other.id);
        }
        return hCompare;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return Objects.equals(id, node.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id;
    }
}

class BoundedGreedySearch {

    public static List<Node> findPath(Node start, Node goal, int beamWidth) {
        
        // All the nodes we have to visited are going to be stored here 
        TreeSet<Node> frontier = new TreeSet<>();
        
        // All the nodes which were already visited are here
        Set<Node> visited = new HashSet<>();

        start.g = 0;
        frontier.add(start);

        while (!frontier.isEmpty()) {
            
            Node current = frontier.pollFirst();
                        
            if (current.equals(goal)) {
                return reconstructPath(current);
            }
            
            visited.add(current);

            for (Map.Entry<Node, Integer> entry : current.neighbors.entrySet()) {

                Node neighbor = entry.getKey();
                int cost = entry.getValue();
                
                // if it was already visited or if it was already added to visit
                if (visited.contains(neighbor) || frontier.contains(neighbor)) {
                    continue;
                }

                neighbor.g = current.g + cost;
                neighbor.parent = current;
                
                frontier.add(neighbor);

                //if we have reached the limit, we removed the one with the least priority
                if (frontier.size() > beamWidth) {
                    Node worstNode = frontier.pollLast(); 
                }
            }
        }

        return null; 
    }

   
    private static List<Node> reconstructPath(Node goal) {

        List<Node> path = new ArrayList<>();
        Node current = goal;
        int totalCost = goal.g;

        while (current != null) {
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);
        
        return path;
    }
}