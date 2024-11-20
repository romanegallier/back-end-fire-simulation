package com.example.forestfire.model;

/**
 * The ForestCell class represents a single cell in a forest fire simulation grid.
 * Each cell has a specific state, as well as x and y coordinates that denote
 * its position within the forest grid.
 */
public class ForestCell {
    private final CellState state;
    private final int x;
    private final int y;

    public ForestCell(int x, int y, CellState cellState) {
        this.x=x;
        this.y = y;
        this.state= cellState;
    }

    public CellState getState() {
        return this.state;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}