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
		assertFalse(tui.processSingleCharInput("q"));
		assertTrue(tui.processSingleCharInput("r"));
		assertTrue(tui.processSingleCharInput("n"));
		assertTrue(tui.processSingleCharInput("s"));
		assertTrue(tui.processSingleCharInput("u"));
		assertTrue(tui.processSingleCharInput("."));
		assertTrue(tui.processSingleCharInput("+"));
		
	}

}
