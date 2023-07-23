package org.example.triviagame;

public class EmptyLetterOptions extends Exception {

    public EmptyLetterOptions() {
        super("Ran out of letters for answer options!");
    }
}
