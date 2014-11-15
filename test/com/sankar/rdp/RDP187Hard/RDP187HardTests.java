package com.sankar.rdp.RDP187Hard;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RDP187HardTests {

    @Test
    public void test_simple_path() {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        
        a.addEdge(new Edge(1,b));
        b.addEdge(new Edge(1,c));
        
        assertEquals(1, a.paths().size());
    }
    
    @Test
    public void test_path_with_single_branch() {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        
        a.addEdge(new Edge(1,b));
        a.addEdge(new Edge(1,c));
        
        assertEquals(2, a.paths().size());
    }
    
    @Test
    public void test_path_for_tree() {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");
        Node g = new Node("G");
        
        a.addEdge(new Edge(1,b));
        a.addEdge(new Edge(1,c));
        
        b.addEdge(new Edge(1, d));
        b.addEdge(new Edge(1, e));
        
        c.addEdge(new Edge(1, f));
        c.addEdge(new Edge(1, g));
        
        assertEquals(4, a.paths().size());
    }
    
    @Test
    public void path_tracks_limiting_edges() {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        
        a.addEdge(new Edge(3,b));
        b.addEdge(new Edge(1,c));
        c.addEdge(new Edge(2,d));
        
        Path p = a.paths().get(0);
        
        assertEquals(1, p.capacity());
    }
    
    @Test
    public void test_path_to_string() {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        
        a.addEdge(new Edge(1,b));
        b.addEdge(new Edge(1,c));
        
        Path p = a.paths().get(0);
        
        assertEquals("A->B->C - path of 3", p.toString());
    }
    
    @Test
    public void test_log_path() {
        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");
        Node e = new Node("E");
        Node f = new Node("F");
        Node g = new Node("G");
        Node h = new Node("H");
        Node i = new Node("I");
        
        connect(a,b,6);
        connect(a,c,2);
        connect(b,e,3);
        connect(b,d,3);
        connect(d,c,2);
        connect(d,f,1);
        connect(c,g,5);
        connect(e,h,1);
        connect(e,i,2);
        connect(f,h,1);
        connect(g,h,2);
        connect(g,i,2);
        connect(h,i,4);
        
        LogPaths logPaths = LogPaths.computeLogPaths(a.paths());
        logPaths.print();
        
        assertEquals(8, logPaths.maxLogsAccomodated());
    }
    
    private static void connect(Node from, Node to, int capacity) {
        from.addEdge(new Edge(capacity, to));
    }

}
