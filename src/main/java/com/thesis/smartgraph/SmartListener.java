package com.thesis.smartgraph;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class SmartListener<N> {

    public void nodeAdded(Graph<N> graph, N node) {
    }

    public void edgeAdded(Graph<N> graph, Edge<N, N> edge) {
    }

    // --------------------

    public static <T> SmartListener<T> INFERS(EdgeType<T, T> type1, EdgeType<T, T> type2, EdgeType<T, T> type3) {
        return new SmartListener<T>() {
            @Override
            public void edgeAdded(Graph<T> graph, Edge<T, T> edge) {
                if (edge.getType().equals(type1)) {
                    Set<T> targets = graph.getOutgoingEdges(type2, edge.getTarget()).stream()
                            .map(Edge::getTarget)
                            .collect(Collectors.toSet());


                    for (T node : targets) {
                        graph.createEdge(type3, edge.getSource(), node);
                    }
                }
                if (edge.getType().equals(type2)) {
                    Set<T> sources = graph.getIncomingEdges(type1, edge.getSource()).stream()
                            .map(Edge::getSource)
                            .collect(Collectors.toSet());

                    for (T node : sources) {
                        graph.createEdge(type3, node, edge.getTarget());
                    }
                }
            }
        };
    }

    public static <T> SmartListener<T> TRANSITIVE(EdgeType<T, T> edgeType) {
        return INFERS(edgeType, edgeType, edgeType);
    }

    public static <T> SmartListener<T> REFLEXIVE(EdgeType<T, T> edgeType) {
        return new SmartListener<T>() {
            @Override
            public void nodeAdded(Graph<T> graph, T node) {
                graph.createEdge(edgeType, node, node);
            }
        };
    }

    public static <T> SmartListener<T> SYMMETRIC(EdgeType<T, T> edgeType) {
        return new SmartListener<T>() {
            @Override
            public void edgeAdded(Graph<T> graph, Edge<T, T> edge) {
                if (edge.getType().equals(edgeType)) {
                    graph.createEdge(edgeType, edge.getTarget(), edge.getSource());
                }
            }
        };
    }

}
