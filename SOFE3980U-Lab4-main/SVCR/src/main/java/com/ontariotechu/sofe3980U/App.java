package com.ontariotechu.sofe3980U;

import java.io.FileReader;
import java.util.List;
import com.opencsv.*;

public class App {
    public static void main(String[] args) {
        String filePath = "model_3.csv";  
        FileReader filereader;
        List<String[]> allData;

        try {
            filereader = new FileReader(filePath);
            CSVReader csvReader = new CSVReaderBuilder(filereader).withSkipLines(1).build();
            allData = csvReader.readAll();
        } catch (Exception e) {
            System.out.println("Error reading the CSV file");
            return;
        }

        double sumSquaredError = 0;
        double sumAbsoluteError = 0;
        double sumRelativeError = 0;
        int n = allData.size();
        double epsilon = 1e-10; 

        System.out.println("First 10 Data Points:");
        System.out.println("True Value\tPredicted Value");

        int count = 0;
        for (String[] row : allData) {
            double y_true = Double.parseDouble(row[0]);
            double y_predicted = Double.parseDouble(row[1]);

            if (count < 10) {
                System.out.printf("%.5f\t%.5f\n", y_true, y_predicted);
            }

    
            double error = y_true - y_predicted;
            sumSquaredError += Math.pow(error, 2);
            sumAbsoluteError += Math.abs(error);
            sumRelativeError += (Math.abs(error) / (Math.abs(y_true) + epsilon)) * 100;

            count++;
        }

        double mse = sumSquaredError / n;
        double mae = sumAbsoluteError / n;
        double mare = sumRelativeError / n;

        System.out.println("\nError Metrics:");
        System.out.printf("Mean Squared Error (MSE): %.5f\n", mse);
        System.out.printf("Mean Absolute Error (MAE): %.5f\n", mae);
        System.out.printf("Mean Absolute Relative Error (MARE): %.5f%%\n", mare);
    }
}
