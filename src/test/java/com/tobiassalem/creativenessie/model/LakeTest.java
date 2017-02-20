package com.tobiassalem.creativenessie.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit test for the Lake class
 *
 * @author Tobias
 */
public class LakeTest {

    private static final Integer LAKE_NUMBER = 42;
    Lake lake;

    @Before
    public void setup() {
        lake = new Lake(LAKE_NUMBER);
        lake.setName("Lake Loch");
        lake.setNessieInThisLake(true);
    }

    @Test
    public void getNumber() throws Exception {
        assertEquals(LAKE_NUMBER, lake.getNumber());
    }

    @Test
    public void getName() throws Exception {
        assertEquals("Lake Loch", lake.getName());
    }

    @Test
    public void setName() throws Exception {
        lake.setName("Lake Loch II");
        assertEquals("Lake Loch II", lake.getName());
    }

    @Test
    public void isNessieInThisLake() throws Exception {
        assertTrue(lake.isNessieInThisLake());
    }

    @Test
    public void setNessieInThisLake() throws Exception {
        lake.setNessieInThisLake(false);
        assertFalse(lake.isNessieInThisLake());
    }

}