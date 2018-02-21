package com.thesis.smartgraph;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class EdgeType<S, T> {

    private String name;
    private Set<Edge<S, T>> edges;

    protected EdgeType(String name) {
        this.name = name;
        edges = new HashSet<>();
    }

    protected Edge<S, T> createEdge(S source, T target) {
        Edge<S, T> edge = getEdge(source, target);
        if (edge != null)
            return edge;
        edge = new Edge<>(this, source, target);
        edges.add(edge);
        return edge;
    }

    public Edge<S, T> getEdge(S source, T target) {
        return edges.stream()
                .filter(e -> e.getSource().equals(source) && e.getTarget().equals(target))
                .findFirst()
                .orElse(null);
    }

    public Set<Edge<S, T>> getEdges() {
        return edges;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || (
                obj instanceof EdgeType
                        && ((EdgeType) obj).getName().equals(name)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Edge '"+ name + "'\n"
                + edges.stream()
                        .map(e -> "(" + e.getSource() + ", " + e.getTarget() + ")")
                        .collect(Collectors.joining("\n"));
    }

}
