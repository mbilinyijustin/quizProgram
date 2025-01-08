import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizUI {
    // Question class to store question, options, and correct answer
    static class Question {
        String questionText;
        String[] options;
        int correctAnswer;

        Question(String questionText, String[] options, int correctAnswer) {
            this.questionText = questionText;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    private JFrame frame;
    private JLabel questionLabel;
    private JRadioButton[] optionButtons;
    private ButtonGroup buttonGroup;
    private JButton nextButton;
    private int currentQuestionIndex = 0;
    private int score = 0;

    private Question[] questions = {
            new Question("What is the capital of France?",
                    new String[]{"Berlin", "Madrid", "Paris", "Rome"}, 3),
            new Question("Which programming language has a logo with a coffee cup?",
                    new String[]{"Python", "Java", "C++", "Kotlin"}, 2),
            new Question("What is 2 + 2?",
                    new String[]{"3", "4", "5", "6"}, 2),
            new Question("What is 5 - 2?",
                    new String[]{"3", "4", "5", "6"}, 1),
            new Question("What is 2 / 2?",
                    new String[]{"3", "4", "5", "1"}, 4),
            new Question("Who is your 5th country President",
                    new String[]{"J.P.Magufuri", "Mama Samia", "Jakaya Kikwete", "Mkapa"}, 1)
    };

    public QuizUI() {
        // Initialize the UI components
        frame = new JFrame("Quiz Program");
        questionLabel = new JLabel("", JLabel.CENTER);
        optionButtons = new JRadioButton[4];
        buttonGroup = new ButtonGroup();
        nextButton = new JButton("Next");

        // Set up the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Question panel
        JPanel questionPanel = new JPanel(new GridLayout(5, 1));
        questionPanel.add(questionLabel);

        // Options panel
        for (int i = 0; i < 4; i++) {
            optionButtons[i] = new JRadioButton();
            buttonGroup.add(optionButtons[i]);
            questionPanel.add(optionButtons[i]);
        }

        // Add components to frame
        frame.add(questionPanel, BorderLayout.CENTER);
        frame.add(nextButton, BorderLayout.SOUTH);

        // Add action listener to the Next button
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
            }
        });

        loadQuestion();
        frame.setVisible(true);
    }

    private void loadQuestion() {
        if (currentQuestionIndex < questions.length) {
            // Load the current question and options
            Question currentQuestion = questions[currentQuestionIndex];
            questionLabel.setText(currentQuestion.questionText);
            for (int i = 0; i < 4; i++) {
                optionButtons[i].setText(currentQuestion.options[i]);
            }
            buttonGroup.clearSelection(); // Clear previous selection
        } else {
            // End of quiz
            JOptionPane.showMessageDialog(frame,
                    "Quiz Over! You scored " + score + " out of " + questions.length + ".");
            frame.dispose(); // Close the application
        }
    }

    private void checkAnswer() {
        // Check if an option is selected
        int selectedOption = -1;
        for (int i = 0; i < 4; i++) {
            if (optionButtons[i].isSelected()) {
                selectedOption = i + 1; // Convert to 1-based index
                break;
            }
        }

        if (selectedOption == -1) {
            JOptionPane.showMessageDialog(frame, "Please select an option!");
            return;
        }

        // Check if the selected answer is correct
        if (selectedOption == questions[currentQuestionIndex].correctAnswer) {
            score++;
        }

        // Load the next question
        currentQuestionIndex++;
        loadQuestion();
    }

    public static void main(String[] args) {
        new QuizUI();
    }
}
