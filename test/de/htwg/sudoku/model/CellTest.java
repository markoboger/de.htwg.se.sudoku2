package de.htwg.sudoku.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Cell
 */
public class CellTest {
/* Fields */
    private Cell cell;

/* Setup */
    @Before
    public void setUp() throws Exception {
        cell = new Cell(1,2);
    }

/* Tests */
    @Test
    public void testGetValue() {
        cell.setValue(0);
        assertEquals(0, cell.getValue());
        cell.setValue(1);
        assertEquals(1, cell.getValue());
    }

    @Test
    public void testGetRow() {
        assertEquals(1, cell.getRow());
    }

    @Test
    public void testGetColumn() {
        assertEquals(2,cell.getColumn());

    }

/* Teardown */
}
