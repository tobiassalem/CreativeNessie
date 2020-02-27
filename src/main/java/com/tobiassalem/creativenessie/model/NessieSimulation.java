package com.tobiassalem.creativenessie.model;

import com.tobiassalem.creativenessie.NumberHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for simulating one round of finding Nessie in one of the lakes.
 *
 * @author Tobias
 */
public class NessieSimulation {

    private static final int NR_OF_LAKES = 3;

    private Map<Integer, Lake> lakes;
    private Integer luckyLake;
    private Integer currentLakeChoice;

    public NessieSimulation() {
        lakes = buildLakes();
        luckyLake = placeNessieInRandomLake();
        currentLakeChoice = null;
    }

    public NessieSimulationResult runFindNessieSimulation(int lakeToSearch, boolean shouldSwitchLake) {
        boolean nessieFound = findNessie(lakeToSearch);

        if (shouldSwitchLake) {
            Integer lakePresentedAsNonLucky = findRandomNonLuckyNonSearchedLake();
            nessieFound = findNessie(findLakeToSwitchTo(lakePresentedAsNonLucky));
        }

        return new NessieSimulationResult(nessieFound, shouldSwitchLake);
    }

    /**
     * Try to find Nessie in the lake with the given lake number.
     *
     * @param lakeNumber the number of the lake to search
     * @return true if Nessie is found, false otherwise.
     */
    public boolean findNessie(int lakeNumber) {
        if (!isValidLakeSelection(lakeNumber)) {
            return false;
        }
        currentLakeChoice = lakeNumber;
        return lakes.get(lakeNumber).isNessieInThisLake();
    }

    private Integer placeNessieInRandomLake() {
        int luckyLake = NumberHelper.getRandomIntExclusiveUpperBound(1, 4);
        lakes.get(luckyLake).setNessieInThisLake(true);
        return luckyLake;
    }

    private Map<Integer, Lake> buildLakes() {
        Map<Integer, Lake> lakes = new HashMap<>(3);
        for (int i = 1; i <= NR_OF_LAKES; i++) {
            lakes.put(i, new Lake(i));
        }
        return lakes;
    }

    private Integer findLakeToSwitchTo(Integer lakePresentedAsNonLucky) {
        Integer lakeCandidate = null;
        while (!isValidLakeToSearchNext(lakeCandidate, lakePresentedAsNonLucky)) {
            lakeCandidate = NumberHelper.getRandomIntInclusiveUpperBound(1, lakes.size());
        }
        return lakeCandidate;
    }

    /**
     * From the set of all lakes, find a random non-lucky and non-searched lake.
     * That is:
     * 1) the lake should NOT contain Nessie
     * 2) the lake should NOT already be selected for search
     * <p>
     * In the classic Monty Hall problem this corresponds to:
     * From the set of all doors, select a random non-price door which is not already selected by the player.
     * Then select one remaining - always non-winning - door at random.
     *
     * @return the number of the lake with the stated criteria
     */
    private Integer findRandomNonLuckyNonSearchedLake() {

        Integer randomLake = null;
        while (!isValidLakeToPresentAsNonLucky(randomLake)) {
            randomLake = NumberHelper.getRandomIntInclusiveUpperBound(1, lakes.size());
        }

        return randomLake;
    }

    private boolean isValidLakeSelection(Integer lakeSelection) {
        return lakeSelection != null && lakeSelection >= 1 && lakeSelection <= NR_OF_LAKES;
    }

    private boolean isValidLakeToPresentAsNonLucky(Integer lakeToSearch) {
        return lakeToSearch != null && !lakeToSearch.equals(currentLakeChoice) && !lakeToSearch.equals(luckyLake);
    }

    private boolean isValidLakeToSearchNext(Integer lakeToSearch, Integer lakePresentedAsNonLucky) {
        return lakeToSearch != null && !lakeToSearch.equals(currentLakeChoice) && !lakeToSearch.equals(lakePresentedAsNonLucky);
    }
}
