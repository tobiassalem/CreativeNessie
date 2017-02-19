package com.tobiassalem.creativenessie;

/**
 * Created by Tobias on 2017-02-18.
 */
public class NumberHelper {

        public static int getRandomIntExlusiveUpperBound(int lowerBound,int upperBound) {
            // Classic formula:
            return lowerBound + (int) (Math.random() * (upperBound - lowerBound));
            // My simple formula:
            //return new Random().nextInt(upperBound);
        }

        public static int getRandomIntInclusiveUpperBound(int lowerBound,int upperBound) {
            return lowerBound + (int) (Math.random() * ((upperBound - lowerBound) + 1));
            //return new Random().nextInt(upperBound+1);
        }

        public static double calculatPercentage(int nominator, int denominator) {
            return calculatPercentage(new Double(nominator), new Double(denominator));
        }

        public static double calculatPercentage(Double nominator, Double denominator) {
            return (nominator / denominator) * 100;
        }

}
