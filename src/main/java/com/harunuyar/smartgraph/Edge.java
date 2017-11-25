package com.harunuyar.smartgraph;

public class Edge<N, E> {

    private E type;
    private N sourceNode;
    private N targetNode;

    public Edge(N sourceNode, N targetNode, E type) {
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
        this.type = type;
    }

    protected void changeSourceNode(N sourceNode) {
        this.sourceNode = sourceNode;
    }

    protected void changeTargetNode(N targetNode) {
        this.targetNode = targetNode;
    }

    public void setType(E type) {
        this.type = type;
    }

    public N getSourceNode() {
        return sourceNode;
    }

    public N getTargetNode() {
        return targetNode;
    }

    public E getType() {
        return type;
    }

    @Override
    public boolean equals(Object obj) {

        if (super.equals(obj)) {
            return true;
        }

        if (obj instanceof Edge) {
            Edge e = (Edge) obj;

            return getSourceNode().equals(e.getSourceNode())
                    && getTargetNode().equals(e.getTargetNode())
                    && getType().equals(e.getType());
        }

        return false;
    }

    @Override
    public String toString() {
        return type + ": " + sourceNode + " -> " + targetNode;
    }
}
