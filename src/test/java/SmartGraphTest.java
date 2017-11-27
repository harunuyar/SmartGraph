import com.harunuyar.smartgraph.Edge;
import com.harunuyar.smartgraph.Graph;
import com.harunuyar.smartgraph.SmartGraph;
import com.harunuyar.smartgraph.SmartListener;

import java.util.Set;

public class SmartGraphTest {

    private static final String LIKES = "likes";

    public static void main(String[] args) {
        SmartGraph<String, String> graph = new SmartGraph<>();

        graph.addSmartListener(new Informer());
        graph.addSmartListener(new ReflexiveLikes());
        graph.addSmartListener(new SymmetricLikes());
        graph.addSmartListener(new TransitiveLikes());

        graph.addNode("Ali");
        graph.addNode("Ayşe");
        graph.addNode("Hakan");
        graph.addNode("Fatma");
        graph.addNode("Melih");

        System.out.println("\nNodes are added.\n");
        System.out.println(graph);

        graph.addEdge("Ali", "Ayşe", LIKES);
        graph.addEdge("Ayşe", "Hakan", LIKES);
        graph.addEdge("Fatma", "Melih", LIKES);
        graph.addEdge("Hakan", "Fatma", LIKES);

        System.out.println("Edges are added.\n");
        System.out.println(graph);

        System.out.println("\nEdges comes out of Ali:\n");
        graph.getOutgoingEdges("Ali").forEach(System.out::println);

        System.out.println("\nEdges comes in to Melih:\n");
        graph.getIncomingEdges("Melih").forEach(System.out::println);
    }

    public static class Informer implements SmartListener<String, String> {
        @Override
        public void nodeAdded(Graph<String, String> graph, String node) {
            System.out.println("Node added: " + node);
        }

        @Override
        public void edgeAdded(Graph<String, String> graph, Edge<String, String> edge) {
            System.out.println("Edge added: " + edge);
        }
    }

    public static class ReflexiveLikes implements SmartListener<String, String> {
        @Override
        public void nodeAdded(Graph<String, String> graph, String node) {
            // Everybody likes himself/herself.
            graph.addEdge(node, node, LIKES);
        }

        @Override
        public void edgeAdded(Graph<String, String> graph, Edge<String, String> edge) {}
    }

    public static class SymmetricLikes implements SmartListener<String, String> {
        @Override
        public void nodeAdded(Graph<String, String> graph, String node) {}

        @Override
        public void edgeAdded(Graph<String, String> graph, Edge<String, String> edge) {
            // If you like someone, that someone likes you too!
            graph.addEdge(edge.getTargetNode(), edge.getSourceNode(), LIKES);
        }
    }

    public static class TransitiveLikes implements SmartListener<String, String> {
        @Override
        public void nodeAdded(Graph<String, String> graph, String node) {}

        @Override
        public void edgeAdded(Graph<String, String> graph, Edge<String, String> edge) {
            // If you like someone and that someone likes another one, then you like that other one too!

            if (!edge.getType().equals(LIKES))
                return;

            Set<String> sources = graph.getIncomingNodes(edge.getSourceNode(), LIKES);
            String s = edge.getSourceNode();
            String t = edge.getTargetNode();
            Set<String> targets = graph.getOutgoingNodes(edge.getTargetNode(), LIKES);

            // Everyone who likes you, loves who you like
            for (String node : sources) {
                graph.addEdge(node, t, LIKES);
            }

            // You like everyone the one you like likes
            for (String node : targets) {
                graph.addEdge(s, node, LIKES);
            }

            // Everyone who likes you, likes everyone the one you like likes
            for (String nodeS : sources) {
                for (String nodeT : targets) {
                    graph.addEdge(nodeS, nodeT, LIKES);
                }
            }

        }
    }
}
