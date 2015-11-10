package de.htwg.sudoku;

import static org.junit.Assert.*;

import org.junit.Test;

public class SudokuTest {
	
//	@Test
//	public void testMain() {
//		Sudoku.main(new String[] {"#", "n", "r", "s", "+", "001", "00", "s", "u", "u", ".", "H", "s"});
//		assertEquals(1, Sudoku.getInstance().getController().getValue(0,0));
//	}
	
	@Test
	public void testGetInstance() {
		Sudoku game = Sudoku.getInstance();
		assertNotNull(game);
		assertTrue(game.getTui().toString().contains("+---"));
	}
	
	

}
