package com.harunuyar.smartgraph;

import java.util.ArrayList;
import java.util.List;

public class SmartGraph<N, E> extends Graph<N, E> {

    private List<SmartListener<N, E>> smartListeners;

    public SmartGraph() {
        super();
        this.smartListeners = new ArrayList<>();
    }

    @Override
    public void addNode(N node) {
        if (hasNode(node))
            return;

        super.addNode(node);

        smartListeners.forEach(l -> l.nodeAdded(this, node));
    }

    @Override
    public Edge<N, E> addEdge(N source, N target, E type) {
        Edge<N, E> edge = findEdgeBetween(source, target, type);
        if (edge != null)
            return edge;

        Edge<N, E> newEdge = super.addEdge(source, target, type);

        smartListeners.forEach(l -> l.edgeAdded(this, newEdge));

        return newEdge;
    }

    public void addSmartListener(SmartListener<N, E> listener) {
        smartListeners.add(listener);
    }

    public void removeSmartListener(SmartListener<N, E> listener) {
        smartListeners.remove(listener);
    }
}
