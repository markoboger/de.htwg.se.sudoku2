package de.htwg.sudoku.controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SudokuControllerTest {
/* Fields */
    SudokuController controller1, controller4, controller9;

/* Setup */
    @Before
    public void setUp() {
        controller1 = new SudokuController(1);
        controller4 = new SudokuController(4);
        controller9 = new SudokuController(9);
    }
/* Tests */
    @Test
    public void testGetStatus() {
        assertEquals(GameStatus.WELCOME, controller1.getStatus());
        assertEquals(GameStatus.WELCOME, controller4.getStatus());
        assertEquals(GameStatus.WELCOME, controller9.getStatus());
    }

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
    public void testSetValue() {
        controller4.setValue(1,1,1);
        assertEquals(1,controller4.getValue(1,1));
    }
}