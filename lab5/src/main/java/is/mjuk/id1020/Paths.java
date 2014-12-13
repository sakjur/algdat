/**
 * (c) 2014 Emil Tullstedt <emiltu@kth.se>
 * Credit to literature authors have been given on a per class basis.
 */

package is.mjuk.id1020;

import se.kth.id1020.Edge;
import se.kth.id1020.Graph;
import se.kth.id1020.DataSource;

public class Paths {
    public static void main(String[] argv)
    {
        Graph g = DataSource.load();
        System.out.println();
        subgraphs(g);
        System.out.println();
        shortestPath(g);
    }

    private static void subgraphs(Graph g)
    {
        DFS run = new DFS(g);

        if (run.count() > 1)
            System.out.println("The graph isn't fully connected, it has " + String.valueOf(run.count()) + " subgraphs");
        else if (run.count() == 1)
            System.out.println("The graph is fully connected");
        else
            throw new IllegalStateException("The graph is a lie?");
    }

    private static void shortestPath(Graph g)
    {
        BellmanFord bf = new BellmanFord(g);

        String from = "Renyn";
        String to = "Parses";

        System.out.println("Shortest weighted path between " + from + " and " + to);
        printEdges(g, bf, bf.shortestPath(from, to, true));

        System.out.println("\nShortest non-weighted path between " + from + " and " + to);
        printEdges(g, bf, bf.shortestPath(from, to, false));
    }

    private static void printEdges(Graph g, BellmanFord bf, Edge[] edges)
    {
        int next = 0;
        int breakLineCounter = 0;

        if (edges.length == 0)
        {
            System.out.println("There is no connection between the vertices.");
            return;
        }


        for (Edge edge : edges)
        {
            System.out.print(g.vertex(edge.from).label + " (" + String.valueOf(edge.from) + ")");
            System.out.print(" -> ");
            next = edge.to;
            breakLineCounter++;
            if (breakLineCounter >= 6)
            {
                System.out.println();
                breakLineCounter = 0;
            }
        }
        System.out.print(g.vertex(next).label + " (" + String.valueOf(next) + ")");
        System.out.println("; ");

        System.out.println("Weight is: " + String.valueOf(bf.distance[edges[edges.length-1].to]));
        System.out.println("Length is: " + String.valueOf(edges.length));
    }
}
