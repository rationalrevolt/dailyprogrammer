package com.sankar.rdp.RDP188Hard;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import com.sankar.rdp.RDP188Hard.Grid.Cell;

class CycleScanner {
    
    static final int NOT_SCANNED = -1;
    static final int NOT_PART_OF_ANY_CYCLE = 0;
    
    private Grid grid;
    private int[][] cycleData;
    private Set<Cycle> cycles = new TreeSet<>();
    
    private int cycleId;
    
    private ScanProgress scanProgress = new ScanProgress() {
        @Override
        public void scanned(Cell cell, boolean partOfCycle) {
            cycleData[cell.y()][cell.x()] = partOfCycle ? cycleId : NOT_PART_OF_ANY_CYCLE;
        }
        public void completedCycle(Cell start, int length) {
            cycles.add(new Cycle(cycleId++, start, length));
        }
    };
    
    private ScanProgress dummyScanProgress = new ScanProgress() {
        @Override public void scanned(Cell cell, boolean partOfCycle) {}
        @Override public void completedCycle(Cell cycleStart, int cycleLength) {}
    };
    
    CycleScanner(Grid grid) {
        this.grid = grid;
        this.cycleData = new int[grid.height()][grid.width()];
        
        initCycles();
    }
    
    private void initCycles() {
        cycleId = 1;
        
        cycles.clear();
        
        for(int y = 0; y < grid.height(); y++)
            for(int x = 0; x < grid.width(); x++)
                cycleData[y][x] = NOT_SCANNED;
    }
    
    void scanAllCycles() {
        initCycles();
        
        for(int y = 0; y < grid.height(); y++)
            for(int x = 0; x < grid.width(); x++)
                if (cycleData[y][x] == NOT_SCANNED) {
                    scan(x, y, scanProgress);
                }
    }
    
    int cycleLengthFrom(int x, int y) {
        return scan(x, y, dummyScanProgress);
    }
    
    int scan(int x, int y, ScanProgress progress) {
        final boolean PART_OF_CYCLE = true;
        final boolean NOT_PART_OF_CYCLE = false;
        
        Route route = new Route();
        Cell startCell = grid.cellAt(x, y);
        
        Cell cell = startCell;
        while(!route.contains(cell)) {
            route.add(cell);
            cell = cell.next();
        }
        
        int cycleLength = 1;
        Cell cycleStart = cell;
        
        cell = startCell;
        
        while(!cell.equals(cycleStart)) {
            progress.scanned(cell, NOT_PART_OF_CYCLE);
            cell = cell.next();
        }
        
        cell = cycleStart;
        
        progress.scanned(cell, PART_OF_CYCLE);
        while(!cell.next().equals(cycleStart)) {
            cycleLength = cycleLength + 1;
            cell = cell.next();
            progress.scanned(cell, PART_OF_CYCLE);
        }
        
        progress.completedCycle(cycleStart, cycleLength);
        
        return cycleLength;
    }
    
    Cycle longestCycle() {
        return cycles.iterator().next();
    }
    
    private static class Route {
        Set<Cell> cells = new HashSet<>();
        
        void add(Cell cell) {
            cells.add(cell);
        }
        
        boolean contains(Cell cell) {
            return cells.contains(cell);
        }
    }
    
    static interface ScanProgress {
        public void scanned(Cell cell, boolean partOfCycle);
        public void completedCycle(Cell cycleStart, int cycleLength);
    }
}
