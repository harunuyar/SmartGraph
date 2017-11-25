package com.harunuyar.smartgraph;

public class SimpleGraph<N> extends Graph<N, String> {

    public Edge<N, String> addEdge(N source, N target) {
        Edge<N, String> edge = new Edge<>(source, target, "");
        this.addEdge(edge);
        return edge;
    }

}
