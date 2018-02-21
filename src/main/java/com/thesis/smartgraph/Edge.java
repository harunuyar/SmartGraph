package com.thesis.smartgraph;

import java.util.Objects;

public class Edge<S, T> {

    private EdgeType<S, T> type;
    private S source;
    private T target;

    protected Edge(EdgeType<S, T> type, S source, T target) {
        this.type = type;
        this.source = source;
        this.target = target;
    }

    public EdgeType<S, T> getType() {
        return type;
    }

    public S getSource() {
        return source;
    }

    public T getTarget() {
        return target;
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this || (
                obj instanceof Edge
                        && ((Edge) obj).getType().equals(type)
                        && ((Edge) obj).getSource().equals(source)
                        && ((Edge) obj).getTarget().equals(target)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, source, target);
    }

    @Override
    public String toString() {
        return type.getName() + "(" + getSource() + ", " + getTarget() + ")";
    }
}
