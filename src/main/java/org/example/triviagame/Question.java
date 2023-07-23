package org.example.triviagame;

import java.util.*;

public class Question {

    private String triviaFact;
    private final String triviaNumber;
    private char correctOption;
    private Map<Character, String> optionsAnswersMap = new HashMap<>();

    public Question(Map<String, String> questionInfo) {
        this.triviaFact = questionInfo.get("triviaFact");
        this.triviaNumber = questionInfo.get("triviaNumber");
        generateAnswerOptions();
    }

    private void generateAnswerOptions() {
        List<Character> letterList = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd'));

        int numberOfDigits = getTriviaNumber().length();

        // generates an upper and lower bound for the answer number options
        int upperOptionNumberBound = Integer.parseInt(getTriviaNumber()) * 5;
        int lowerOptionNumberBound;

        if (numberOfDigits > 2) {
            lowerOptionNumberBound = (int) (numberOfDigits / 3);
        } else {
            lowerOptionNumberBound = 0;
        }

        // maps answer option letters (a, b, c etc.) to answer number values
        Random random = new Random();
        try {
            this.correctOption = pickRandomLetter(letterList);
            optionsAnswersMap.put(this.correctOption, getTriviaNumber());

            int option1 = generateRandomIntegers(lowerOptionNumberBound, upperOptionNumberBound);
            int option2 = generateRandomIntegers(lowerOptionNumberBound, upperOptionNumberBound);
            int option3 = generateRandomIntegers(lowerOptionNumberBound, upperOptionNumberBound);

            optionsAnswersMap.put(pickRandomLetter(letterList), String.valueOf(option1));
            optionsAnswersMap.put(pickRandomLetter(letterList), String.valueOf(option2));
            optionsAnswersMap.put(pickRandomLetter(letterList), String.valueOf(option3));


        } catch (EmptyLetterOptions e) {
            System.out.println("Couldn't pick random letter! It possible 'pickRandomLetter()' ran out of letters to return from 'letterList' and is empty!");
            System.out.println("letterList contains " + letterList + " and it's size is " + letterList.size());
        }
    }

    public boolean checkAnswer(String userAnswer) {
        return this.triviaNumber.equals(userAnswer);
    }

    public static char pickRandomLetter(List<Character> letterList) throws EmptyLetterOptions {
        Random random = new Random();
        char chosenLetter = 0;

        if (letterList.size() > 0) {
            int index = random.nextInt(letterList.size());
            chosenLetter = letterList.get(index);
            letterList.remove(index);
        } else {
            throw new EmptyLetterOptions();
        }

        return chosenLetter;
    }

    public static int generateRandomIntegers(int lowerBound, int upperBound) {
        Random random = new Random();
        return random.nextInt(upperBound - lowerBound + 1) + lowerBound;
    }

    public String getTriviaNumber() {
        return triviaNumber;
    }

    public String getTriviaFact() {
        return triviaFact;
    }

    @Override
    public String toString() {

        StringBuilder questionToText = new StringBuilder(
                "_".repeat(this.getTriviaNumber().length()) + getTriviaFact()
        );

        questionToText.append("\n");
        questionToText.append("\n");
        for (Map.Entry<Character, String> option : optionsAnswersMap.entrySet()) {
            questionToText.append(option.getKey())
                    .append(") ")
                    .append(option.getValue())
                    .append('\n');
        }
        return questionToText.toString();
    }
}
