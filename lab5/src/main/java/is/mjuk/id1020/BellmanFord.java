/**
 * Based on the Bellman Ford algorithm as presented in
 * Algorithms in a Nutshell by George T. Heineman & co (O'Reilly 2009)
 *
 * Has a time complexity of O(V*E) which is worse than Dijkstra's, but
 * doesn't need a queue and should use less memory.
 * Can be drop-in replaced by Dijkstras algorithm if needed.
 */

package is.mjuk.id1020;

import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import se.kth.id1020.Vertex;

import java.util.ArrayList;

public class BellmanFord {
    public Graph graph;
    public double distance[];
    public Edge edgeTo[];
    private boolean distancesCalculated = false;
    private boolean weightUsed;
    private int start;

    public BellmanFord(Graph graph)
    {
        this.graph = graph;
        this.distance = new double[graph.numberOfVertices()];
        this.edgeTo = new Edge[graph.numberOfVertices()];
    }

    public Edge[] shortestPath(String from, String to, Boolean weight) throws IllegalArgumentException
    {
        int start = -1;
        int end = -1;

        if (from.equals(to))
            throw new IllegalArgumentException("Really?");

        for (Vertex v : graph.vertices())
        {
            if (v.label.equals(from))
                start = v.id;
            else if (v.label.equals(to))
                end = v.id;
        }

        return shortestPath(start, end, weight);
    }

    public Edge[] shortestPath(String from, String to)
    {
        return shortestPath(from, to, true);
    }

    public Edge[] shortestPath(int start, int end)
    {
        return shortestPath(start, end, true);
    }

    public Edge[] shortestPath(int start, int end, boolean useWeight)
    {
        if (start == -1 || end == -1)
            return null;

        calculateDistances(useWeight, start);

        ArrayList<Edge> rv = new ArrayList<Edge>();

        for(int step = end; this.edgeTo[step] != null; step = this.edgeTo[step].from)
        {
            rv.add(0, this.edgeTo[step]);
        }

        return rv.toArray(new Edge[rv.size()]);
    }

    private void calculateDistances(boolean useWeight, int start)
    {
        if (this.distancesCalculated &&
                this.weightUsed == useWeight &&
                this.start == start)
            return; // Skip if the correct results are already in the array (not relevant for the task)

        for (Vertex v : graph.vertices())
        {
            this.distance[v.id] = Double.POSITIVE_INFINITY;
            this.edgeTo[v.id] = null;
        }

        distance[start] = 0;
        this.start = start;

        for (Vertex v : graph.vertices()) {
            for (Edge e : graph.edges()) {
                double weight;

                if (useWeight)
                    weight = e.weight;
                else
                    weight = 1;

                double newLength = this.distance[e.from] + weight;

                if (newLength < this.distance[e.to]) {
                    this.distance[e.to] = newLength;
                    this.edgeTo[e.to] = e;
                }
            }
        }

        this.distancesCalculated = true;
        this.weightUsed = useWeight;
    }
}
