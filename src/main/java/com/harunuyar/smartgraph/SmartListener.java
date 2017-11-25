package com.harunuyar.smartgraph;

public interface SmartListener<N, E> {

    public void nodeAdded(Graph<N, E> graph, N node);

    public void edgeAdded(Graph<N, E> graph, Edge<N, E> edge);

}
