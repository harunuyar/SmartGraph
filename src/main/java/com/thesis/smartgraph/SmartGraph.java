package com.thesis.smartgraph;

import java.util.ArrayList;
import java.util.List;

public class SmartGraph<N> extends Graph<N> {

    private List<SmartListener<N>> smartListeners;

    public SmartGraph() {
        super();
        this.smartListeners = new ArrayList<>();
    }

    @Override
    public void addNode(N node) {
        if (super.hasNode(node))
            return;

        super.addNode(node);

        smartListeners.forEach(l -> l.nodeAdded(this, node));
    }

    @Override
    public Edge<N, N> createEdge(EdgeType<N, N> edgeType, N source, N target) {
        Edge<N, N> edge = super.getEdge(edgeType, source, target);

        if (edge != null)
            return edge;

        Edge<N, N> newEdge = super.createEdge(edgeType, source, target);

        smartListeners.forEach(l -> l.edgeAdded(this, newEdge));

        return newEdge;
    }

    public void addSmartListener(SmartListener<N> listener) {
        smartListeners.add(listener);
    }

    public void removeSmartListener(SmartListener<N> listener) {
        smartListeners.remove(listener);
    }
}
