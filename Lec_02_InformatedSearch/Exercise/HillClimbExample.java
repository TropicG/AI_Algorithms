import java.util.*;

class Node implements Comparable<Node> {
    String id;
    int h; 
    
    Map<Node, Integer> neighbors = new HashMap<>();
    Node parent = null;
    int g; 

    public Node(String id, int h) {
        this.id = id;
        this.h = h;
        this.g = Integer.MAX_VALUE;
    }

    public void addNeighbor(Node neighbor, int cost) {
        neighbors.put(neighbor, cost);
    }


    @Override
    public int compareTo(Node other) {
        int hCompare = Integer.compare(this.h, other.h);
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

public class HillClimbing {
    public static List<Node> findPath(Node start, Node goal) {
        
        Node current = start;
        current.g = 0;


        while (true) {
            System.out.println("Текущо местоположение: " + current.id + " (h=" + current.h + ")");

            // 1. Проверка за целта
            if (current.equals(goal)) {
                System.out.println("Цел намерена!");
                return reconstructPath(current);
            }


            Node bestNeighbor = null;
            
            for (Node neighbor : current.neighbors.keySet()) {
                if (bestNeighbor == null || neighbor.h < bestNeighbor.h) {
                    bestNeighbor = neighbor;
                }
            }

           
            if (bestNeighbor == null || bestNeighbor.h >= current.h) {
                return break; 
            }

    
            int cost = current.neighbors.get(bestNeighbor);
            bestNeighbor.parent = current;
            bestNeighbor.g = current.g + cost;
            
            current = bestNeighbor;
        }
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
        
        System.out.println("Обща цена на пътя: " + totalCost);
        return path;
    }
}