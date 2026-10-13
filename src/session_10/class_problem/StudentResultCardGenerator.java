class Student {
    private String name;
    private int[] marks;

    Student(String name, int[] marks) {
        this.name = name;
        this.marks = marks;
    }

    double average() {
        int total = 0;
        for (int m : marks) {
            total += m;
        }
        return (double) total / marks.length;
    }

    char grade() {
        double avg = average();
        if (avg >= 90) {
            return 'A';
        } else if (avg >= 75) {
            return 'B';
        } else if (avg >= 60) {
            return 'C';
        } else if (avg >= 40) {
            return 'D';
        } else {
            return 'F';
        }
    }

    void printCard() {
        System.out.printf("%s: Average %.1f, Grade %c%n", name.toUpperCase(), average(), grade());
    }
}

public class StudentResultCardGenerator {
    public static void main(String[] args) {
        Student[] students = {
            new Student("Asha", new int[]{80, 90, 70}),
            new Student("Ravi", new int[]{60, 55, 50})
        };
        for (Student s : students) {
            s.printCard();
        }
    }
}
