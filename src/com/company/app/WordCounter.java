package com.company.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordCounter {
    public final Set<String> englishWordsUsed;

    private int numberOfConstants;
    private int numberOfVowels;
    private int numberOfLines;
    private int numberOfWords;
    private int numberOfEnglishWords;

    public WordCounter() {
        englishWordsUsed = new HashSet<>();
        numberOfConstants = 0;
        numberOfVowels = 0;
        numberOfLines = 0;
        numberOfWords = 0;
    }

    public void count(String line) {
        String[] array = line.toLowerCase().split(" ");
        final int arrayLength = array.length;

        if (arrayLength <= 1) {
            return;
        }

        for (int i = 0; i < arrayLength; i++) {
            array[i] = array[i].replaceAll("'s$|s'$", "");
            array[i] = array[i].replaceAll("[^a-zA-Z -]", "");
            array[i] = array[i].replaceAll("^[-]|[-]$", "");
        }

        numberOfWords += arrayLength;
        numberOfLines++;

        for (final String word : array) {
            if (!word.equals("")) {
                final File words = new File("src/com/company/words/english");
                final String[] paths = words.list();
                final String[] splittedWord = word.split("-");

                if (testing(splittedWord, paths, words)) {
                    englishWordsUsed.add(word);
                    numberOfEnglishWords++;
                }
            }
        }

        for (String word : array) {
            final int wordLength = word.length();
            for (int j = 0; j < wordLength; j++) {
                final char letter = word.charAt(j);

                if (letter == 'a'
                        || letter == 'e'
                        || letter == 'i'
                        || letter == 'o'
                        || letter == 'u') {
                    numberOfVowels++;
                } else {
                    numberOfConstants++;
                }
            }
        }

    }

    private boolean testing(String[] splittedWord, String[] paths, File words)  {
        try {
            for (String word : splittedWord) {
                final String temp = getDictionaryIndex(paths, word);
                final String fileIndexPath = words.getPath() + "\\" + temp;

                final File fileIndex = new File(fileIndexPath);
                final Scanner scanner = new Scanner(fileIndex);

                if (!isEnglishWord(scanner, word)) {
                    scanner.close();
                    return false;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return true;
    }

    private boolean isEnglishWord(Scanner scanner, String word) {
        while (scanner.hasNextLine()) {

            final String scannerLine = scanner.nextLine();
            if (scannerLine.equals(word)) {
                return true;
            }
        }

        return false;
    }

    private String getDictionaryIndex(String[] paths, String word) {
        for (String path : paths) {
            final char firstLetterOfWord = word.charAt(0);

            final String temp = path.toLowerCase();
            final char firstLetter = temp.charAt(0);
            final char secondLetter = temp.charAt(4);

            if (firstLetterOfWord >= firstLetter && firstLetterOfWord <= secondLetter) {
                return path;
            }
        }

        return null;
    }

    final public String toString() {
        final StringBuilder temp = new StringBuilder();

        temp.append("Number of Lines: ").append(numberOfLines).append("\n")
                .append("Number of Words: ").append(numberOfWords).append("\n")
                .append("Number of Vowels: ").append(numberOfVowels).append("\n")
                .append("Number of Constants: ").append(numberOfConstants).append("\n")
                .append("Number of English Words: ").append(numberOfEnglishWords).append("\n\n\n\n")
                .append("English Words: \n");

        String[] englistWordsUsedArray = englishWordsUsed.toArray(String[]::new);
        for (String word : englistWordsUsedArray) {
            temp.append(word).append("\n");
        }

        return temp.toString();
    }
}


