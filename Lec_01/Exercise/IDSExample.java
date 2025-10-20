import java.util.Iterator;
import java.util.LinkedList;

public class IDSExample {

    public int vertex;
    public LinkedList<Integer>[] edges;

    public IDSExample(int numberVertex) {

        vertex = numberVertex;
        edges = new LinkedList[5];

        for(int i = 0; i < vertex; i++){
            edges[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int from, int to) {
        edges[from].add(to);
    }

    private boolean DLSUtil(int currentState, int goalState, int limit, boolean[] visited) {

        visited[currentState] = true;

        if(currentState == goalState) {
            return true;
        }

        Iterator<Integer> iter = edges[currentState].listIterator();
        while(iter.hasNext()) {
            int nextState = iter.next();

            if(!visited[nextState]) {
                if(DLSUtil(currentState, goalState, limit - 1, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean DLS(int start, int goal, int limit) {

        boolean[] visited = new boolean[vertex];

        return DLSUtil(start, goal, limit, visited);
    }

    public boolean IDS(int start, int goal, int maxDepth) {

        for(int i = 0; i <= maxDepth; i++) {

            if(DLS(start, goal, i)) {
                return true;
            }

        }

        return false;
    }

    public static void main(String[] args) {
        IDSExample g = new IDSExample(7); // Създаваме граф със 7 върха (0-6)
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(1, 4);
        g.addEdge(2, 5);
        g.addEdge(2, 6);


        int start = 0;
        int goal = 6;
        int maxDepth = 3;

        g.IDS(start, goal, maxDepth);
    }
}
