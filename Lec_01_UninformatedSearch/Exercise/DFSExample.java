import java.util.*;

public class DFSExample {

    private final int v; // vertex number
    private LinkedList[] adj; // edges

    public DFSExample(int v) {
        this.v = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int from, int to) {
        this.adj[from].add(to);
    }

    private void DFSUtil(int currentVertex, boolean[] visited) {

        visited[currentVertex] = true;
        System.out.println(currentVertex + " ");

        Iterator<Integer> iter = adj[currentVertex].listIterator();
        while(iter.hasNext()) {
            int nextVertex = iter.next();

            if(!visited[nextVertex]){
                DFSUtil(nextVertex, visited);
            }
        }
    }


    public void DFS(int startVertex) {
        boolean[] visited = new boolean[v];

        DFSUtil(startVertex,visited);
    }

    public static void main(String[] args) {

        DFSExample g = new DFSExample(5);

        g.addEdge(0, 1); // 0 -> 1
        g.addEdge(0, 2); // 0 -> 2
        g.addEdge(1, 3); // 1 -> 3
        g.addEdge(2, 4); // 2 -> 4
        g.addEdge(0, 3); // 0 -> 3

        System.out.println("DFS starts with vertex 0");

        g.DFS(0);
    }
}

