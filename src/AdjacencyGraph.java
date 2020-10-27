import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class AdjacencyGraph {
    //An adjacency graph should have an ArrayList of vertices, where each vertex contains an ArrayList of all its out-edges.
    ArrayList<Vertex> vertices;

    //Contructor of AdjacencyGraph object.
    public AdjacencyGraph() {
        vertices = new ArrayList<Vertex>();
    }

    //A method that adds a vertex to the ArrayList of vertices that makes up the adjacency graph.
    public void addVertex(Vertex vertex) {
        vertices.add(vertex);
    }

    //This method adds an edge between two vertices and connects them. We also take into account the weight of the edge.
    public void addEdge(Vertex from, Vertex to, Integer weight) {
        //In case of connecting two edges that might not exist, we handle this.
        if (!(vertices.contains(from) && vertices.contains(to))) {
            System.out.println("Vertex is not in graph");
            return;
        }
        //If both vertices exist, an edge between them is created per direction to make sure the edges are bidirectional.
        //e.g. we can travel from Holbaek to Kalundborg, and also from Kalundborg to Holbaek.
        new Edge(from, to, weight);
        new Edge(to, from, weight);

    }

    //A method to print the adjacency graph neatly. It goes to every single vertex and prints all the out-edges from this vertex.
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

    //A method that performs Prim's algorithm on the graph, and makes it the minimum spanning tree.
    public void MSTPrims() {
        //Each vertex is only visited once in Prim's algorithm. Therefore all the visited vertices is added in an ArrayList
        ArrayList<Vertex> visitedVertices = new ArrayList<>();
        //visitedEdges is an ArrayList to contain all the out-edges of all the visited vertices.
        ArrayList<Edge> visitedEdges = new ArrayList<Edge>();
        //usedEdges is an ArrayList to contain all the out-edges that are part of the minimum spanning tree.
        ArrayList<Edge> usedEdges = new ArrayList<Edge>();
        //In this implementation the starting vertex is the vertex at index 0 of the adjacency graph.
        //In Prim's algorithm for minimum spanning tree the starting index does not make a difference.
        //It will return the same overall cost of the minimum spanning tree.
        Vertex startingPoint = vertices.get(0);
        visitedVertices.add(startingPoint);
        //We initialize the total cost of all the edges part of usedEdges.
        int totalCost = 0;

        //until visitedVertices contains all vertices, find the next vertex to be added in visitedVertices according to Prim's algorithm.
        while (visitedVertices.size() < vertices.size()){
            findNextVertex(visitedVertices, visitedEdges, usedEdges);
        }

        //System.out.println(visitedVertices);
        //System.out.println(visitedEdges);
        //System.out.println(usedEdges);
        // The total cost of all the used edges is found.
        totalCost = findTotalCost(usedEdges);
        tester(usedEdges);
        //System.out.println(totalCost);
        //Prints out the minimum spanning tree neatly·
        printMST(visitedVertices, usedEdges, totalCost);

    }

    //Performs prims algorithm by finding what should be the next Vertex in visitedVertices
    //and what edge was the useful one to get there.
    void findNextVertex(ArrayList<Vertex> visitedVertices, ArrayList<Edge> visitedEdges, ArrayList<Edge> usedEdges) {
        //find all out-edges of all the vertices in visitedVertices and adds them to visitedEdges.
        for (Vertex vertex : visitedVertices) // goes through all vertices in visitedVertices.
        {
            for (Edge edge : vertex.OutEdges) //goes through all edges of all vertices in visitedVertices.
            {
                if (!visitedEdges.contains(edge)) // if the edge has not already been visited, it is added to visitedEdges.
                {
                    visitedEdges.add(edge);
                }
            }
        }
        //adds the vertex at the endpoint of the shortest edge to visitedVertex, and saves this shortest edge in usedEdges.
        findShortestEdge(visitedVertices, visitedEdges, usedEdges);

        //This removes all other edges from visitedEdges that is not going to be part of the minimum spanning tree.
        for (Vertex vertex : visitedVertices) {
            for ( Edge edge : vertex.OutEdges) {
                //If the to-Vertex and the from-Vertex is both in visitedVertices, then remove the Edge from visitedEdges.
                if (visitedVertices.contains(edge.from) && visitedVertices.contains(edge.to)){
                    visitedEdges.remove(edge);
                }
            }
        }
    }

    //This method finds the shortest edge to a vertex we have not visited yet. This shortest edge is added to usedEdges.
    void findShortestEdge(ArrayList<Vertex> visitedVertex, ArrayList<Edge> visitedEdges, ArrayList<Edge> usedEdges){
        //visitedEdges is sorted in ascending order.
        Collections.sort(visitedEdges);
        //if the end Vertex of the shortest edge has not already been visited, then it is added to visitedVertex.
        //and this edge, that was chosen to get to this vertex, is added to usedEdges.
        if (!visitedVertex.contains(visitedEdges.get(0).to)) {
            visitedVertex.add(visitedEdges.get(0).to);
            usedEdges.add(visitedEdges.get(0));
        } else {
            //if the endpoint vertex of the shortest edge has already been visited, the shortest vertex is removed from
            //visitedEdges. Next the methods calls itself recursively, since it now will have a new shortest edge at index 0.
            //This will go on until we reach a vertex that is not already in visitedVertex.
            visitedEdges.remove(0);
            findShortestEdge(visitedVertex,visitedEdges, usedEdges);
        }
    }

    //I Made this to test why we cant write <= 0.
    void tester(ArrayList<Edge> usedEdges) {
        Edge lastElement = usedEdges.get(usedEdges.size()-1);
        for (int i = 0; usedEdges.get(i).compareTo(lastElement)== -1;i++) {
            System.out.println(usedEdges.get(i));
        }
        System.out.println(lastElement);
    }

    //This method adds up the weights of all the usedEdges part of our final minimum spanning tree.
    int findTotalCost(ArrayList<Edge> usedEdges) {
        int cost = 0;
        for (int i = 0; usedEdges.get(i).compareTo(usedEdges.get(usedEdges.size()-1)) == -1; i++){
            cost += usedEdges.get(i).weight;
        }
        cost += usedEdges.get(usedEdges.size()-1).weight;
        return cost;
    }

//NOT DONE RIGHT
    //A method to print the minimum spanning tree as well as the total cost.
    public void printMST(ArrayList<Vertex> listToPrint, ArrayList<Edge> usedEdges, int cost){
        for (int i = 0; i < listToPrint.size()-1; i++){
            System.out.println(i+1 + ") From " + listToPrint.get(i).name + " to " +
                    usedEdges.get(i).to+ ": Distance " + usedEdges.get(i).weight + "km");
        }
        System.out.println("The total cost of the electricity grid is " + cost+" million.");
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
        if (this.weight < o.weight)
            return -1;
        if (this.weight > o.weight)
            return 1;
        else return 0;
    }

    public Integer getWeight() {
        return weight;
    }
}
