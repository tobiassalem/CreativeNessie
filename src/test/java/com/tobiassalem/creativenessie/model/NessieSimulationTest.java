package com.tobiassalem.creativenessie.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for the NessieSimulation
 *
 * @author Tobias
 */
public class NessieSimulationTest {

    NessieSimulation sim;

    @Before
    public void setup() {
        sim = new NessieSimulation();
    }

    @Test
    public void runFindNessieSimulationWithKeep() throws Exception {
        NessieSimulationResult result = sim.runFindNessieSimulation(1, false);
        assertNotNull(result);
        assertFalse(result.isLakeChoiceSwitched());
    }

    @Test
    public void runFindNessieSimulationWithSwitch() throws Exception {
        NessieSimulationResult result = sim.runFindNessieSimulation(1, true);
        assertNotNull(result);
        assertTrue(result.isLakeChoiceSwitched());
    }

}