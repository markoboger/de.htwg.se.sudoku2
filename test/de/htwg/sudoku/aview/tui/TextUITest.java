package de.htwg.sudoku.aview.tui;

import static org.junit.Assert.*;

import org.junit.Test;

import de.htwg.sudoku.controller.SudokuController;

public class TextUITest {
	
	TextUI tui = new TextUI(new SudokuController(9));

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
