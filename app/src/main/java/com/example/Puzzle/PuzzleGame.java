package com.example.lab2_1089635;

import android.util.Log;
import java.util.Random;

public class PuzzleGame {

    private String[] parts;
    Random random = new Random();

    public PuzzleGame(String[] parts) {
        this.parts = parts;
    }

    public PuzzleGame() {
        this.parts = new String[] {"FRIEND", "IN","NEED","IS","FRIEND","INDEED"};
    }

    public boolean solved(String[] solution) {
        if (solution != null && solution.length == parts.length) {
            for (int i = 0; i < parts.length; i++) {
                if (!solution[i].equals(parts[i]))
                    return false;
            }
            return true;
        } else
            return false;
    }

    public String[] scramble() {
        String[] scrambled = new String[parts.length];
        for (int i = 0; i < parts.length; i++) {
            scrambled[i] = parts[i];
        }

        while (solved(scrambled)) {
            for (int i = 0; i < scrambled.length; i++) {
                int n = random.nextInt(scrambled.length);
                String temp = scrambled[i];
                scrambled[i] = scrambled[n];
                scrambled[n] = temp;
            }
        }
        for (int i = 0; i < scrambled.length; i++)
            Log.w("RANDOM", " Randomize " + scrambled[i]);

        return scrambled;
    }

    public int getNumberOfParts() {
        return parts.length;
    }
}