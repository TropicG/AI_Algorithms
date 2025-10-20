import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BFSExample {

    public int v;
    public LinkedList<Integer>[] edges;

    public BFSExample(int v) {
        this.v = v;
        edges = new LinkedList[v];
        for(int i = 0; i < v; i++) {
            edges[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int from, int to) {
        this.edges[from].add(to);
    }

    public void BFS(int startIndex) {

        //keeping a check which were visited already
        boolean[] visited = new boolean[v];

        //storing the next vertex to be checked
        Queue<Integer> queue = new LinkedList<>();

        //adding the starting point
        visited[startIndex] = true;
        queue.add(startIndex);

        while(!queue.isEmpty()) {

            //getting the next in line
            int currentVertex = queue.poll();

            System.out.println(currentVertex + " ");

            //those children of currentVertex which were not visited are going to be added to the queue
            Iterator<Integer> iter = edges[currentVertex].listIterator();
            while(iter.hasNext()){
                int nextVertex = iter.next();

                if(!visited[nextVertex]) {
                    visited[nextVertex] = true;
                    queue.add(nextVertex);
                }
            }
        }
    }

    public static void main(String[] args) {

        BFSExample g = new BFSExample(5);

        g.addEdge(0, 1); // 0 -> 1
        g.addEdge(0, 2); // 0 -> 2
        g.addEdge(1, 3); // 1 -> 3
        g.addEdge(2, 4); // 2 -> 4
        g.addEdge(1, 4); // 1 -> 4

        System.out.println("BFS starting from vertex 0");

        g.BFS(0);
    }
}
