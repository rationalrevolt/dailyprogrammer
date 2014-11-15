package com.sankar.rdp.RDP188Hard;


class Grid {
    private Direction[][] grid;
    private int width;
    private int height;
    
    Grid(Direction[][] data) {
        this.grid = data;
        this.width = data[0].length;
        this.height = data.length;
    }
    
    int width() {
        return width;
    }
    
    int height() {
        return height;
    }
    
    Cell cellAt(int x, int y) {
        return new Cell(x,y);
    }
    
    Direction directionOf(int x, int y) {
        return grid[y][x];
    }
    
    class Cell {
        private final int x;
        private final int y;
        
        Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        Cell next() {
            switch(direction()) {
                case Left : return x == 0 ? new Cell(width - 1, y) : new Cell(x-1, y);
                case Right: return x == width - 1 ? new Cell(0,y) : new Cell(x+1, y);
                case Up   : return y == 0 ? new Cell(x, height - 1) : new Cell(x, y-1);
                case Down : return y == height - 1 ? new Cell(x,0) : new Cell(x, y+1); 
            }
            throw new AssertionError();
        }
        
        int x() {
            return x;
        }
        
        int y() {
            return y;
        }
        
        int index() {
            return (y * width) + x;
        }
        
        Grid grid() {
            return Grid.this;
        }
        
        Direction direction() {
            return directionOf(x, y);
        }
        
        @Override
        public boolean equals(Object o) {
            if (o == null) return false;
            if (!(o instanceof Cell)) return false;
            
            Cell other = (Cell)o;
            return x == other.x && y == other.y;
        }
        
        @Override
        public int hashCode() {
            return x ^ y;
        }
        
        @Override
        public String toString() {
            return String.format("[%d,%d]", x, y);
        }
    }

}
