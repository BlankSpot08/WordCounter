package com.company.main;

import com.company.app.WordCounter;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    public void start() {
        final File inputFile = new File("inputs/Python.txt");

        final WordCounter wordCounter = new WordCounter();
        final Scanner scanner;

        try {
            scanner = new Scanner(inputFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                wordCounter.count(line);
            }

            final OutputStream outputFile = new FileOutputStream("outputs/result.txt");

            final String outputString = wordCounter.toString();
            final byte[] outputByte =  outputString.getBytes(StandardCharsets.UTF_8);

            outputFile.write(outputByte);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
