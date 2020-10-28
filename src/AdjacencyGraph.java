import java.util.ArrayList;
import java.util.PriorityQueue;
public class AdjacencyGraph {
    //An adjacency graph should have an ArrayList of vertices, where each vertex contains an ArrayList of all its out-edges.
    ArrayList<Vertex> vertices;

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
            System.out.println("From vertex: " + vertices.get(i).getName());
            Vertex currentFrom = vertices.get(i);
            for (int j = 0; j < currentFrom.getOutEdges().size(); j++) {
                Edge currentEdge = currentFrom.getOutEdges().get(j);
                System.out.println("To: " + currentEdge.getVertexTo().getName() + " distance: " + currentEdge.getWeight());
            }
            System.out.println();
        }
    }

    //A method that performs Prim's algorithm on the graph, and makes it the minimum spanning tree.
    public void MSTPrims() {
        //Each vertex is only visited once in Prim's algorithm. Therefore all the visited vertices is added in an ArrayList
        ArrayList<Vertex> visitedVertices = new ArrayList<>();
        //visitedEdges is a PriorityQueue of edges to contain all the out-edges of all the visited vertices.
        PriorityQueue<Edge> visitedEdges = new PriorityQueue<>();
        //usedEdges is an ArrayList to contain all the out-edges that are part of the minimum spanning tree.
        ArrayList<Edge> usedEdges = new ArrayList<>();
        //In this implementation the starting vertex is the vertex at index 0 of the adjacency graph.
        //In Prim's algorithm for minimum spanning tree the starting index does not make a big difference, because
        //it will return the same overall cost of the minimum spanning tree.
        Vertex startingPoint = vertices.get(0);
        visitedVertices.add(startingPoint);

        //until visitedVertices contains all vertices, find the next vertex to be added in visitedVertices according to Prim's algorithm.
        while (visitedVertices.size() < vertices.size()){
            findNextVertex(visitedVertices, visitedEdges, usedEdges);
        }

        //Prints out the minimum spanning tree neatly·
        printMST(usedEdges);

    }

    //Performs Prim's algorithm by finding what should be the next Vertex in visitedVertices
    //and what edge was the useful one to get there.
    private void findNextVertex(ArrayList<Vertex> visitedVertices, PriorityQueue<Edge> visitedEdges, ArrayList<Edge> usedEdges) {
        //find all out-edges of all the vertices in visitedVertices and adds them to visitedEdges,
        //where they smallest edge will be the head of the list
        for (Vertex vertex : visitedVertices) //goes through all vertices in visitedVertices.
        {
            for (Edge edge : vertex.getOutEdges()) //goes through all edges of all vertices in visitedVertices.
            {
                if (!visitedEdges.contains(edge)) //if the edge has not already been visited, it is added to visitedEdges.
                {
                    visitedEdges.add(edge);
                }
            }
        }
        //adds the vertex at the endpoint of the shortest edge to visitedVertex, and saves this shortest edge in usedEdges.
        findShortestEdge(visitedVertices, visitedEdges, usedEdges);

        //This removes all other edges from visitedEdges that is not going to be part of the minimum spanning tree.
        for (Vertex vertex : visitedVertices) {
            for ( Edge edge : vertex.getOutEdges()) {
                //If the to-Vertex and the from-Vertex is both in visitedVertices, then remove the Edge from visitedEdges.
                if (visitedVertices.contains(edge.getVertexFrom()) && visitedVertices.contains(edge.getVertexTo())){
                    visitedEdges.remove(edge);
                }
            }
        }
    }

    //This method finds the shortest edge to a vertex we have not visited yet. This shortest edge is added to usedEdges.
    private void findShortestEdge(ArrayList<Vertex> visitedVertex, PriorityQueue<Edge> visitedEdges, ArrayList<Edge> usedEdges){
        //if the end Vertex of the shortest edge has not already been visited, then it is added to visitedVertex.
        //and this edge, that was chosen to get to this vertex, is added to usedEdges.
        if (!visitedVertex.contains(visitedEdges.peek().to)) {
            visitedVertex.add(visitedEdges.peek().to);
            usedEdges.add(visitedEdges.peek());
        } else {
            //if the endpoint vertex of the shortest edge has already been visited, the shortest vertex is removed from
            //visitedEdges. Next the methods calls itself recursively, since it now will have a new shortest edge at index 0.
            //This will go on until we reach a vertex that is not already in visitedVertex.
            visitedEdges.poll();
            findShortestEdge(visitedVertex,visitedEdges, usedEdges);
        }
    }

    //This method adds up the weights of all the usedEdges part of our final minimum spanning tree.
    private int findTotalCost(ArrayList<Edge> usedEdges) {
        int cost = 0;
        for (int i = 0; i < usedEdges.size(); i++){
            cost += usedEdges.get(i).getWeight();
        }
        return cost;
    }

    //A method to print the minimum spanning tree as well as the total cost.
    private void printMST(ArrayList<Edge> usedEdges){
        for (int i = 0; i < usedEdges.size(); i++){
            System.out.println(i+1 + ") From " + usedEdges.get(i).getVertexFrom() + " to " +
                    usedEdges.get(i).getVertexTo()+ ": Distance " + usedEdges.get(i).getWeight() + "km");
        }
        int totalCost = findTotalCost(usedEdges);
        System.out.println("The total cost of the electricity grid is " + totalCost+" million.");
    }
}

