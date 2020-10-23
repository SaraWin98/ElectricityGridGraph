import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class AdjacencyGraph {
    ArrayList<Vertex> vertices;

    public AdjacencyGraph() {
        vertices = new ArrayList<Vertex>();
    }

    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    public void addEdge(Vertex from, Vertex to, Integer distance) {
        if (!(vertices.contains(from) && vertices.contains(to))) {
            System.out.println("Vertex is not in graph");
            return;
        }
        Edge e = new Edge(from, to, distance);
    }

    public void printGraph() {
        for (int i = 0; i < vertices.size(); i++) {
            System.out.println("From vertex: " + vertices.get(i).name);
            Vertex currentFrom = vertices.get(i);
            for (int j = 0; j < currentFrom.OutEdges.size(); j++) {
                Edge currentEdge = currentFrom.OutEdges.get(j);
                System.out.println("To: " + currentEdge.to.name + " distance: " + currentEdge.distance);
            }
            System.out.println();
        }
    }


}

class Vertex implements Comparable<Vertex>{
    String name;
    ArrayList<Edge> OutEdges;
    Integer distance = Integer.MAX_VALUE;

    public Vertex(String id){
        name=id;
        OutEdges = new ArrayList<Edge>();
    }

    public void addOutEdge(Edge edge){
        OutEdges.add(edge);
    }

    @Override
    public int compareTo(Vertex o) {
        if (this.distance < o.distance)
            return -1;
        if (this.distance > o.distance)
            return 1;
        return 0;
    }
}

class Edge {
    Integer distance;
    Vertex from;
    Vertex to;

    public Edge(Vertex from, Vertex to, Integer distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
        this.from.addOutEdge(this);
    }
}
