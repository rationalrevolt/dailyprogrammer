package com.sankar.rdp.RDP188Hard;

import static com.sankar.rdp.RDP188Hard.Direction.Down;
import static com.sankar.rdp.RDP188Hard.Direction.Right;
import static com.sankar.rdp.RDP188Hard.Direction.Up;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RDP188HardTests {
    
    @Test public void
    test_grid_cell_movement() {
        Direction[][] gridData = {{Right, Up,    Right},
                                  {Right, Right, Down }};
        
        Grid grid = new Grid(gridData);
        
        assertEquals("[0,0]", grid.cellAt(0,0).toString());
        assertEquals(Right, grid.cellAt(0,0).direction());
        assertEquals(Up, grid.cellAt(0,0).next().direction());
        assertEquals(Right, grid.cellAt(0,0).next().next().direction());
        assertEquals(grid.cellAt(1,1), grid.cellAt(0,0).next().next());
    }
    
    @Test public void
    test_path_cycle_length1() {
        Direction[][] gridData = {{Right, Up,    Right},
                                  {Right, Right, Down }};

        Grid grid = new Grid(gridData);
        CycleScanner scanner = new CycleScanner(grid);
        
        assertEquals(5, scanner.cycleLengthFrom(0,0));
        assertEquals(5, scanner.cycleLengthFrom(0,1));
    }
    
    @Test public void
    test_path_cycle_length2() {
        Direction[][] gridData = {{Right, Up, Right}};

        Grid grid = new Grid(gridData);
        CycleScanner path = new CycleScanner(grid);
        
        assertEquals(1, path.cycleLengthFrom(0,0));
    }
    
    @Test public void
    finds_longest_cycle() {
        final String input = new StringBuilder()
        .append("^^v>>v^>>v<<<v>v<>>>>>>>>^vvv^^vvvv<v^^><^^v>")
        .append(">><<>vv<><<<^><^<^v^^<vv>>^v<v^vv^^v<><^>><v<")
        .append("vv<^v<v<v<vvv>v<v<vv<^<v<<<<<<<<^<><>^><^v>>>")
        .append("<v<v^^<v<>v<>v<v<^v^>^<^<<v>^v><^v^>>^^^<><^v")
        .append("^>>>^v^v^<>>vvv>v^^<^<<<><>v>>^v<^^<>v>>v<v>^")
        .append("^^^<<^<^>>^v>>>>><>>^v<^^^<^^v^v<^<v^><<^<<<>")
        .append("v<>v^vv^v<><^>v^vv>^^v^<>v^^^>^>vv<^<<v^<<>^v")
        .append("<<<<<^<vv<^><>^^>>>^^^^<^<^v^><^v^v>^vvv>^v^^")
        .append("<<v^<v<<^^v<>v>v^<<<<<>^^v<v^>>>v^><v^v<v^^^<")
        .append("^^>>^<vv<vv<>v^<^<^^><><^vvvv<<v<^<<^>^>vv^<v")
        .append("^^v^>>^>^<vv^^<>>^^v>v>>v>>v^vv<vv^>><>>v<<>>")
        .append("^v<^v<v>^^<>>^>^>^^v>v<<<<<>><><^v<^^v><v>^<<")
        .append("v>v<><^v<<^^<^>v>^><^><v^><v^^^>><^^<^vv^^^>^")
        .append("v><>^><vv^v^^>><>^<^v<^><v>^v^<^<>>^<^vv<v>^v")
        .append("><^<v>>v>^<<^>^<^^>v^^v<>>v><<>v<<^><<>^>^v<v")
        .append(">vv>^>^v><^^<v^>^>v<^v><>vv>v<^><<<<v^<^vv<>v")
        .append("<><<^^>>^<>vv><^^<vv<<^v^v^<^^^^vv<<>^<vvv^vv")
        .append(">v<<v^><v<^^><^v^<<<>^<<vvvv^^^v<<v>vv>^>>^<>")
        .append("^^^^<^<>^^vvv>v^<<>><^<<v>^<<v>>><>>><<^^>vv>")
        .append("<^<^<>vvv^v><<<vvv<>>>>^<<<^vvv>^<<<^vv>v^><^")
        .toString();
        
        Grid grid = makeGrid(input, 45, 20);
        CycleScanner cycleScanner = new CycleScanner(grid);
        
        cycleScanner.scanAllCycles();
        Cycle longest = cycleScanner.longestCycle();
        
        printGridRepresentation(longest.representation(), 45, 20);
        
        assertEquals(44, cycleScanner.longestCycle().length());
    }
    
    private Grid makeGrid(String input, int width, int height) {
        Direction[][] data = new Direction[height][width];
        
        for(int y = 0; y < height; y++)
            for(int x = 0; x < width; x++)
                data[y][x] = Direction.fromSymbol(input.charAt(y*width + x));
        
        return new Grid(data);
    }
    
    private void printGridRepresentation(String input, int width, int height) {
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                if (x == 0) System.out.print('[');
                System.out.print(input.charAt(y*width + x));
                if (x == width - 1) System.out.print(']');
            }
            System.out.println();
        }
    }

}
