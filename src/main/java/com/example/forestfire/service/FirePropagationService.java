package com.example.forestfire.service;

import com.example.forestfire.config.ForestConfig;
import com.example.forestfire.model.CellState;
import com.example.forestfire.model.ForestCell;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for managing the fire propagation simulation
 * in a forest grid.
 */
@Service
public class FirePropagationService {

    private CellState[][] forest;
    private final ForestConfig forestConfig;

    public FirePropagationService(ForestConfig forestConfig) {
        this.forestConfig = forestConfig;
    }

    /**
     * Initialize the forrest from the configuration file
     * @return the state of the forrest
     */
    public CellState[][] initializeForest() {
        //Create empty forest
        forest = new CellState[forestConfig.getHeight()][forestConfig.getWidth()];
        for (int i = 0; i < forestConfig.getHeight(); i++) {
            for (int j = 0; j < forestConfig.getWidth(); j++) {
                forest[i][j] = CellState.EMPTY;
            }
        }
        //Set fire to inital position
        for (List<Integer> position : forestConfig.getInitialFirePositions()) {
            forest[position.get(0)][position.get(1)]=CellState.BURNING;
        }

        return forest;
    }

    /**
     * Simulate a step of forrest fire
     * @return a list of updated cell
     */
    public List<ForestCell> simulateStep() {
        List<ForestCell> updatedCell = new ArrayList<>();
        for (int i = 0; i < forestConfig.getHeight(); i++) {
            for (int j = 0; j < forestConfig.getWidth(); j++) {
                if (forest[i][j] == CellState.BURNING) {
                    updatedCell.add(new ForestCell(i,j, CellState.ASH));
                    updatedCell.addAll(propagateFire(i, j));
                }
            }
        }
        for (ForestCell cell : updatedCell) {
            this.forest[cell.getX()][cell.getY()]= cell.getState();
        }
        return updatedCell;
    }

    /**
     * Propagate the fire to adjacent cell
     * @param x x of the cell currently on fire
     * @param y y of the cell currently on fire
     * @return the affected cell
     */
    private List<ForestCell> propagateFire(int x, int y) {
        List<ForestCell> updatedCell = new ArrayList<>();
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= 0 && ny >= 0 && nx < forestConfig.getHeight() && ny < forestConfig.getWidth() &&
                    forest[nx][ny] ==CellState.EMPTY &&
                    Math.random() < forestConfig.getPropagationProbability()) {
                updatedCell.add(new ForestCell(nx,ny,CellState.BURNING));

            }
        }
        return updatedCell;
    }

    public CellState[][] getForestGrid() {
        return forest;
    }
}

