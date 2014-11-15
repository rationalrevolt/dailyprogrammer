package com.sankar.rdp.RDP188Hard;


class Cycle implements Comparable<Cycle> {
    private final int cycleId;
    private final Grid.Cell cycleStart;
    private final int length;
    
    Cycle(int cycleId, Grid.Cell cycleStart, int length) {
        this.cycleId = cycleId;
        this.cycleStart = cycleStart;
        this.length = length;
    }
    
    int cycleId() {
        return cycleId;
    }

    Grid.Cell cycleStart() {
        return cycleStart;
    }

    int length() {
        return length;
    }
    
    public String representation() {
        Grid grid = cycleStart.grid();
        
        int width = grid.width();
        int height = grid.height();
        
        StringBuilder buff = new StringBuilder();
        
        for(int y = 0; y < height; y++)
            for(int x = 0; x < width; x++)
                buff.append('.');
        
        Grid.Cell cell = cycleStart;
        
        buff.setCharAt(cell.index(), cell.direction().code());
        while(!cell.next().equals(cycleStart)) {
            cell = cell.next();
            buff.setCharAt(cell.index(), cell.direction().code());
        }
        
        return buff.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Cycle)) return false;
        
        Cycle other = (Cycle)o;
        return cycleId == other.cycleId && 
               cycleStart.equals(other.cycleStart) &&
               length == other.length;
    }
    
    @Override
    public int hashCode() {
        int x = cycleId;
        int y = cycleStart.hashCode();
        int z = length;
        
        int result = (int) (x ^ (x >>> 32));
        result = 31 * result + (int) (y ^ (y >>> 32));
        result = 31 * result + (int) (z ^ (z >>> 32));
        
        return result;
    }

    @Override
    public int compareTo(Cycle o) {
        return ((Integer)length).compareTo(o.length) * -1;
    }

}
