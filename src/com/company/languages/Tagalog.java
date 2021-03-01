package com.company.languages;

import java.io.File;

public class Tagalog extends Language{
    public Tagalog(File file) {
        super(
                "Tagalog",
                "Katinig",
                "Patinig",
                new char[] { 'a', 'e', 'i', 'o', 'u'},
                new char[] {
                        'b', 'c', 'd', 'f', 'g',
                        'h', 'j', 'k', 'l', 'm',
                        'n', 'p', 'q', 'r', 's',
                        't', 'v', 'w', 'x', 'y', 'z'},
                file);
    }
}
