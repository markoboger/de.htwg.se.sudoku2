package de.htwg.sudoku.aview.tui;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.sudoku.SudokuTestModule;
import de.htwg.sudoku.controller.ISudokuController;

public class TextUITest {
	 // Set up Google Guice Dependency Injector
    Injector injector = Guice.createInjector(new SudokuTestModule());
    
    ISudokuController controller = injector.getInstance(ISudokuController.class);
	
	TextUI tui = new TextUI(controller);

	@Test
	public void testProcessSingleCharInput() {
		tui.processSingleCharInput("r");
		tui.processSingleCharInput("n");
		tui.processSingleCharInput("s");
		tui.processSingleCharInput("u");
		tui.processSingleCharInput(".");
		tui.processSingleCharInput("+");
		
	}

}
