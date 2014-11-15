package com.sankar.rdp.RDP188Hard;

enum Direction {
    Left, Right, Up, Down;
    
    static Direction fromSymbol(char symbol) {
        switch(symbol) {
            case '<': return Left;
            case '>': return Right;
            case '^': return Up;
            case 'v': return Down;
        }
        throw new AssertionError();
    }
    
    char code() {
        switch(this) {
            case Left : return '<';
            case Right: return '>';
            case Up   : return '^';
            case Down : return 'v';
        }
        throw new AssertionError();
    }
}
