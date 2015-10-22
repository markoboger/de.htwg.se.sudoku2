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

    @Test
    public void testIsGiven() {
        assertFalse(cell.isGiven());
        cell.setGiven(true);
        assertTrue(cell.isGiven());
    }

    @Test
    public void testIsSet() {
        assertFalse(cell.isSet());
        assertTrue(cell.isUnSet());
        cell.setValue(3);
        assertTrue(cell.isSet());
        assertFalse(cell.isUnSet());
    }

    @Test
    public void testToggleShowCandidates() {
        assertFalse(cell.isShowCandidates());
        cell.toggleShowCandidates();
        assertTrue(cell.isShowCandidates());
        cell.toggleShowCandidates();
        assertFalse(cell.isShowCandidates());
    }

    @Test
    public void testReset() {
        cell.setGiven(true);
        cell.toggleShowCandidates();
        cell.setValue(1);
        cell.reset();
        assertFalse(cell.isGiven());
        assertFalse(cell.isShowCandidates());
        assertEquals(0, cell.getValue());
    }

    @Test
    public void testMkString() {
        assertEquals("(1,2) = 0", cell.mkString());
        cell.setValue(3);
        assertEquals("(1,2) = 3", cell.mkString());
    }

    @Test
    public void testToString() {
        assertEquals("-", cell.toString("-"));
        cell.setValue(3);
        assertEquals("3", cell.toString("-"));
    }



/* Teardown */
}
