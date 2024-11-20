package com.example.forestfire.controler;

import com.example.forestfire.model.ForestCell;
import com.example.forestfire.model.ForestState;
import com.example.forestfire.service.FirePropagationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * This controller handles requests related to the simulation of forest fires.
 * It provides endpoints to initialize the forest and simulate the propagation of the fire step by step.
 */
@RestController
@RequestMapping("/api/forest")
public class ForestFireController {

    @Autowired
    private FirePropagationService firePropagationService;

    //Initialise the fire
    @GetMapping("/initialize")
    public ForestState initialize() {
        return firePropagationService.initializeForest();

    }

    // Provide the next step of the fire
    @GetMapping("/step")
    public List<ForestCell> nextStep() {
        return firePropagationService.simulateStep();
    }
}
