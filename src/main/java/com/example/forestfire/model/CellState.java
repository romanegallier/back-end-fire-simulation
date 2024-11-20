package com.example.forestfire.model;

/**
 * The CellState enum represents the possible states of a cell
 * in a forest fire simulation.
 * States:
 * - EMPTY: The cell has not burned yet.
 * - BURNING: The cell is currently in fire.
 * - ASH: The cell has burned and is now in ash state.
 */
public enum CellState {
    EMPTY, // Not burned yet
    BURNING, // In fire
    ASH // Burned
}