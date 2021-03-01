package com.company;

import com.company.main.EntryPoint;

public class Run {
    public static void main(String[] args) {
        String command = "-f Python.txt -o Result.txt";
        String[] splittedCommand = command.split(" ");

        EntryPoint.main(splittedCommand);
    }
}
