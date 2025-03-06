package com.ontariotechu.sofe3980U;

import java.io.FileReader;
import java.util.List;
import com.opencsv.*;

/**
 * Evaluate Single Variable Binary Regression
 */
public class App {
    public static void main(String[] args) {
        String filePath = "model_1.csv";
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
        
        int TP = 0, FP = 0, TN = 0, FN = 0;
        double BCE = 0.0;
        int nPositive = 0, nNegative = 0;
        double[] TPR = new double[101];
        double[] FPR = new double[101];
        
        for (String[] row : allData) {
            int y_true = Integer.parseInt(row[0]);
            double y_predicted = Double.parseDouble(row[1]);
            
            BCE += (y_true == 1) ? -Math.log(y_predicted) : -Math.log(1 - y_predicted);
            
            if (y_true == 1) {
                nPositive++;
                if (y_predicted >= 0.5) TP++;
                else FN++;
            } else {
                nNegative++;
                if (y_predicted >= 0.5) FP++;
                else TN++;
            }
        }
        
        BCE /= allData.size();
        double accuracy = (double) (TP + TN) / allData.size();
        double precision = (TP + FP) == 0 ? 0 : (double) TP / (TP + FP);
        double recall = (TP + FN) == 0 ? 0 : (double) TP / (TP + FN);
        double f1Score = (precision + recall) == 0 ? 0 : 2 * (precision * recall) / (precision + recall);
        
        for (int i = 0; i <= 100; i++) {
            double threshold = i / 100.0;
            int tempTP = 0, tempFP = 0;
            
            for (String[] row : allData) {
                int y_true = Integer.parseInt(row[0]);
                double y_predicted = Double.parseDouble(row[1]);
                
                if (y_predicted >= threshold) {
                    if (y_true == 1) tempTP++;
                    else tempFP++;
                }
            }
            
            TPR[i] = (nPositive == 0) ? 0 : (double) tempTP / nPositive;
            FPR[i] = (nNegative == 0) ? 0 : (double) tempFP / nNegative;
        }
        
        double auc = 0.0;
        for (int i = 1; i <= 100; i++) {
            auc += (TPR[i - 1] + TPR[i]) * Math.abs(FPR[i - 1] - FPR[i]) / 2;
        }
        
        System.out.println("Binary Classification Metrics for " + filePath);
        System.out.println("Binary Cross-Entropy (BCE): " + BCE);
        System.out.println("Confusion Matrix:");
        System.out.println("TP: " + TP + " | FP: " + FP);
        System.out.println("FN: " + FN + " | TN: " + TN);
        System.out.println("Accuracy: " + accuracy);
        System.out.println("Precision: " + precision);
        System.out.println("Recall: " + recall);
        System.out.println("F1-score: " + f1Score);
        System.out.println("AUC-ROC: " + auc);
    }
}
