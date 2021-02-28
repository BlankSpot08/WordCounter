package com.company.app;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WordCounter {
    public final Set<String> wordsUsed;

    private int numberOfConstants;
    private int numberOfVowels;
    private int numberOfLines;
    private int numberOfWords;

    public WordCounter() {
        wordsUsed = new HashSet<>();
        numberOfConstants = 0;
        numberOfVowels = 0;
        numberOfLines = 0;
        numberOfWords = 0;
    }

    public void count(String line) {
        String[] array = line.replaceAll("[^a-zA-Z -]", "").toLowerCase().split(" ");

        final int arrayLength = array.length;

//        System.out.println(Arrays.toString(array));
        if (arrayLength <= 1) {
            return;
        }

        for (int i = 0; i < arrayLength; i++) {
            if (!array[i].equals("")) {
                System.out.println(array[i]);
                File words = new File("src/com/company/words/english");
                String[] paths = words.list();

                final int pathLength = paths.length;
                for (int j = 0; j < pathLength; j++) {
                    final char temp = array[i].charAt(0);

                    final String path = paths[j].toLowerCase();
                    final char firstLetter = path.charAt(0);
                    final char secondLetter = path.charAt(4);

                    if (temp >= firstLetter && temp <= secondLetter) {

                        File fileIndex = new File(words.getPath() + "\\" + paths[j]);

                        try {
                            Scanner scanner = new Scanner(fileIndex);

                            while (scanner.hasNextLine()) {
                                if (array[i].equals(scanner.nextLine())) {

                                    if (array[i].startsWith("a")
                                            || array[i].startsWith("e")
                                            || array[i].startsWith("i")
                                            || array[i].startsWith("o")
                                            || array[i].startsWith("u")) {
                                        numberOfVowels++;
                                    }
                                    else {
                                        numberOfConstants++;
                                    }
                                    break;
                                }
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        break;
                    }
                }
                wordsUsed.add(array[i]);
            }

        }

        numberOfLines++;
    }

    public String toString() {
        String temp = "";

        temp = "Number of Lines: " + numberOfLines + "\n" +
                "Number of Words: " + numberOfWords + "\n" +
                "Number of Vowels: " + numberOfVowels + "\n" +
                "Number of Constants: " + numberOfConstants;

        System.out.println(Arrays.toString(wordsUsed.toArray()));

        return temp;
    }
}


