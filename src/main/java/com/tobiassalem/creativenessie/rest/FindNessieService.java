package com.tobiassalem.creativenessie.rest;

import com.tobiassalem.creativenessie.model.NessieSimulation;
import com.tobiassalem.creativenessie.model.NessieSimulationResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Class defining the Find Nessie Service.
 *
 * @author Tobias
 */
@RestController
public class FindNessieService {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/runFindNessieSimulation")
    public NessieSimulationResult runFindNessieSimulation(@RequestParam(value="number") Integer lakeNumber, @RequestParam(value="switchLake") boolean shouldSwitchLake) {
        return new NessieSimulation().runFindNessieSimulation(lakeNumber, shouldSwitchLake);
    }

}
