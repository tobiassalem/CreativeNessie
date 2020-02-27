package com.tobiassalem.creativenessie.rest;

import com.tobiassalem.creativenessie.NumberHelper;
import com.tobiassalem.creativenessie.model.NessieSimulationResult;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for keeping data of a session of multiple simulation runs.
 *
 * @author Tobias
 */
public class FindNessieSimulationSessionResult {

    private static final String NL = System.getProperty("line.separator");

    private static final String GAME_DESC =
            "The Monty Hall Problem gets its name from the TV game show, 'Let's Make A Deal', hosted by Monty Hall. " + NL +
                    "The scenario is such: you are given the opportunity to select one closed door of three, behind one of which " + NL +
                    "there is a prize. The other two doors hide 'goats' (or some other such 'non-prize')." + NL +
                    "Once you have made your selection, Monty Hall will open one of the remaining doors, " + NL +
                    "revealing that it does not contain the prize." + NL + NL +
                    "He then asks you if you would like to switch your selection to the other unopened door, " + NL +
                    "or stay with your original choice. Here is the problem: " + NL +
                    "Does it matter if you switch? Let's find out! " + NL;

    private static final String SCENARIO1 =
            "Scenario 1: " + NL +
                    "============" + NL +
                    "You happen to choose the door with the prize - a 1/3 probability. " + NL +
                    "The game host then opens one of the doors with a goat (of which there are two in this scenario). " + NL +
                    "You switch door - to the door with the other goat, and loose. " + NL +
                    "Remember: the probability of this scenario is only 1/3 since " + NL +
                    "when you choose one of the THREE closed doors the chance of selecting the prize is 1/3 " + NL +
                    "Let's illustrate this Sceario: " + NL +
                    "Door 1 [GOAT] 	<--- closed " + NL +
                    "Door 2 [PRIZE] <--- chosen by you, chance 1/3 " + NL +
                    "Door 3 [GOAT] 	<--- closed " + NL + NL +
                    "After the game host opens one of the doors with a goat behind it: " + NL +
                    "Door 1 [GOAT] 	<--- opened by the host. " + NL +
                    "Door 2 [PRIZE] <--- first chosen by you. " + NL +
                    "Door 3 [GOAT] 	<--- closed - switch results in a loss." + NL + NL;

    private static final String SCENARIO2 =
            "Scenario 2: " + NL +
                    "============" + NL +
                    "You happen to choose a door with a goat - a 2/3 probability. " + NL +
                    "The game host then opens one of the other doors with a goat." + NL +
                    "You switch door - to the door with the prize, and win. " + NL +
                    "Remember: the probability of this type of win is 2/3 since " + NL +
                    "when you choose one of the THREE closed doors the chance of selecting a Goat is 2/3 " + NL +
                    "It does not matter which goat you select since the host will always open the door with the other goat. " + NL + NL +
                    "Let's illustrate this Sceario: " + NL +
                    "Door 1 [GOAT] 	<--- chosen by you " + NL +
                    "Door 2 [PRIZE] <--- closed " + NL +
                    "Door 3 [GOAT] 	<--- closed " + NL + NL +
                    "After the game host opens one of the doors with a goat behind it: " + NL +
                    "Door 1 [GOAT] 	<--- first chosen by you " + NL +
                    "Door 2 [PRIZE] <--- closed - the host never opens the door with the prize - switch results in a WIN. " + NL +
                    "Door 3 [GOAT] 	<--- opened - the host always opens a door with a goat. " + NL + NL;

    private static final String SCENARIO3 =
            "Scenario 3: " + NL +
                    "============" + NL +
                    "You happen to choose a door with a goat - a 2/3 probability. " + NL +
                    "The game host then opens one of the other doors with a goat. " + NL +
                    "You switch door - to the door with the prize, and win. " + NL +
                    "Remember: the probability of this type of win is 2/3 since " + NL +
                    "when you choose one of the THREE closed doors the chance of selecting a Goat is 2/3 " + NL +
                    "It does not matter which goat you select since the host will always open the door with the OTHER goat. " + NL + NL +
                    "Let's illustrate this Sceario: " + NL +
                    "Door 1 [GOAT] 	<--- closed " + NL +
                    "Door 2 [PRIZE] <--- closed " + NL +
                    "Door 3 [GOAT] 	<--- chosen by you " + NL + NL +
                    "After the game host opens one of the doors with a goat behind it: " + NL +
                    "Door 1 [GOAT] 	<--- opened - the host always opens a door with a goat. " + NL +
                    "Door 2 [PRIZE] <--- closed - the host never opens the door with the prize - switch results in a WIN. " + NL +
                    "Door 3 [GOAT] 	<--- first chosen by you" + NL + NL;

