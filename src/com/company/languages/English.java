package com.company.languages;

import java.io.File;

public class English extends Language {
    public English(File file) {
        super(
                "English",
                "Vowel",
                "Consonant",
                new char[] { 'a', 'e', 'i', 'o', 'u'},
                new char[] {'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z'},
                file);
    }
}
