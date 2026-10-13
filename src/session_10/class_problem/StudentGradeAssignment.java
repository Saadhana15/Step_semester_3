import java.util.Scanner;

public class StudentGradeAssignment {
    static char gradeFor(int marks) {
        if (marks >= 90) {
            return 'A';
        } else if (marks >= 75) {
            return 'B';
        } else if (marks >= 60) {
            return 'C';
        } else if (marks >= 40) {
            return 'D';
        } else {
            return 'F';
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int marks = sc.nextInt();
        System.out.println("Grade " + gradeFor(marks));
    }
}