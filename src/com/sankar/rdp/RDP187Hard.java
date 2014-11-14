package com.sankar.rdp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RDP187Hard {
    
    static class Node {
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
    
    static class Edge {
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
    
    static class Path implements Comparable<Path> {
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
            
            else if (capacity == edge.capacity) {
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
    
    static class LogPaths {
        private List<Path> paths;
        
        public LogPaths(List<Path> paths) {
            this.paths = paths;
        }
        
        public int maxLogsAccomodated() {
            int max = 0;
            
            for(Path p : paths)
                max += p.capacity();
            
            return max;
        }
        
        public void print() {
            int maxlogs = 0;
            
            for(Path p : paths) {
                int cap = p.capacity();
                while(cap-- > 0) System.out.printf("Log #%d takes %s%n", ++maxlogs, p);
            }
            
            System.out.printf("River is now full. Can send %d logs.", maxlogs);
        }
        
        public static LogPaths computeLogPaths(List<Path> paths) {
            List<Path> logPaths = new ArrayList<>(paths);
            Collections.sort(logPaths);
            
            Set<Edge> removed = new HashSet<>();
            List<Path> pathsTaken = new ArrayList<>();
            
            loop: for(Path p : logPaths) {    
                for(Edge e : removed)
                    if (p.contains(e))
                        continue loop;
                
                removed.addAll(p.limitingEdges());
                pathsTaken.add(p);
            }
            
            return new LogPaths(pathsTaken);
        }
    }

}
