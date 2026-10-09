import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class Question {
    protected final String text;
    protected final String correctAnswer;
    protected final String studentAnswer;
    protected final int points;

    Question(String text, String correctAnswer, String studentAnswer, int points) {
        this.text = text;
        this.correctAnswer = correctAnswer;
        this.studentAnswer = studentAnswer;
        this.points = points;
    }

    abstract String name();

    abstract double score();
}

class McqQuestion extends Question {
    McqQuestion(String text, String correctAnswer, String studentAnswer, int points) {
        super(text, correctAnswer, studentAnswer, points);
    }

    String name() {
        return "MCQ";
    }

    double score() {
        return studentAnswer.equals(correctAnswer) ? points : 0;
    }
}

class TrueFalseQuestion extends Question {
    TrueFalseQuestion(String text, String correctAnswer, String studentAnswer, int points) {
        super(text, correctAnswer, studentAnswer, points);
    }

    String name() {
        return "TF";
    }

    double score() {
        return studentAnswer.equals(correctAnswer) ? points : 0;
    }
}

class EssayQuestion extends Question {
    EssayQuestion(String text, String correctAnswer, String studentAnswer, int points) {
        super(text, correctAnswer, studentAnswer, points);
    }

    String name() {
        return "ESSAY";
    }

    double score() {
        String answer = studentAnswer.toLowerCase();
        int matched = 0;
        for (String keyword : correctAnswer.split(",")) {
            String k = keyword.trim().toLowerCase();
            if (!k.isEmpty() && answer.contains(k)) {
                matched++;
            }
        }
        if (matched >= 2) {
            return points * 0.75;
        }
        if (matched == 1) {
            return points * 0.50;
        }
        return 0;
    }
}

public class ExamGrader {
    private static final Pattern LINE =
            Pattern.compile("^(\\w+)\\s+\"([^\"]*)\"\\s+\"([^\"]*)\"\\s+\"([^\"]*)\"\\s+(\\d+)$");

    static Question create(String line) {
        Matcher m = LINE.matcher(line.trim());
        if (!m.matches()) {
            throw new IllegalArgumentException("Bad line: " + line);
        }
        String type = m.group(1).toUpperCase();
        String text = m.group(2);
        String correct = m.group(3);
        String student = m.group(4);
        int points = Integer.parseInt(m.group(5));
        switch (type) {
            case "MCQ":
                return new McqQuestion(text, correct, student, points);
            case "TF":
                return new TrueFalseQuestion(text, correct, student, points);
            case "ESSAY":
                return new EssayQuestion(text, correct, student, points);
            default:
                throw new IllegalArgumentException("Unknown question type: " + type);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine().trim());

        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            questions.add(create(in.readLine()));
        }

        double total = 0;
        for (Question q : questions) {
            double score = q.score();
            total += score;
            System.out.println(String.format(Locale.US, "%s: %.2f", q.name(), score));
        }
        System.out.println(String.format(Locale.US, "Total Score: %.2f", total));
    }
}