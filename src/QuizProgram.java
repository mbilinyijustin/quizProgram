import java.util.Scanner;

public class QuizProgram {

    // A class to represent a quiz question
    static class Question {
        String questionText;
        String[] options;
        int correctAnswer;

        Question(String questionText, String[] options, int correctAnswer) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }

        boolean isCorrect(int userAnswer) {
            return userAnswer == correctAnswer;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Define the questions
        Question[] questions = {
                new Question(
                        "What is the capital of France?",
                        new String[]{"1. Berlin", "2. Madrid", "3. Paris", "4. Rome"},
                        3
                ),
                new Question(
                        "Which programming language is known for its logo featuring a cup of coffee?",
                        new String[]{"1. Python", "2. Java", "3. C++", "4. Kotlin"},
                        2
                ),
                new Question(
                        "What is 2 + 2?",
                        new String[]{"1. 3", "2. 4", "3. 5", "4. 6"},
                        2
                )
        };

        int score = 0;

        // Iterate through each question
        for (int i = 0; i < questions.length; i++) {
            System.out.println("Question " + (i + 1) + ": " + questions[i].questionText);

            for (String option : questions[i].options) {
                System.out.println(option);
            }

            System.out.print("Enter your answer (1-4): ");
            int userAnswer = scanner.nextInt();

            if (questions[i].isCorrect(userAnswer)) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Wrong! The correct answer was " + questions[i].correctAnswer + ".\n");
            }
        }

        // Display the final score
        System.out.println("Quiz Over! You scored " + score + " out of " + questions.length + ".");

        scanner.close();
    }
}
