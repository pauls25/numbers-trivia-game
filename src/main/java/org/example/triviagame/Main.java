package org.example.triviagame;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Question> savedGuesses = new ArrayList<>();

    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        Scanner userInput = new Scanner(System.in);

        printStartingMessage();
        while (savedGuesses.size() < 20) {

            String trivia = RequestHandler.getTrivia();
            Question question = new Question(RequestHandler.splitResponse(trivia));

            System.out.println("Guess the correct number!\n");
            System.out.println(question);
            System.out.print("Your guess: ");
            String userGuess = userInput.nextLine();
            if (question.checkAnswer(userGuess)) {
                savedGuesses.add(question);
            } else {
                System.out.println("Bad guess!");
                printCorrectGuesses(savedGuesses);
                break;
            }

        }
        if (savedGuesses.size() == 20) {
            System.out.println("Game is Over! You got all 20 questions correct!");
            printCorrectGuesses(savedGuesses);
            printEndingMessage();
        }
    }


    public static void printCorrectGuesses(List<Question> savedGuesses) {
        System.out.printf("You got %s questions correctly!", savedGuesses.size());
        System.out.println();
        for (int i = 0; i < savedGuesses.size(); i++) {
            Question savedGuess = savedGuesses.get(i);
            String line = (i + 1) + ". " + savedGuess.getTriviaNumber() + savedGuess.getTriviaFact();
            System.out.println(line);
        }
    }

    public static void printStartingMessage() {
        System.out.println("*".repeat(25));
        System.out.println("Welcome to Demo Trivia!");
        System.out.println("*".repeat(25));
        System.out.println("to end game - type 'end'" + " ".repeat(10));
    }

    public static void printEndingMessage() {
        System.out.println("*".repeat(25));
        System.out.println("Game Over!");
        System.out.println();
        System.out.println("*".repeat(25));
    }

}
