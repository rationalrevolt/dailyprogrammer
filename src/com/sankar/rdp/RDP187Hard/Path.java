package com.sankar.rdp.RDP187Hard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sankar.rdp.RDP187Hard.Path;

class Path implements Comparable<Path> {
    private Node start;
    private int capacity = Integer.MAX_VALUE;
    private List<Edge> edges = new ArrayList<>();
    private Set<Edge> limitingEdges = new HashSet<>();
    
    public Path(Node start) {
        this.start = start;
    }
    
    public void add(Edge edge) {
        edges.add(edge);
        
        if (capacity > edge.capacity()) {
            capacity = edge.capacity();
            limitingEdges.clear();
            limitingEdges.add(edge);
        }
        
        else if (capacity == edge.capacity()) {
            limitingEdges.add(edge);
        }
        
    }
    
    public void append(Path other) {
        for(Edge e : other.edges) add(e);
    }

    @Override
    public int compareTo(Path o) {
        return ((Integer)edges.size()).compareTo(o.edges.size());
    }
    
    public int capacity() {
        return capacity;
    }
    
    public Set<Edge> limitingEdges() {
        return limitingEdges;
    }
    
    public boolean contains(Edge e) {
        return edges.contains(e);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        sb.append(start.name());
        for(Edge e : edges) sb.append("->").append(e.node().name());
        sb.append(" - path of ").append(edges.size() + 1);
        
        return sb.toString();
    }
}