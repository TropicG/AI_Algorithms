import javax.sound.sampled.Line;
import java.util.Iterator;
import java.util.LinkedList;

public class DLSExample {
    private int numVertex;
    private LinkedList<Integer>[] edges;

    public DLSExample(int numVertex) {
        this.numVertex = numVertex;
        this.edges = new LinkedList[numVertex];

        for(int i =0; i < numVertex; i++) {
            this.edges[i] = new LinkedList<Integer>();
        }
    }

    public void addEdge(int from, int to) {
        this.edges[from].add(to);
    }

    private boolean DLSUtil(int current, int goal, int limit, boolean[] visited) {

        visited[current] = true;

        if(current == goal) {
            return true;
        }

        if(limit == 0) {
            return false;
        }

        Iterator<Integer> iter = edges[current].listIterator();
        while(iter.hasNext()) {
            int next = iter.next();

            if(!visited[next] && DLSUtil(next, goal, limit - 1, visited)){
                return true;
            }
        }

        return false;
    }

    public boolean DLS(int start,int goal, int limit) {

        boolean[] visited = new boolean[numVertex];


        return DLSUtil(start, goal, limit, visited);
    }
}
