/**
 * Based on Depth First Search in the course-book
 *
 * Using DFS instead of BFS as it requires less memory (no queue)
 * to be allocated and also is less complex to implement.
 */

package is.mjuk.id1020;

import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import se.kth.id1020.Vertex;

public class DFS {

    private boolean[] visited;
    private int count;
    private Graph graph;

    public DFS(Graph graph)
    {
        this.graph = graph;
        visited = new boolean[graph.numberOfVertices()];

        for (Vertex vertex : graph.vertices())
        {
            if (dfs(vertex.id))
                count++;
        }
    }

    /**
     * Steps through all the connected vertices and returns true if it hasn't been connected already
     *
     * @param vertex Vertex to examine
     * @return true if this vertex isn't part of a previous subgraph. Otherwise false
     */
    private boolean dfs(int vertex)
    {
        if (vertex > visited.length)
            throw new ArrayIndexOutOfBoundsException("That element can't exist");
        if (visited[vertex])
            return false;
        visited[vertex] = true;
        for (Edge nxt : graph.adj(vertex))
            if (!visited[nxt.to])
                dfs(nxt.to);
        return true;
    }

    public int count()
    {
        return this.count;
    }

}
