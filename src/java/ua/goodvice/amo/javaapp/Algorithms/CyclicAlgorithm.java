package ua.goodvice.amo.javaapp.Algorithms;

public class CyclicAlgorithm implements Algorithm {
    @Override
    public double calculate(String userInput) {
        Object[] dataArrays = parseUserInput(userInput);
        double result = calculate((double[]) dataArrays[0], (double[]) dataArrays[1], (double[]) dataArrays[2],
                (double[]) dataArrays[3]);
        return result;
    }

    private Object[] parseUserInput(String userInput) {
        String[] arraysArray = userInput.split("\n");
        Object[] dataArrays = new Object[4];
        for (int i = 0; i < 4; i++) {
            String[] stringDataArray = arraysArray[i].split(",");
            double[] dataArray = new double[50];
            for (int k = 0; k < 50; k++) {
                dataArray[k] = Double.parseDouble(stringDataArray[k]);
            }
            dataArrays[i] = dataArray;
        }

        return dataArrays;
    }

    private double calculate(double[] a, double[] c, double[] f, double[] g) {
        double result = 0;
        for (int i = 0; i < 50; i++) {
            result += Math.pow(a[i], 2) + 56 * c[i] * Math.sqrt(f[i]) * g[i];
        }
        return result;
    }


    @Override
    public String getAlgorithmName() {
        return "Циклічний алгоритм";
    }
}
