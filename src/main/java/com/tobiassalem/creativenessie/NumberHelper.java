package com.tobiassalem.creativenessie;

/**
 *  A simple helper class for math and numbers.
 *
 * @author Tobias
 */
public class NumberHelper {

        public static int getRandomIntExclusiveUpperBound(int lowerBound, int upperBound) {
            return lowerBound + (int) (Math.random() * (upperBound - lowerBound));
        }

        public static int getRandomIntInclusiveUpperBound(int lowerBound,int upperBound) {
            return lowerBound + (int) (Math.random() * ((upperBound - lowerBound) + 1));
        }

        public static double calculatePercentage(int nominator, int denominator) {
            return calculatePercentage(new Double(nominator), new Double(denominator));
        }

        public static double calculatePercentage(Double nominator, Double denominator) {
            return (nominator / denominator) * 100;
        }

}
