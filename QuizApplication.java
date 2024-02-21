import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class QuizApplication {
    private static final int MAX_QUESTIONS = 5; // Change this according to your quiz
    private static final int TIME_LIMIT_PER_QUESTION = 10; // Time limit per question in seconds
    private static int score = 0;
    private static int currentQuestion = 0;

    private static String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "What is the largest mammal?",
            "What is the square root of 64?",
            "Who is the author of 'To Kill a Mockingbird'?"
    };

    private static String[][] options = {
            {"Paris", "Berlin", "London", "Madrid"},
            {"Earth", "Mars", "Jupiter", "Saturn"},
            {"Elephant", "Blue Whale", "Giraffe", "Hippopotamus"},
            {"6", "8", "10", "12"},
            {"Harper Lee", "J.K. Rowling", "George Orwell", "Mark Twain"}
    };

    private static int[] correctAnswers = {0, 1, 1, 2, 0};

    public static void main(String[] args) {
        displayQuestion();
    }

    private static void displayQuestion() {
        if (currentQuestion < MAX_QUESTIONS) {
            System.out.println("\nQuestion " + (currentQuestion + 1) + ": " + questions[currentQuestion]);
            for (int i = 0; i < options[currentQuestion].length; i++) {
                System.out.println((i + 1) + ". " + options[currentQuestion][i]);
            }

            startTimer();
            getUserAnswer();
        } else {
            endQuiz();
        }
    }

    private static void startTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\nTime's up!");
                getUserAnswer();
            }
        }, TIME_LIMIT_PER_QUESTION * 1000);
    }

    private static void getUserAnswer() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter your answer (1-" + options[currentQuestion].length + "): ");

        try {
            int userAnswer = scanner.nextInt() - 1;
            if (userAnswer >= 0 && userAnswer < options[currentQuestion].length) {
                checkAnswer(userAnswer);
            } else {
                System.out.println("Invalid choice. Try again.");
                getUserAnswer();
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            getUserAnswer();
        }
        scanner.close();
    }

    private static void checkAnswer(int userAnswer) {
        if (userAnswer == correctAnswers[currentQuestion]) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer is: " + options[currentQuestion][correctAnswers[currentQuestion]]);
        }

        currentQuestion++;
        displayQuestion();
    }

    private static void endQuiz() {
        System.out.println("\nQuiz completed!");
        System.out.println("Your score: " + score + "/" + MAX_QUESTIONS);
    }
}
