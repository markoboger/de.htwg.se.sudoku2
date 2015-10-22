package de.htwg.sudoku;

import de.htwg.sudoku.aview.gui.SudokuFrame;
import de.htwg.sudoku.aview.tui.TextUI;
import de.htwg.sudoku.controller.ISudokuController;

import java.util.Scanner;

import com.google.inject.Guice;
import com.google.inject.Injector;

public final class Sudoku {
	/* Fields */
	private static Scanner scanner;
	private static TextUI tui;
	@SuppressWarnings("unused")
	private static SudokuFrame gui;
	protected static ISudokuController controller;

	/* Constructor */
	private Sudoku() {

	}

	/* Methods */
	public static void main(String[] args) {
		
        // Set up Google Guice Dependency Injector
        Injector injector = Guice.createInjector(new SudokuModule());

        // Build up the application, resolving dependencies automatically by
        // Guice
        controller = injector.getInstance(ISudokuController.class);
        @SuppressWarnings("unused")
        SudokuFrame gui = injector.getInstance(SudokuFrame.class);
        tui = injector.getInstance(TextUI.class);
     

        gui = new SudokuFrame(controller);
		tui = new TextUI(controller);

		// Create an initial game
		controller.create();

		if (args == null) {
			// continue to read user input on the tui until the user decides to
			// quit
			boolean continu = true;
			scanner = new Scanner(System.in);
			while (continu) {
				continu = tui.processInputLine(scanner.next());
			}
		} else {
			for (String input:args) {
				tui.processInputLine(input);
			}

		}
	}

}
