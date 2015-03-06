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
        assertEquals("Welcome to HTWG Sudoku!", controller1.getStatus());
    }
}