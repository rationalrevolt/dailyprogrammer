package com.sankar.rdp.RDP187Hard;

import java.util.ArrayList;
import java.util.List;

class Node {
    private String name;
    private List<Edge> edges = new ArrayList<>();
    
    public Node(String name) {
        this.name = name;
    }
    
    public String name() {
        return name;
    }
    
    public void addEdge(Edge e) {
        edges.add(e);
    }
    
    public List<Path> paths() {
        List<Path> paths = new ArrayList<>();
        
        for(Edge e : edges)
            paths.addAll(e.pathsFrom(this));
        
        return paths;
    }
}