package ua.goodvice.amo.javaapp.Algorithms;

public class BranchingAlgorithm implements Algorithm {
    @Override
    public double calculate(String userInput) {
        String[] userInputArray = userInput.split(",");
        double[] data = new double[userInputArray.length];
        for (int k = 0; k < userInputArray.length; k++)
            data[k] = Double.parseDouble(userInputArray[k]);
        return calculate(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7]);
    }

    private double calculate(double d, double b, double k, double j, double g, double f, double v, double c) {
        double denominator = 23 * Math.sqrt(g * f) + 6 * Math.sqrt(v * c);
        if (denominator == 0) {
            return -1;
        } else {
            double underRootExpressionResult = (d - b - k * j) / denominator;
            if (underRootExpressionResult < 0) {
                return -2;
            } else {
                return Math.sqrt(underRootExpressionResult);
            }
        }
    }

    @Override
    public String getAlgorithmName() {
        return "Алгоритм, що розгалужується";
    }
}
