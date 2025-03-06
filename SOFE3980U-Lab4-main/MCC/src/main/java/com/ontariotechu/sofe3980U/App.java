package com.ontariotechu.sofe3980U;

import java.io.FileReader;
import java.util.List;
import com.opencsv.*;

/**
 * Evaluate Multiclass Classification with Cross-Entropy and Confusion Matrix
 */
public class App {
    public static void main(String[] args) {
        String filePath = "model.csv";
        FileReader filereader;
        List<String[]> allData;
        
        int[][] confusionMatrix = new int[5][5];  
        float totalCrossEntropy = 0;
        int count = 0;
        
        try {
            filereader = new FileReader(filePath);
            CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
            allData = csvReader.readAll();
        } catch (Exception e) {
            System.out.println("Error reading the CSV file");
            return;
        }

        for (String[] row : allData) {
            int y_true = Integer.parseInt(row[0]); 
            float[] y_predicted = new float[5];  

            for (int i = 0; i < 5; i++) {
                y_predicted[i] = Float.parseFloat(row[i + 1]);
            }

            int y_pred = getPredictedClass(y_predicted);

            confusionMatrix[y_true - 1][y_pred - 1]++;

            float ce = calculateCrossEntropy(y_true, y_predicted);
            totalCrossEntropy += ce;

            count++;
        }

        float averageCE = totalCrossEntropy / count;
        System.out.println("\nAverage Cross-Entropy: " + averageCE);

        System.out.println("\nConfusion Matrix:");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(confusionMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

        private static int getPredictedClass(float[] y_predicted) {
        int predictedClass = 0;
        float maxProb = -1;
        for (int i = 0; i < 5; i++) {
            if (y_predicted[i] > maxProb) {
                maxProb = y_predicted[i];
                predictedClass = i + 1;  // Class is 1-indexed
            }
        }
        return predictedClass;
    }

        private static float calculateCrossEntropy(int y_true, float[] y_predicted) {
        float ce = 0;
        ce = - (float) Math.log(y_predicted[y_true - 1]);
        return ce;
    }
}
