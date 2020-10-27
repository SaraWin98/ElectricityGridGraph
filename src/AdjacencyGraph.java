import java.util.ArrayList;
import java.util.Collections;

public class AdjacencyGraph {
    //We initialize the vertices
    ArrayList<Vertex> vertices;

    //In the constructor we add the new vertices to our Arraylist
    public AdjacencyGraph() {
        vertices = new ArrayList<Vertex>();
    }

    //Adds a Vertex to the vertices ArrayList
    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    //Adds an Edge to the Vertex
    public void addEdge(Vertex from, Vertex to, Integer distance) {
        //We check if the ArrayList contains
        if (!(vertices.contains(from) && vertices.contains(to))) {
            System.out.println("Vertex is not in graph");
            return;
        }
        Edge e = new Edge(from, to, distance);
        new Edge(to, from, distance);

    }

    public void printGraph() {
        for (int i = 0; i < vertices.size(); i++) {
            System.out.println("From vertex: " + vertices.get(i).name);
            Vertex currentFrom = vertices.get(i);
            for (int j = 0; j < currentFrom.OutEdges.size(); j++) {
                Edge currentEdge = currentFrom.OutEdges.get(j);
                System.out.println("To: " + currentEdge.to.name + " distance: " + currentEdge.weight);
            }
            System.out.println();
        }
    }

    public boolean isVisited(Vertex city, ArrayList group) {
        if (group.contains(city)) {
            return true;
        }
        return false;
    }

    public void MSTPrims() {
        ArrayList<Vertex> visitedVertices = new ArrayList<>();
        ArrayList<Edge> visitedEdges = new ArrayList<Edge>();
        ArrayList<Edge> usedEdges = new ArrayList<Edge>();
        Vertex startingPoint = vertices.get(0);
        visitedVertices.add(startingPoint);
        int cost = 0;

        // find alle edges
        for (Vertex vertex: visitedVertices) // går igennem alle vertices
        {
            for (Edge edge: vertex.OutEdges) //går igennem alle outedges for alle vertices
            {
                if (!visitedEdges.contains(edge)) //tilføjer alle outedges for alle vertices i VisitedEges
                {
                    visitedEdges.add(edge);
                }
            }
        }
        //adds the vertex at the endpoint of the shortest edge
        findShortestEdge(visitedVertices,visitedEdges,usedEdges);

        while (visitedVertices.size() < vertices.size()){
            findNextVertex(visitedVertices, visitedEdges, usedEdges);
        }

        System.out.println(visitedVertices);
        System.out.println(visitedEdges);
        System.out.println(usedEdges);
        for (int i = 0; usedEdges.get(i).compareTo(usedEdges.get(usedEdges.size()-1)) == -1; i++){
            cost += usedEdges.get(i).weight;
        }
        cost += usedEdges.get(usedEdges.size()-1).weight;

        System.out.println(cost);
        printMST(visitedVertices, usedEdges, cost);

    }

    void findNextVertex(ArrayList<Vertex> visitedVertices, ArrayList<Edge> visitedEdges, ArrayList<Edge> usedEdges){
        // find alle edges
        //for (int i = 0;  ; i++) {
            for (Vertex vertex : visitedVertices) // går igennem alle vertices
            {
                for (Edge edge : vertex.OutEdges) //går igennem alle outedges for alle vertices
                {
                    if (!visitedEdges.contains(edge)) //tilføjer alle outedges for alle vertices i VisitedEges
                    {
                        visitedEdges.add(edge);
                    }
                }
            }
            findShortestEdge(visitedVertices, visitedEdges, usedEdges);
        for (Vertex vertex : visitedVertices) {
            for ( Edge edge : vertex.OutEdges) {
                if (visitedVertices.contains(edge.from) && visitedVertices.contains(edge.to)){
                    visitedEdges.remove(edge);
                }

            }

        }
    }


    void findShortestEdge(ArrayList<Vertex> visitedVertex, ArrayList<Edge> visitedEdges, ArrayList<Edge> usedEdges){
        Collections.sort(visitedEdges);
        if (!visitedVertex.contains(visitedEdges.get(0).to))
        {
            visitedVertex.add(visitedEdges.get(0).to);
            usedEdges.add(visitedEdges.get(0));
        }
        else
        {
            visitedEdges.remove(0);
            findShortestEdge(visitedVertex,visitedEdges, usedEdges);
        }
    }

    public void printMST(ArrayList<Vertex> listToPrint, ArrayList<Edge> usedEdges, int cost){
        System.out.println();
        for (int i = 0; i < listToPrint.size()-1; i++){
            System.out.println(i+1 + ") City: " + listToPrint.get(i).name + " to: " +
                    usedEdges.get(i).to+ " Distance in km: " + usedEdges.get(i).weight + "km");
        }
        System.out.println("The final cost of the electricity grid is " + cost+" million");
    }
}

class Vertex implements Comparable<Vertex>{
    String name;
    ArrayList<Edge> OutEdges;
    Integer distance = Integer.MAX_VALUE;
    Vertex prev;

    public Vertex(String id){
        name=id;
        OutEdges = new ArrayList<Edge>();
        prev = null;
    }

    public void addOutEdge(Edge edge){
        OutEdges.add(edge);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Vertex o) {
        if (this.distance < o.distance)
            return -1;
        if (this.distance > o.distance)
            return 1;
        return 0;
    }

    public ArrayList<Edge> getOutEdges() {
        return OutEdges;
    }
}

class Edge implements Comparable<Edge> {
    Integer weight;
    Vertex from;
    Vertex to;

    public Edge(Vertex from, Vertex to, Integer weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.from.addOutEdge(this);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "weight=" + weight +
                ", from=" + from +
                ", to=" + to +
                '}';
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight< o.weight)
            return -1;
        if (this.weight > o.weight)
            return 1;
        return 0;
    }

    public Integer getWeight() {
        return weight;
    }
}
