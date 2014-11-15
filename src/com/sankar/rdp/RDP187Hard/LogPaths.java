package com.sankar.rdp.RDP187Hard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class LogPaths {
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