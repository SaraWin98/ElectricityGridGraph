import java.util.ArrayList;

public class Vertex{
    //attributes for vertex class:
    //It contains a name, an ArrayList of out-edges.
    String name;
    ArrayList<Edge> OutEdges;


    public Vertex(String id){
        name=id;
        OutEdges = new ArrayList<Edge>();
    }

    public void addOutEdge(Edge edge){
        OutEdges.add(edge);
    }

    @Override
    public String toString() {
        return name;
    }

    public ArrayList<Edge> getOutEdges() {
        return OutEdges;
    }

    public String getName() { return name; }
}
