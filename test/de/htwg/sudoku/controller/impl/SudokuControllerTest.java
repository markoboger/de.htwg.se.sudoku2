package de.htwg.sudoku.controller.impl;

import org.junit.Before;
import org.junit.Test;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.sudoku.SudokuTestModule;
import de.htwg.sudoku.controller.GameStatus;
import de.htwg.sudoku.controller.ISudokuController;
import static org.junit.Assert.*;

public class SudokuControllerTest {
/* Fields */
    String newLine = System.getProperty("line.separator");
    ISudokuController controller1, controller4, controller9;
 // Set up Google Guice Dependency Injector
    Injector injector = Guice.createInjector(new SudokuTestModule());

/* Setup */
    @Before
    public void setUp() {
        controller1 = injector.getInstance(ISudokuController.class);
        controller1.resetSize(1);
        controller4 = injector.getInstance(ISudokuController.class);
        controller4.resetSize(4);
        controller9 = injector.getInstance(ISudokuController.class);
        controller9.resetSize(9);
    }
    
/* Tests */
    @Test
    public void testSetGrid() {
        assertEquals(1,controller1.getSize());
        controller1.setGrid(4);
        assertEquals(4,controller1.getSize());
        controller1.setGrid(9);
        assertEquals(9,controller1.getSize());
        controller1.setGrid(4);
        assertEquals(4,controller1.getSize());
        controller1.setGrid(1);
        assertEquals(1,controller1.getSize());
    }

    @Test
    public void testSetGridWithIllegalArgument() {
        controller4.setGrid(3);
        assertEquals(GameStatus.ILLEGAL_ARGUMENT,controller4.getStatus());
    }


    @Test
    public void testSetValue() {
        controller4.setValue(1,1,1);
        assertEquals(1,controller4.getValue(1,1));
        assertEquals(GameStatus.CELL_SET_SUCCESS,controller4.getStatus());

        controller4.setValue(1,1,1);
        assertEquals(1,controller4.getValue(1,1));
        assertEquals(GameStatus.CELL_SET_FAIL,controller4.getStatus());
    }

    @Test
    public void testCreate() {
        controller4.create();
        assertEquals(GameStatus.CREATE, controller4.getStatus());
        assertEquals("",controller4.getStatusText());
    }

    @Test
    public void testReset() {
        controller4.reset();
        assertEquals(GameStatus.RESET,controller4.getStatus());
        assertEquals("",controller4.getStatusText());
    }

    @Test
    public void testShowCandidates() {
        controller4.create();
        controller4.showCandidates(0,0);
        assertEquals(GameStatus.SHOW_CANDIDATES, controller4.getStatus());
    }

    @Test
    public void testGetGridString() {
        String grid = controller1.getGridString();
        assertEquals("+---+"+newLine+"|   |"+newLine+"+---+"+newLine,grid);
    }

    @Test
    public void testSolve() {
        controller1.solve();
        assertEquals(GameStatus.SOLVE_SUCCESS,controller1.getStatus());
    }
    
    @Test
    public void testUndo() {
    	controller1.setValue(0,0,1);
    	controller1.undo();
    	assertEquals(0,controller1.getValue(0,0));
    	
    	controller1.setValue(0,0,1);
    	controller1.reset();
    	controller1.undo();
    	assertEquals(1,controller1.getValue(0,0));
    	
    	controller1.reset();
    	controller1.solve();
    	controller1.undo();
    	assertEquals(0,controller1.getValue(0,0));
    	
    	controller1.setValue(0,0,1);
    	controller1.create();
    	controller1.undo();
    	assertEquals(1,controller1.getValue(0,0));
    }

    @Test
	public void testSolveFails() {
		controller4.reset();
		controller4.setValue(0, 0, 1);
		controller4.setValue(1, 0, 1);
		controller4.setValue(1, 1, 1);
		controller4.solve();

		assertEquals(GameStatus.SOLVE_FAIL,controller4.getStatus());
	}

}