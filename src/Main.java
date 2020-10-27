public class Main {

    public static void main(String[] args) {
        //We initialize the AdjacencyGraph, whereafter we use the following method to add the cities
        AdjacencyGraph grid = new AdjacencyGraph();
        addCities(grid);
        grid.printGraph();
        //We run our Prims Minimum Spanning Tree algorithm
        grid.MSTPrims();

    }
    //This method simply adds the cities to the grid
    public static void addCities(AdjacencyGraph grid){
        //First by initializing all the vertices - this is not completely necessary,
        // these could be initalized later, but this makes it cleaner
        Vertex Eskildstrup = new Vertex("Eskildstrup");
        Vertex Haslev = new Vertex("Haslev");
        Vertex Holbaek = new Vertex("Holbaek");
        Vertex Jaergerspris = new Vertex("Jaergerspris");
        Vertex Kalundborg = new Vertex("Kalundborg");
        Vertex Korsoer = new Vertex("Korsoer");
        Vertex Koege = new Vertex("Koege");
        Vertex Maribo = new Vertex("Maribo");
        Vertex Nakskov = new Vertex("Nakskov");
        Vertex NykoebingF = new Vertex("Nykoebing F");
        Vertex Naestved = new Vertex("Naestved");
        Vertex Ringsted = new Vertex("Ringsted");
        Vertex Roskilde = new Vertex("Roskilde");
        Vertex Slagelse = new Vertex("Slagelse");
        Vertex Soroe = new Vertex("Soroe");
        Vertex Vordingborg = new Vertex("Vordingborg");

        //The cities are now added to the grid, this is done using addVertex from the AdjacencyGraph class
        grid.addVertex(Eskildstrup);
        grid.addVertex(Haslev);
        grid.addVertex(Holbaek);
        grid.addVertex(Jaergerspris);
        grid.addVertex(Kalundborg);
        grid.addVertex(Korsoer);
        grid.addVertex(Koege);
        grid.addVertex(Maribo);
        grid.addVertex(Nakskov);
        grid.addVertex(NykoebingF);
        grid.addVertex(Naestved);
        grid.addVertex(Ringsted);
        grid.addVertex(Roskilde);
        grid.addVertex(Slagelse);
        grid.addVertex(Soroe);
        grid.addVertex(Vordingborg);

        //The connections between the cities are now added using addEdge from the AdjacencyGraph class
        grid.addEdge(Eskildstrup, Maribo, 28);
        grid.addEdge(Eskildstrup, NykoebingF, 13);
        grid.addEdge(Eskildstrup, Vordingborg, 24);
        grid.addEdge(Haslev, Korsoer, 60);
        grid.addEdge(Haslev, Koege, 24);
        grid.addEdge(Haslev, Naestved, 25);
        grid.addEdge(Haslev, Ringsted, 19);
        grid.addEdge(Haslev, Roskilde, 47);
        grid.addEdge(Haslev, Slagelse, 48);
        grid.addEdge(Haslev, Soroe, 34);
        grid.addEdge(Haslev, Vordingborg, 40);
        grid.addEdge(Holbaek, Jaergerspris, 34);
        grid.addEdge(Holbaek, Kalundborg, 44);
        grid.addEdge(Holbaek, Korsoer, 66);
        grid.addEdge(Holbaek, Ringsted, 36);
        grid.addEdge(Holbaek, Roskilde, 32);
        grid.addEdge(Holbaek, Slagelse, 46);
        grid.addEdge(Holbaek, Soroe, 34);
        grid.addEdge(Jaergerspris, Korsoer, 95);
        grid.addEdge(Jaergerspris, Koege, 58);
        grid.addEdge(Jaergerspris, Ringsted, 56);
        grid.addEdge(Jaergerspris, Roskilde, 33);
        grid.addEdge(Jaergerspris, Slagelse, 74);
        grid.addEdge(Jaergerspris, Soroe, 63);
        grid.addEdge(Kalundborg, Ringsted, 62);
        grid.addEdge(Kalundborg, Roskilde, 70);
        grid.addEdge(Kalundborg, Slagelse, 39);
        grid.addEdge(Kalundborg, Soroe, 51);
        grid.addEdge(Korsoer, Naestved, 45);
        grid.addEdge(Korsoer, Slagelse, 20);
        grid.addEdge(Koege, Naestved, 45);
        grid.addEdge(Koege, Ringsted, 28);
        grid.addEdge(Koege, Roskilde, 25);
        grid.addEdge(Koege, Vordingborg, 60);
        grid.addEdge(Maribo, Nakskov, 27);
        grid.addEdge(Maribo, NykoebingF, 26);
        grid.addEdge(Naestved, Roskilde, 57);
        grid.addEdge(Naestved, Ringsted, 26);
        grid.addEdge(Naestved, Slagelse, 37);
        grid.addEdge(Naestved, Soroe, 32);
        grid.addEdge(Naestved, Vordingborg, 28);
        grid.addEdge(Ringsted, Roskilde, 31);
        grid.addEdge(Ringsted, Soroe, 15);
        grid.addEdge(Ringsted, Vordingborg, 58);
        grid.addEdge(Slagelse, Soroe, 14);
    }
}
