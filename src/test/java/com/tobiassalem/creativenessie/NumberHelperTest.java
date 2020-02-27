package com.tobiassalem.creativenessie;

import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.*;

/**
 * Unit tests for the NumberHelper
 *
 * @author Tobias
 */
public class NumberHelperTest {

    private static final double DELTA = 1e-15;

    @Test
    public void getRandomIntExlusiveUpperBound() throws Exception {
        for (int i = 0; i < 10; i++) {
            int randomInt = NumberHelper.getRandomIntExclusiveUpperBound(1, 5);
            assertThat("randomInt lower bound", randomInt, greaterThan(0));
            assertThat("randomInt upper bound", randomInt, lessThan(5));
        }
    }

    @Test
    public void getRandomIntInclusiveUpperBound() throws Exception {
        for (int i = 0; i < 10; i++) {
            int randomInt = NumberHelper.getRandomIntInclusiveUpperBound(1, 5);
            assertThat("randomInt lower bound", randomInt, greaterThan(0));
            assertThat("randomInt upper bound", randomInt, lessThanOrEqualTo(5));
        }
    }

    @Test
    public void calculatPercentage() throws Exception {
        int nominator = 42;
        int denominator = 100;
        double percentage = NumberHelper.calculatePercentage(nominator, denominator);
        assertEquals(42.0, percentage, DELTA);
    }

    @Test
    public void calculatPercentageDoubleVersion() throws Exception {
        double nominator = 42.0;
        double denominator = 100.0;
        double percentage = NumberHelper.calculatePercentage(nominator, denominator);
        assertEquals(42.0, percentage, DELTA);

    }

}