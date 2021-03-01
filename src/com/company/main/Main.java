package com.company.main;

import com.company.app.WordCounter;
import com.company.app.WordCounter1;
import com.company.languages.English;
import com.company.languages.Language;
import com.company.languages.Tagalog;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public void start(String[] command) {
        System.out.println(Arrays.toString(command));
        final String languageCode = command[0];
        final String inputUrl = command[1];
        final String outputUrl = command[3];

        final String fixedInputUrl = !inputUrl.contains("/") ? "inputs/" + inputUrl : inputUrl;
        final File inputFile = new File(fixedInputUrl);

        Language language = null;
        final File languageFileIndex;

        String languageFileIndexPath = "src/com/company/words/";
        if (languageCode.equals("-e")) {
            languageFileIndex = new File(languageFileIndexPath + "english");
            language = new English(languageFileIndex);
        }

        else if (languageCode.equals("-f")) {
            languageFileIndex = new File(languageFileIndexPath + "tagalog");
            language = new Tagalog(languageFileIndex);
        }


//        final WordCounter wordCounter = new WordCounter();
        final WordCounter1 wordCounter = new WordCounter1(language);
        final Scanner scanner;

        try {
            scanner = new Scanner(inputFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                wordCounter.count(line);
            }

            final String fixedOutputUrl = !outputUrl.contains("/") ? "outputs/" + outputUrl : outputUrl;
            final OutputStream outputFile = new FileOutputStream(fixedOutputUrl);

            final String outputString = wordCounter.toString();
            final byte[] outputByte =  outputString.getBytes(StandardCharsets.UTF_8);

            outputFile.write(outputByte);

        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(wordCounter.toString());
    }
}
