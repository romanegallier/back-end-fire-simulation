package com.example.forestfire.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Configuration class for defining the properties of a forest in the simulation.
 */
@Configuration
@ConfigurationProperties(prefix = "forest")
public class ForestConfig {

    //Height of the forest
    private int height;
    //Width of the forest
    private int width;
    //Propagation probability of the fire
    private double propagationProbability;
    // Initial fire position
    private String initialFire;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public double getPropagationProbability() {
        return propagationProbability;
    }

    public void setPropagationProbability(double propagationProbability) {
        this.propagationProbability = propagationProbability;
    }

    public String getInitialFire() {
        return initialFire;
    }

    public void setInitialFire(String initialFire) {
        this.initialFire = initialFire;
    }

    public List<List<Integer>> getInitialFirePositions() {
        // Initial position of the fire
        List<List<Integer>> initialFirePositions = new ArrayList<>();
        if (initialFire != null && !initialFire.isEmpty()) {
            String[] pairs = initialFire.split(";");
            for (String pair : pairs) {
                String[] coords = pair.split(",");
                initialFirePositions.add(Arrays.asList(
                        Integer.parseInt(coords[0]),
                        Integer.parseInt(coords[1])
                ));
            }
        }
        return initialFirePositions;
    }
}
