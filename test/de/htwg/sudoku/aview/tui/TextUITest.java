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
		assertEquals(0,controller.getValue(0, 0));
		tui.processSingleCharInput("c");
		tui.processSingleCharInput("s");
		assertNotEquals(0,controller.getValue(0, 0));
		tui.processSingleCharInput(".");
		assertEquals(1, controller.getSize());
		tui.processSingleCharInput("+");
		assertEquals(4, controller.getSize());
		tui.processSingleCharInput("#");
		assertEquals(9, controller.getSize());		
	}
	
	@Test
	public void testProcessInput() {
		tui.processInputLine("r");
		tui.processInputLine("111");
		assertEquals(1,controller.getValue(1, 1));
		tui.processInputLine("u");
		assertEquals(0,controller.getValue(1, 1));
		tui.processInputLine("y");
		assertEquals(1,controller.getValue(1, 1));
		tui.processInputLine("00");
		assertTrue(controller.getStatusText().contains("2"));
		assertFalse(controller.getStatusText().contains("1"));
		tui.processInputLine("1");
		tui.processInputLine("f");
		assertEquals(1,controller.getValue(1, 1));
		tui.processInputLine("XXX");
		tui.processInputLine("n");
		tui.processInputLine("s");
		assertNotEquals(0,controller.getValue(0, 0));
	}

	@Test
	public void testQuit() {
		assertFalse(tui.processInputLine("q"));
	}
	
	@Test
	public void testToHtml() {
		assertTrue(tui.toHtml().contains("<br>"));
	}
	

}
