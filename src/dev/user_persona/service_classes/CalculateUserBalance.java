package dev.user_persona.service_classes;

import dev.user_persona.data_classes.User_Persona;

import java.util.List;

public class CalculateUserBalance {
    public static boolean calculateBalance(User_Persona user_persona){

        double variance = getVariance(user_persona);
        double standardDeviation = calculateStandardDeviation(variance);

        // for now, we will calculate balance based on variance, although std is easier to read as it is in same units as data set
        // but variance is easier to define regardless of the values in the data set
        // larger variance == greater variability in data set
        if (variance <= 1.5){
            return true;
        } else return false;
    }

    private static double getVariance(User_Persona user_persona) {
        int[] userValues = {
                user_persona.getUserNutritionalValues().getImmuneHealth(),
                user_persona.getUserNutritionalValues().getNutritionalStatus(),
                user_persona.getUserNutritionalValues().getGutHealth(),
                user_persona.getUserNutritionalValues().getMentalWellness()
        };

        double meanAverage = calculateMeanAverage(userValues);
        return calculateVariance(userValues, meanAverage);
    }

    private static double calculateMeanAverage(int[] userValues){
        double average = 0.0;
        for (int i = 0; i < userValues.length; i++) {
            average += userValues[i];
        }
        average /= userValues.length;
        return average;
    }
    private static double calculateVariance(int[] userValues, double mean){
        double variance = 0;
        for (int i = 0; i < userValues.length; i++) {
            variance += Math.pow(userValues[i] - mean, 2);
        }
        variance /= userValues.length;
        return variance;
    }
    private static double calculateStandardDeviation(double variance){
        return Math.sqrt(variance);
    }
}
