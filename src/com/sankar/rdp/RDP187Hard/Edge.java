package com.sankar.rdp.RDP187Hard;

import java.util.ArrayList;
import java.util.List;

class Edge {
    private int capacity;
    private Node node;
    
    public Edge(int capacity, Node node) {
        this.capacity = capacity;
        this.node = node;
    }
    
    public int capacity() {
        return capacity;
    }
    
    public Node node() {
        return node;
    }
    
    public List<Path> pathsFrom(Node from) {
        List<Path> paths = new ArrayList<>();
        
        for(Path p : node.paths()) {
            Path newPath = new Path(from);
            newPath.add(this);
            newPath.append(p);
            paths.add(newPath);
        }
        
        if (paths.isEmpty()) {
            Path newPath = new Path(from);
            newPath.add(this);
            paths.add(newPath);
        }
        
        return paths;
    }
    
}