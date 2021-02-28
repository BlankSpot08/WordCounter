package com.company.main;

import com.company.app.WordCounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public void start() {
        File file = new File("inputs/Python.txt");

        WordCounter wordCounter = new WordCounter();

        Scanner scanner;
        try {
            scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
//                System.out.println(line);
                wordCounter.count(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(wordCounter.toString());
    }
}
