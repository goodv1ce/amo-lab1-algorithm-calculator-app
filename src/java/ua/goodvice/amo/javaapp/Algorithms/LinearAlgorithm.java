package ua.goodvice.amo.javaapp.Algorithms;


import java.util.Arrays;

public class LinearAlgorithm implements Algorithm {

    public double calculate(String userInput) {
        String[] userInputArray = userInput.split(",");
        double[] data = new double[userInputArray.length];
        for (int k = 0; k < userInputArray.length; k++)
            data[k] = Double.parseDouble(userInputArray[k]);
        double b = data[0];
        double c = data[1];

        return 2 * Math.cos(b * (c / 2.0) + 2 * Math.sin(c * (b / 2.0)));
    }

    @Override
    public String getAlgorithmName() {
        return "Лінійний алгоритм";
    }
}
