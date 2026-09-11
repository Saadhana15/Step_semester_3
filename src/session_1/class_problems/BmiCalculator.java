import java.util.Scanner;

public class BmiCalculator {

    static String getBmiStatus(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi < 25) {
            return "Normal";
        } else if (bmi < 30) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    static void printWellnessReport(double[] heights, double[] weights) {
        System.out.println("Person | Height (m) | Weight (kg) | BMI   | Status");

        for (int i = 0; i < heights.length; i++) {
            double bmi = weights[i] / (heights[i] * heights[i]);
            double roundedBmi = Math.round(bmi * 100.0) / 100.0;
            String status = getBmiStatus(bmi);

            System.out.println("Person " + (i + 1) + " | " + heights[i] + "       | " + weights[i] + "         | " + roundedBmi + " | " + status);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of people in the team: ");
        int n = sc.nextInt();

        double[] heights = new double[n];
        double[] weights = new double[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter height (m) for Person " + (i + 1) + ": ");
            heights[i] = sc.nextDouble();

            System.out.print("Enter weight (kg) for Person " + (i + 1) + ": ");
            weights[i] = sc.nextDouble();
        }

        System.out.println();
        printWellnessReport(heights, weights);

        sc.close();
    }
}