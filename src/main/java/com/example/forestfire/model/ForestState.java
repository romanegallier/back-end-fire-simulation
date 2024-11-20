package com.example.forestfire.model;


/**
 * The ForestState class represents the state of a forest fire simulation.
 * It includes the height and width of the forest grid, and the state of each cell within the grid.
 */
public class ForestState {

    private final int height;
    private final int width;
    private final CellState[][] grid;

    public ForestState(int height, int width, CellState[][] grid) {
        this.height = height;
        this.width = width;
        this.grid = grid;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public CellState[][] getGrid() {
        return grid;
    }

}
