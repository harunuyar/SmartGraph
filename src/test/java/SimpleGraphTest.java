import com.harunuyar.smartgraph.SimpleGraph;

public class SimpleGraphTest {

    public static void main(String[] args) {
        SimpleGraph<String> graph = new SimpleGraph<>();

        graph.addNode("Ali");
        graph.addNode("Ayşe");

        System.out.println("Nodes are added.\n");
        System.out.println(graph);

        graph.addEdge("Ali", "Ayşe");
        graph.addEdge("Ali", "Hakan");
        graph.addEdge("Hakan", "Melih");

        System.out.println("Edges are added.\n");
        System.out.println(graph);

        System.out.println("\nEdges comes out of Ali:\n");
        graph.getOutgoingEdges("Ali").forEach(System.out::println);

        System.out.println("\nEdges comes in to Melih:\n");
        graph.getIncomingEdges("Melih").forEach(System.out::println);
    }

}
