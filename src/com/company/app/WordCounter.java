package com.company.app;

import com.company.languages.Language;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordCounter {
    private final Language language;

    public WordCounter(Language language) {
        this.language = language;
    }

    public void count(String line) {
        String[] array = line.toLowerCase().split(" ");

        final int arrayLength = array.length;
        if (arrayLength <= 1) {
            return;
        }

        final Language language = this.language;

        final String[] regexs = {
                "'s$|s'$",
                "[^a-zA-Z -]",
                "^[-]|[-]$"
        };

        for (int i = 0; i < arrayLength; i++) {
            for (String regex : regexs) {
                array[i] = array[i].replaceAll(regex, "");
            }
        }

        language.setNumberOfWords(language.getNumberOfWords() + arrayLength);
        language.setNumberOfLines(language.getNumberOfLines() + 1);

        for (final String word : array) {
            if (!word.equals("")) {
                final File words = language.getLanguageFileIndex();
                final String[] paths = words.list();
                final String[] splittedWord = word.split("-");

                if (searchingWord(splittedWord, paths, words)) {
                    language.getLanguageWordsUsed().add(word);
                    language.setNumberOfLanguageWords(language.getNumberOfLanguageWords() + 1);
                }
            }
        }

        for (String word : array) {
            final int wordLength = word.length();
            for (int i = 0; i < wordLength; i++) {
                final char letter = word.charAt(i);

                final int tempNumberOfVowels = language.getNumberOfVowels();

                final char[] vowels = language.getVowels();
                for (char vowel : vowels) {
                    if (vowel == letter) {
                        language.setNumberOfVowels(language.getNumberOfVowels() + 1);
                        break;
                    }
                }

                if (language.getNumberOfVowels() == tempNumberOfVowels) {
                    language.setNumberOfConsonants(language.getNumberOfConsonants() + 1);
                }
            }
        }

    }

    private boolean searchingWord(String[] splittedWord, String[] paths, File words)  {
        try {
            for (String word : splittedWord) {
                final String temp = getDictionaryIndex(paths, word);
                final String fileIndexPath = words.getPath() + "\\" + temp;

                final File fileIndex = new File(fileIndexPath);
                final Scanner scanner = new Scanner(fileIndex);

                if (!isLanguageWord(scanner, word)) {
                    scanner.close();
                    return false;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return true;
    }

    private boolean isLanguageWord(Scanner scanner, String word) {
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

        final Language language = this.language;
        temp.append("Number of Lines: ").append(language.getNumberOfLines()).append("\n")
                .append("Number of Words: ").append(language.getNumberOfWords()).append("\n")
                .append("Number of Vowels: ").append(language.getNumberOfVowels()).append("\n")
                .append("Number of Constants: ").append(language.getNumberOfConsonants()).append("\n")
                .append("Number of ").append(language.getName()).append(" Words: ").append(language.getNumberOfLanguageWords()).append("\n\n\n\n")
                .append(language.getName()).append(" Used: \n");

        String[] englishWordsUsedArray = language.getLanguageWordsUsed().toArray(String[]::new);
        for (String word : englishWordsUsedArray) {
            temp.append(word).append("\n");
        }

        return temp.toString();
    }
}


