package de.htwg.sudoku;


import de.htwg.sudoku.aview.tui.TextUI;
import de.htwg.sudoku.controller.SudokuController;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class Sudoku {
/* Fields */
    private static Scanner scanner;
    private static TextUI tui;
    private static SudokuController controller;

/* Constructor */
    private Sudoku() {

    }

/* Methods */
    public static void main(String[] args) {

        try {
            System.setProperty("log4j.configuration", new File(".", "resources"+File.separatorChar+"log4j.xml").toURI().toURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        // Build up the application
        controller = new SudokuController(9);

        tui = new TextUI(controller);

        // Create an initial game
        controller.create();

        // continue to read user input on the tui until the user decides to quit
        boolean continu = true;
        scanner = new Scanner(System.in);
        while (continu) {
            continu = tui.processInputLine(scanner.next());
        }
    }

}
