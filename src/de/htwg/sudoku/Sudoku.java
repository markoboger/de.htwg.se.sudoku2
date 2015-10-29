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
	private TextUI tui;
	@SuppressWarnings("unused")
	private SudokuFrame gui;
	protected ISudokuController controller;
	private static Sudoku instance = null;

	/* Constructor */
	private Sudoku() {
		// Set up Google Guice Dependency Injector
		Injector injector = Guice.createInjector(new SudokuModule());

		// Build up the application, resolving dependencies automatically by
		// Guice
		controller = injector.getInstance(ISudokuController.class);
		tui = new TextUI(controller);
		gui = new SudokuFrame(controller);

		// Create an initial game
		controller.create();
	}

	/* Methods */

	public static Sudoku getInstance() {
		if (instance == null) {
			instance = new Sudoku();
		}
		return instance;
	}

	public TextUI getTui() {
		return tui;
	}
    
    public ISudokuController getController() {
    	return controller;
    }
    
	public static void main(String[] args) {

		Sudoku game = Sudoku.getInstance();

		if (args == null) {
			// continue to read user input on the tui until the user decides to
			// quit
			boolean continu = true;
			scanner = new Scanner(System.in);
			while (continu) {
				continu = game.tui.processInputLine(scanner.next());
			}
		} else {
			for (String input : args) {
				game.tui.processInputLine(input);
			}

		}
	}

}