    private static final String GAME_CHOICE_MOTIVATION =
            "In your initial choice of door you clearly have 1/3 (or 33,33...%) chance of selecting " + NL +
                    "the door with the price, and thus 2/3 (or 66,66...%) chance of selecting a door with a goat. " + NL +
                    "The game host KNOWS which door has the prize and NEVER will open the door with the prize " + NL +
                    "- he will always open a goat door and never the prize door." + NL +
                    "This means that unless you chose the door with the prize -which we already know is a 1/3 chance- the remaining closed door " + NL +
                    "actually has the grand prize. This with a probability of 2/3. " + NL +
                    "Thus by SWITCHING door you have a 2/3 probability of winning the prize! " + NL + NL +
                    "You may think to yourself 'well I don't know if that's true or not, so lets go through each scenario step by step " + NL +
                    "so you can find out for yourself that it is true. " + NL + NL +
                    SCENARIO1 + NL + SCENARIO2 + NL + SCENARIO3 + NL +
                    "In summary: two of the three possible scenarios results in a WIN if you choose to SWITCH choice after the game hosts opens " +
                    "one of the remaining non-prize doors. " + NL +
                    "In other words, if you want a 2/3 or 66,6666...% chance of winning the Monty Hall Game  " + NL +
                    "- ALWAYS switch door when the option is given. =) ";

    List<NessieSimulationResult> simulations;
    int simulationsWhereNessieIsFound;

    private int nrOfGamesWithLakeKept = 0;
    private int nrOfGamesWithLakeSwitched = 0;

    private int nrOfGamesWonWithLakeKept = 0;
    private int nrOfGamesWonWithLakeSwitched = 0;

    public FindNessieSimulationSessionResult() {
        simulations = new ArrayList<>();
        simulationsWhereNessieIsFound = 0;
    }

    public void addSimulationResult(NessieSimulationResult result) {
        simulations.add(result);

        if (result.isLakeChoiceSwitched()) {
            gamePlayedWithLakeSwitched();
            if (result.isNessieFound()) {
                gameWonWithDoorSwitched();
            }
        } else {
            gamePlayedWithLakeKept();
            if (result.isNessieFound()) {
                gameWonWithLakeKept();
            }
        }
    }

    public void addAll(List<NessieSimulationResult> resultList) {
        for (NessieSimulationResult result : resultList) {
            addSimulationResult(result);
        }
    }

    public void gamePlayedWithLakeKept() {
        nrOfGamesWithLakeKept++;
    }

    public void gamePlayedWithLakeSwitched() {
        nrOfGamesWithLakeSwitched++;
    }

    public void gameWonWithLakeKept() {
        nrOfGamesWonWithLakeKept++;
    }

    public void gameWonWithDoorSwitched() {
        nrOfGamesWonWithLakeSwitched++;
    }

    public double getPercentageWonWithLakeKept() {
        return NumberHelper.calculatePercentage(nrOfGamesWonWithLakeKept, nrOfGamesWithLakeKept);
    }

    public double getPercentageWonWithLakeSwitched() {
        return NumberHelper.calculatePercentage(nrOfGamesWonWithLakeSwitched, nrOfGamesWithLakeSwitched);
    }

    public void reportGameSessionStatistics() {

        int totalNrOfGames = nrOfGamesWithLakeKept + nrOfGamesWithLakeSwitched;
        if (totalNrOfGames == 0) {
            System.out.println("No game session statistics to report");
            return;
        }

        double percentageWonWithLakeKept = getPercentageWonWithLakeKept();
        double percentageWonWithLakeSwitched = getPercentageWonWithLakeSwitched();
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println();
        System.out.println("The statistics for this Simulation Session is the following:");
        System.out.println("=============================================================");
        System.out.println("Total number of games played: " + totalNrOfGames + NL);

        System.out.println("Simulations run with the lake choice KEPT: " + nrOfGamesWithLakeKept);
        System.out.println("Percentage of games won when keeping lake: " + df.format(percentageWonWithLakeKept) + " %");
        System.out.println();

        System.out.println("Simulations run with the lake choice SWITCHED: " + nrOfGamesWithLakeSwitched);
        System.out.println("Percentage of games won when switching lake: " + df.format(percentageWonWithLakeSwitched) + " %");
        System.out.println();

        System.out.println("Now, do you think it is better to keep or to switch lake?");
        System.out.println("Remember that the statistical significance is more clear the more games you play.");
    }

    public void reportGameMotivation() {
        System.out.println(GAME_DESC);
        System.out.println(GAME_CHOICE_MOTIVATION);
    }
}
