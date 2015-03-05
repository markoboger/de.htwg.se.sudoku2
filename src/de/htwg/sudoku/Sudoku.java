package de.htwg.sudoku;


import de.htwg.sudoku.aview.tui.TextUI;
import de.htwg.sudoku.controller.SudokuController;
import java.util.Scanner;

public final class Sudoku {

    private static Scanner scanner;
    private static TextUI tui;
    private SudokuController controller;
    private static Sudoku sudoku;

    private Sudoku() {

        // Build up the application, resolving dependencies automatically by
        // Guice
        controller = new SudokuController(9);

        tui = new TextUI(controller);
        tui.printTUI();

        // Create an initial game
        controller.create();
    }

    public static void main(String[] args) {

        sudoku = new Sudoku();

        // continue to read user input on the tui until the user decides to quit
        boolean continu = true;
        scanner = new Scanner(System.in);
        while (continu) {
            continu = tui.processInputLine(scanner.next());
        }
    }

}
