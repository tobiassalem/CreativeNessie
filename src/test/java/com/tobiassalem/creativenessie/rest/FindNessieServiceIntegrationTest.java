package com.tobiassalem.creativenessie.rest;

import com.tobiassalem.creativenessie.model.NessieSimulationResult;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Integration test for the FindNessieService.
 *
 * Initial choice has a win probability of 1/3 = 33.33 %
 * Switch choice - given that we are presented a non-win option - has a win probability of 2/3 = 66%
 *
 * Thus it is always better to switch your choice.
 * Note that the switch choice is GIVEN  that we are presented a non-win option.
 * This is the heart of the Monty Hall problem.
 *
 * For a full motivation run the test montyHallStatisticsMotivation.
 *
 * @author Tobias
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FindNessieServiceIntegrationTest {

    private static final int SIMULATION_SESSION_SIZE = 1000;
    private static final double THEORY_PROBABILIY_WON_WITH_LAKE_KEPT = 33.33;
    private static final double THEORY_PROBABILIY_WON_WITH_LAKE_SWITCHED = 66.66;
    private static final double ACCEPTED_DELTA = 4.0;

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testSpringBoot() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
        ResponseEntity<String> response = template.getForEntity(base.toString(),
                String.class);
        assertThat(response.getBody(), equalTo("Greetings from Spring Boot!"));
    }

    @Test
    public void montyHallStatisticsMotivation() throws Exception {
        FindNessieSimulationSessionResult sessionResult = new FindNessieSimulationSessionResult();
        sessionResult.reportGameMotivation();
    }

    @Test
    public void findNessieSimulationSession() throws Exception {
        this.base = new URL("http://localhost:" + port + "/runFindNessieSimulation" +"?number=1&switchLake=false");

        FindNessieSimulationSessionResult sessionResult = new FindNessieSimulationSessionResult();

        // Run 1000 simulations where we KEEP our lake choice
        sessionResult.addAll(runNessieSimulations(false));

        // Run 1000 simulations where we SWITCH our lake choice
        sessionResult.addAll(runNessieSimulations(true));

        double percentageWonWithLakeKept = sessionResult.getPercentageWonWithLakeKept();
        double percentageWonWithLakeSwitched = sessionResult.getPercentageWonWithLakeSwitched();

        // Assert KEEPING our lake choice results in ~33% chance of winning, +/- the accepted delta
        assertThat("percentageWonWithLakeKept", percentageWonWithLakeKept, greaterThanOrEqualTo(getAcceptedMinProbabilityWithChoiceKept()));
        assertThat("percentageWonWithLakeKept", percentageWonWithLakeKept, lessThanOrEqualTo(getAcceptedMaxProbabilityWithChoiceKept()));

        // Assert SWITCHING our lake choice results in ~66% chance of winning, +/- the accepted delta
        assertThat("percentageWonWithLakeSwitched", percentageWonWithLakeSwitched, greaterThanOrEqualTo(getAcceptedMinProbabilityWithChoiceSwitched()));
        assertThat("percentageWonWithLakeSwitched", percentageWonWithLakeSwitched, lessThanOrEqualTo(getAcceptedMaxProbabilityWithChoiceSwitched()));

        sessionResult.reportGameSessionStatistics();
    }

    private List<NessieSimulationResult> runNessieSimulations(boolean switchLake) throws MalformedURLException {
        this.base = new URL("http://localhost:" + port + "/runFindNessieSimulation" +"?number=1&switchLake="+switchLake);
        List<NessieSimulationResult> resultList = new ArrayList<>();

        for (int i = 1; i <= SIMULATION_SESSION_SIZE; i++) {
            ResponseEntity<NessieSimulationResult> response = template.getForEntity(base.toString(),
                    NessieSimulationResult.class);
            NessieSimulationResult result = response.getBody();
            assertNotNull(result);
            resultList.add(result);
        }
        return resultList;
    }

    private double getAcceptedMinProbabilityWithChoiceKept() {
        return THEORY_PROBABILIY_WON_WITH_LAKE_KEPT - ACCEPTED_DELTA;
    }

    private double getAcceptedMaxProbabilityWithChoiceKept() {
        return THEORY_PROBABILIY_WON_WITH_LAKE_KEPT + ACCEPTED_DELTA;
    }

    private double getAcceptedMinProbabilityWithChoiceSwitched() {
        return THEORY_PROBABILIY_WON_WITH_LAKE_SWITCHED - ACCEPTED_DELTA;
    }

    private double getAcceptedMaxProbabilityWithChoiceSwitched() {
        return THEORY_PROBABILIY_WON_WITH_LAKE_SWITCHED + ACCEPTED_DELTA;
    }
}
