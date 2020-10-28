public class Edge implements Comparable<Edge> {
    //Edges have to be comparable because we compare the weight of them. Therefore, the interface Comparable is used.
    //an edge has a from vertex and a to vertex and an integer containing the weight.
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

    //The edges should be compared according to their weights.
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

    public Vertex getVertexFrom() {
        return from;
    }

    public Vertex getVertexTo() {
        return to;
    }
}
