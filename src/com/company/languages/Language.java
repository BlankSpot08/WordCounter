package com.company.languages;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

public abstract class Language {
    private final File languageFileIndex;
    private final Set<String> languageWordsUsed;
    private final char[] consonants;
    private final char[] vowels;
    private int numberOfConsonants;
    private int numberOfVowels;
    private int numberOfLines;
    private int numberOfWords;
    private int numberOfLanguageWords;

    public String getName() {
        return this.name;
    }
    public String getLanguageNameOfConsonants() {
        return this.languageNameOfConsonants;
    }
    public String getLanguageNameOfVowels() {
        return this.languageNameOfVowels;
    }

    public int getNumberOfConsonants() {
        return numberOfConsonants;
    }

    public void setNumberOfConsonants(int numberOfConsonants) {
        this.numberOfConsonants = numberOfConsonants;
    }

    public int getNumberOfVowels() {
        return numberOfVowels;
    }

    public void setNumberOfVowels(int numberOfVowels) {
        this.numberOfVowels = numberOfVowels;
    }

    public int getNumberOfLines() {
        return numberOfLines;
    }

    public Set<String> getLanguageWordsUsed() {
        return languageWordsUsed;
    }

    public void setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    public int getNumberOfWords() {
        return numberOfWords;
    }

    public void setNumberOfWords(int numberOfWords) {
        this.numberOfWords = numberOfWords;
    }

    public int getNumberOfLanguageWords() {
        return numberOfLanguageWords;
    }

    public void setNumberOfLanguageWords(int numberOfLanguageWords) {
        this.numberOfLanguageWords = numberOfLanguageWords;
    }

    public char[] getConsonants() {
        return consonants;
    }

    public char[] getVowels() {
        return vowels;
    }

    public File getLanguageFileIndex() {
        return languageFileIndex;
    }

    protected String name;
    protected String languageNameOfConsonants;
    protected String languageNameOfVowels;

    public Language(String name, String languageNameOfConsonants, String languageNameOfVowels, char[] consonants, char[] vowels, File languageFileIndex) {
        languageWordsUsed = new HashSet<>();
        numberOfConsonants = 0;
        numberOfVowels = 0;
        numberOfLines = 0;
        numberOfWords = 0;

        this.name = name;
        this.languageNameOfConsonants = languageNameOfConsonants;
        this.languageNameOfVowels = languageNameOfVowels;
        this.consonants = consonants;
        this.vowels = vowels;
        this.languageFileIndex = languageFileIndex;
    }
}
