package com.thesis.smartgraph;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph<N> {

    private Set<N> nodes;
    private Set<EdgeType<N, N>> edgeTypes;

    public Graph() {
        this.nodes = new HashSet<>();
        this.edgeTypes = new HashSet<>();
    }

    public void addNode(N node) {
        this.nodes.add(node);
    }

    public boolean hasNode(N node) {
        return nodes.contains(node);
    }

    public EdgeType<N, N> createEdgeType(String name) {
        EdgeType<N, N> edgeType = new EdgeType<>(name);
        this.edgeTypes.add(edgeType);
        return edgeType;
    }

    public Edge<N, N> createEdge(EdgeType<N, N> edgeType, N source, N target) {
        nodes.add(source);
        nodes.add(target);
        return edgeType.createEdge(source, target);
    }

    public Edge<N, N> getEdge(EdgeType<N, N> edgeType, N source, N target) {
        return edgeType.getEdge(source, target);
    }

    public boolean hasEdge(EdgeType<N, N> edgeType, N source, N target) {
        return getEdge(edgeType, source, target) != null;
    }

    public Set<Edge<N, N>> getOutgoingEdges(EdgeType<N, N> edgeType, N source) {
        return edgeType.getEdges().stream().filter(e -> e.getSource().equals(source)).collect(Collectors.toSet());
    }

    public Set<Edge<N, N>> getAllOutgoingEdges(N source) {
        return edgeTypes.stream()
                .flatMap(e -> getOutgoingEdges(e, source).stream())
                .collect(Collectors.toSet());
    }

    public Set<Edge<N, N>> getIncomingEdges(EdgeType<N, N> edgeType, N target) {
        return edgeType.getEdges().stream().filter(e -> e.getTarget().equals(target)).collect(Collectors.toSet());
    }

    public Set<Edge<N, N>> getAllIncomingEdges(N target) {
        return edgeTypes.stream()
                .flatMap(e -> getIncomingEdges(e, target).stream())
                .collect(Collectors.toSet());
    }

    public void removeNode(N node) {
        nodes.remove(node);
        edgeTypes.forEach(et -> removeEdges(et, node));
    }

    public void removeEdgeType(EdgeType<N, N> edgeType) {
        edgeTypes.remove(edgeType);
    }

    public void removeEdges(EdgeType<N, N> edgeType, N node) {
        edgeType.getEdges().removeIf(e -> e.getSource().equals(node) || e.getTarget().equals(node));
    }

    public void removeEdge(EdgeType<N, N> edgeType, N source, N target) {
        edgeType.getEdges().removeIf(e -> e.getSource().equals(source) && e.getTarget().equals(target));
    }

    public void removeIncomingEdges(EdgeType<N, N> edgeType, N node) {
        edgeType.getEdges().removeIf(e -> e.getTarget().equals(node));
    }

    public void removeOutgoingEdges(EdgeType<N, N> edgeType, N node) {
        edgeType.getEdges().removeIf(e -> e.getSource().equals(node));
    }

    public void removeEdges(N node) {
        edgeTypes.forEach(et -> removeEdges(et, node));
    }

    public void removeEdges(N source, N target) {
        edgeTypes.forEach(et -> removeEdge(et, source, target));
    }

    public void removeIncomingEdges(N node) {
        edgeTypes.forEach(et -> removeIncomingEdges(et, node));
    }

    public void removeOutgoingEdges(N node) {
        edgeTypes.forEach(et -> removeOutgoingEdges(et, node));
    }

    @Override
    public String toString() {
        return "Nodes:\n"
                + nodes.stream().map(Object::toString).collect(Collectors.joining(", "))
                + "\n\n"
                + edgeTypes.stream().map(Object::toString).collect(Collectors.joining("\n"));
    }
}
