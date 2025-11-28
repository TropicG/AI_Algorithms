import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

public class AStart {

    class Node implements Comparable<Node> {

        String id;
        Map<Node, Integer> neighbors;

        int g;
        int h;

        Node parent;


        public Node(String id, int h) {
            this.id = id;
            this.h = h;

            parent = null;
            neighbors = new HashMap<>();
        }

        @Override
        public int compareTo(Node otherNode){
            return Integer.compare(this.h + this.g, otherNode.g + otherNode.h);
        }

        public void addNeighbor(Node newNeighbor, int cost) {
            this.neighbors.put(newNeighbor, cost);
        }

        @Override
        public boolean equals(Object other) {

            if(this == other) return true;

            if(other == null || other.getClass() != this.getClass()) return false;

            Node otherNode = (Node) other;
            return this.id.equals(otherNode.id);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(id);
        }
    }

    public void AStarAlg(Node start, Node goal) {

        PriorityQueue<Node> allNodeForProcess = new PriorityQueue<>();
        Set<Node> visistedNodes = new HashSet<>();
        Set<Node> toBeCheckedNotes = new HashSet<>();

        start.g = 0;
        allNodeForProcess.add(start);
        toBeCheckedNotes.add(start);

        while(!allNodeForProcess.isEmpty()) {

            Node currentNode = allNodeForProcess.poll();
            System.out.println("Current Node for checkout is " + currentNode.id);

            if(currentNode.equals(goal)){
                System.out.println("Found the node we are searching for " + goal.id);
            }

            visistedNodes.add(currentNode);

            for(Map.Entry<Node, Integer> neighborInfo : currentNode.neighbors.entrySet()) {

                Node neighbor = neighborInfo.getKey();
                int cost = neighborInfo.getValue();

                if(visistedNodes.contains(neighbor) || toBeCheckedNotes.contains(neighbor)) {
                    continue;
                }

                neighbor.g = cost + currentNode.g;
                neighbor.parent = currentNode;

                allNodeForProcess.add(neighbor);
                toBeCheckedNotes.add(neighbor);

            }
        }
    }

    private static List<Node> reconstructPath(Node goal) {

        List<Node> path = new ArrayList<>();
        Node current = goal;

        while(current != null){
            path.add(current);
            current = current.parent;
        }
        Collections.reverse(path);

        return path;
    }
}
