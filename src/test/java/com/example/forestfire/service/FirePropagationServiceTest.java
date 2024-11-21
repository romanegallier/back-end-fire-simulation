package com.example.forestfire.service;

import com.example.forestfire.config.ForestConfig;
import com.example.forestfire.model.CellState;
import com.example.forestfire.model.ForestCell;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.List;

@SpringBootTest
public class FirePropagationServiceTest {
    @Autowired
    private FirePropagationService firePropagationService;

    @MockBean
    private ForestConfig forestConfig;

    @Test
    public void testSimulateStep_NoPropagation() {
        when(forestConfig.getHeight()).thenReturn(3);
        when(forestConfig.getWidth()).thenReturn(3);
        when(forestConfig.getInitialFirePositions()).thenReturn(List.of(List.of(1, 1)));
        when(forestConfig.getPropagationProbability()).thenReturn(0.0);

        firePropagationService.initializeForest();
        List<ForestCell> updatedCells = firePropagationService.simulateStep();

        assertNotNull(updatedCells);
        assertEquals(1, updatedCells.size());  // Only the initial burning cell should turn to ash
        assertEquals(CellState.ASH, updatedCells.get(0).getState());
        assertEquals(1, updatedCells.get(0).getX());
        assertEquals(1, updatedCells.get(0).getY());
    }

    @Test
    public void testSimulateStep_FullPropagation() {
        when(forestConfig.getHeight()).thenReturn(3);
        when(forestConfig.getWidth()).thenReturn(3);
        when(forestConfig.getInitialFirePositions()).thenReturn(List.of(List.of(1, 1)));
        when(forestConfig.getPropagationProbability()).thenReturn(1.0);

        firePropagationService.initializeForest();
        List<ForestCell> updatedCells = firePropagationService.simulateStep();

        assertNotNull(updatedCells);
        assertEquals(5, updatedCells.size());
        assertEquals(CellState.ASH, updatedCells.get(0).getState());
        assertEquals(1, updatedCells.get(0).getX());
        assertEquals(1, updatedCells.get(0).getY());

        // Check that fire propagated to all neighboring cells
        CellState[][] grid = firePropagationService.getForestGrid();
        assertEquals(CellState.EMPTY, grid[0][0]);
        assertEquals(CellState.BURNING, grid[0][1]);
        assertEquals(CellState.EMPTY, grid[0][2]);
        assertEquals(CellState.BURNING, grid[1][0]);
        assertEquals(CellState.ASH, grid[1][1]);
        assertEquals(CellState.BURNING, grid[1][2]);
        assertEquals(CellState.EMPTY, grid[2][0]);
        assertEquals(CellState.BURNING, grid[2][1]);
        assertEquals(CellState.EMPTY, grid[2][2]);
    }

    @Test
    public void testInitializeForest_EmptyForest() {
        when(forestConfig.getHeight()).thenReturn(3);
        when(forestConfig.getWidth()).thenReturn(3);
        when(forestConfig.getInitialFirePositions()).thenReturn(List.of());

        CellState[][] forestState = firePropagationService.initializeForest();

        assertNotNull(forestState);
        assertEquals(3, forestState.length);
        assertEquals(3, forestState[0].length);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                assertEquals(CellState.EMPTY, forestState[i][j]);
            }
        }
    }

    @Test
    public void testInitializeForest_WithInitialFirePositions() {
        when(forestConfig.getHeight()).thenReturn(3);
        when(forestConfig.getWidth()).thenReturn(3);
        when(forestConfig.getInitialFirePositions()).thenReturn(List.of(
                List.of(0, 0), List.of(1, 1), List.of(2, 2)
        ));

        CellState[][] forestState = firePropagationService.initializeForest();

        assertNotNull(forestState);
        assertEquals(3, forestState.length);
        assertEquals(3, forestState[0].length);
        assertEquals(CellState.BURNING, forestState[0][0]);
        assertEquals(CellState.BURNING, forestState[1][1]);
        assertEquals(CellState.BURNING, forestState[2][2]);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if ((i == 0 && j == 0) || (i == 1 && j == 1) || (i == 2 && j == 2)) {
                    continue;
                }
                assertEquals(CellState.EMPTY, forestState[i][j]);
            }
        }
    }


}