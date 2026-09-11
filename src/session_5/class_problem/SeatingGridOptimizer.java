import java.util.Scanner;

public class SeatingGridOptimizer {

    private static double rowAverage(int[] row) {
        int sum = 0;
        for (int val : row) {
            sum += val;
        }
        return (double) sum / row.length;
    }

    static String classifyRows(int[][] seatingScores, int threshold) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < seatingScores.length; i++) {
            double avg = rowAverage(seatingScores[i]);
            String zone = avg >= threshold ? "Buzzing Zone" : "Quiet Zone";
            sb.append("Row ").append(i).append(": ").append(zone);
            if (i != seatingScores.length - 1) {
                sb.append(" | ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rows = sc.nextInt();
        int[][] seatingScores = new int[rows][];

        for (int i = 0; i < rows; i++) {
            int cols = sc.nextInt();
            seatingScores[i] = new int[cols];
            for (int j = 0; j < cols; j++) {
                seatingScores[i][j] = sc.nextInt();
            }
        }

        int threshold = sc.nextInt();
        System.out.println(classifyRows(seatingScores, threshold));

        sc.close();
    }
}