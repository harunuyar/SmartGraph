package com.harunuyar.smartgraph;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph<N, E> {

    private Set<N> nodes;
    private Set<Edge<N, E>> edges;

    public Graph() {
        this.nodes = new HashSet<>();
        this.edges = new HashSet<>();
    }

    public void addNode(N node) {
        this.nodes.add(node);
    }

    protected void addEdge(Edge<N, E> edge) {
        addNode(edge.getSourceNode());
        addNode(edge.getTargetNode());

        this.edges.add(edge);
    }

    public Edge<N, E> addEdge(N source, N target, E type) {
        Edge edge = findEdgeBetween(source, target, type);
        if (edge != null)
            return edge;

        edge = new Edge<>(source, target, type);
        this.addEdge(edge);
        return edge;
    }

    public Set<Edge<N, E>> getIncomingEdges(final N node) {
        return edges.stream()
                .filter(e -> e.getTargetNode().equals(node))
                .collect(Collectors.toSet());
    }

    public Set<N> getIncomingNodes(final N node) {
        return edges.stream()
                .filter(e -> e.getTargetNode().equals(node))
                .map(Edge::getSourceNode)
                .collect(Collectors.toSet());
    }

    public Set<N> getIncomingNodes(final N node, E type) {
        return edges.stream()
                .filter(e -> e.getTargetNode().equals(node)
                        && e.getType().equals(type))
                .map(Edge::getSourceNode)
                .collect(Collectors.toSet());
    }

    public Set<Edge<N, E>> getOutgoingEdges(final N node) {
        return edges.stream()
                .filter(e -> e.getSourceNode().equals(node))
                .collect(Collectors.toSet());
    }

    public Set<N> getOutgoingNodes(final N node) {
        return edges.stream()
                .filter(e -> e.getSourceNode().equals(node))
                .map(Edge::getSourceNode)
                .collect(Collectors.toSet());
    }

    public Set<N> getOutgoingNodes(final N node, E type) {
        return edges.stream()
                .filter(e -> e.getSourceNode().equals(node)
                        && e.getType().equals(type))
                .map(Edge::getSourceNode)
                .collect(Collectors.toSet());
    }

    public Set<Edge<N, E>> getEdges(E type) {
        return edges.stream()
                .filter(e -> e.getType().equals(type))
                .collect(Collectors.toSet());
    }

    public Set<Edge<N, E>> findEdgesBetween(N source, N target) {
        return edges.stream()
                .filter(e -> e.getSourceNode().equals(source)
                        && e.getTargetNode().equals(target))
                .collect(Collectors.toSet());
    }

    public Edge<N, E> findEdgeBetween(N source, N target, E type) {
        return edges.stream()
                .filter(e -> e.getSourceNode().equals(source)
                        && e.getTargetNode().equals(target)
                        && e.getType().equals(type))
                .findFirst().orElse(null);
    }

    public Set<Edge<N, E>> getEdges() {
        return edges;
    }

    public Set<N> getNodes() {
        return nodes;
    }

    public boolean hasNode(N node) {
        return this.nodes.contains(node);
    }

    public boolean hasEdge (N source, N target) {
        return edges.stream()
                .anyMatch(e -> e.getSourceNode().equals(source)
                        && e.getTargetNode().equals(target));
    }

    public boolean hasEdge (N source, N target, E type) {
        return edges.stream()
                .anyMatch(e -> e.getSourceNode().equals(source)
                        && e.getTargetNode().equals(target)
                        && e.getType().equals(type));
    }

    @Override
    public String toString() {
        return "- Nodes:\n"
                + nodes.stream().map(N::toString).collect(Collectors.joining("\n"))
                + "\n- Edges:\n"
                + edges.stream().map(Edge::toString).collect(Collectors.joining("\n"));
    }
}
