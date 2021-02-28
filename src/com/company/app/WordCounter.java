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
//        String[] array = line.replaceAll("[^a-zA-Z -]", "").toLowerCase().split(" ");
        String[] array = line.toLowerCase().split(" ");
        final int arrayLength = array.length;

        if (arrayLength <= 1) {
            return;
        }

        for (int i = 0; i < arrayLength; i++) {
            List<String> asList = Arrays.asList("[^a-zA-Z -]", "^[-]|[-]$");
            for (String s : asList) {
                array[i] = array[i].replaceAll(s, "");
            }
        }

        numberOfWords += arrayLength;
        numberOfLines++;

//        System.out.println(Arrays.toString(array));

        for (int i = 0; i < arrayLength; i++) {
            if (!array[i].equals("")) {
//                System.out.println(array[i]);

                File words = new File("src/com/company/words/english");
                String[] paths = words.list();

                String indexPath = null;

                final int pathLength = paths.length;
                for (int j = 0; j < pathLength; j++) {
                    String s = paths[j];
                    final char temp = array[i].charAt(0);

                    final String path = s.toLowerCase();
                    final char firstLetter = path.charAt(0);
                    final char secondLetter = path.charAt(4);

                    if (temp >= firstLetter && temp <= secondLetter) {
                        indexPath = words.getPath() + "\\" + s;

                        break;
                    }
                }

                try {
                    File fileIndex = new File(indexPath);
                    Scanner scanner = new Scanner(fileIndex);

                    while (scanner.hasNextLine()) {
                        String scannerLine = scanner.nextLine();
                        if (scannerLine.contains(array[i])) {
                            System.out.println("Scanner Line: " + scannerLine);
                            System.out.println("Array[i]: " + array[i]);
                            englishWordsUsed.add(array[i]);
                            numberOfEnglishWords++;
                            break;
                        }
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

//        numberOfEnglishWords = englishWordsUsed.size();
    }

    public String toString() {
        String temp = "";

        temp = "Number of Lines: " + numberOfLines + "\n" +
                "Number of Words: " + numberOfWords + "\n" +
                "Number of Vowels: " + numberOfVowels + "\n" +
                "Number of Constants: " + numberOfConstants + "\n" +
                "Number of English Words: " + numberOfEnglishWords;

        System.out.println(Arrays.toString(englishWordsUsed.toArray()));

        return temp;
    }
}


