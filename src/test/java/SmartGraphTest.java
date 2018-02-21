import com.thesis.smartgraph.*;

public class SmartGraphTest {

    public static void main(String[] args) {
        SmartGraph<String> graph = new SmartGraph<>();

        EdgeType<String, String> LIKES = graph.createEdgeType("likes");

        graph.addSmartListener(new Informer());
        graph.addSmartListener(SmartListener.REFLEXIVE(LIKES));
        graph.addSmartListener(SmartListener.SYMMETRIC(LIKES));
        graph.addSmartListener(SmartListener.TRANSITIVE(LIKES));

        graph.addNode("Ali");
        graph.addNode("Ayşe");
        graph.addNode("Hakan");
        graph.addNode("Fatma");
        graph.addNode("Melih");

        System.out.println("\nNodes are added.\n");
        System.out.println(graph);

        System.out.println();

        graph.createEdge(LIKES, "Ali", "Ayşe");
        graph.createEdge(LIKES, "Ayşe", "Hakan");
        graph.createEdge(LIKES, "Fatma", "Melih");
        graph.createEdge(LIKES, "Hakan", "Fatma");

        System.out.println("Edges are added.\n");
        System.out.println(graph);

        System.out.println();

        System.out.println("\nEdges comes out of Ali:\n");
        graph.getAllOutgoingEdges("Ali").forEach(System.out::println);

        System.out.println("\nEdges comes in to Melih:\n");
        graph.getAllIncomingEdges("Melih").forEach(System.out::println);
    }

    public static class Informer extends SmartListener<String> {
        @Override
        public void nodeAdded(Graph<String> graph, String node) {
            System.out.println("Node added: " + node);
        }

        @Override
        public void edgeAdded(Graph<String> graph, Edge<String, String> edgeType) {
            System.out.println("EdgeType added: " + edgeType);
        }
    }

}
