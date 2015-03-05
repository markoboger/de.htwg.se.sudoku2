package de.htwg.sudoku.model;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class GridTest {

/* Fields */
    String newLine = System.getProperty("line.separator");
    private Grid grid1, grid4, grid9;

/* Setup */
    @Before
    public void setUp()  {
        grid1 = new Grid(1);
        grid4 = new Grid(4);
        grid9 = new Grid(9);
    }

/* Tests */
    @Test(expected = IllegalArgumentException.class)
    public void testGridArgumentTooSmall() {
        new Grid(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGridArgumentNotSquareOfNaturalNumber() {
        new Grid(2);
    }

    @Test
    public void testCellsPerEdge()  {
        assertEquals(1, grid1.getSize());
        assertEquals(4, grid4.getSize());
        assertEquals(9, grid9.getSize());
    }

    @Test
    public void testGetBlockSize() {
        assertEquals(1,grid1.getBlockSize());
        assertEquals(2,grid4.getBlockSize());
        assertEquals(3,grid9.getBlockSize());
    }

    @Test
    public void testSetCell(){
        assertEquals(0,grid1.getCell(0,0).getValue());

        grid1.setCell(0, 0, 1);
        assertEquals(1,grid1.getCell(0,0).getValue());
    }

    @Test
    public void testGetCell() {
        assertReachAllCells(grid1);
    }

    @Test
    public void testIntSqrt() {
        assertEquals(1,Grid.intSqrt(1));
        assertEquals(1,Grid.intSqrt(2));
        assertEquals(1,Grid.intSqrt(3));
        assertEquals(2,Grid.intSqrt(4));
        assertEquals(2,Grid.intSqrt(5));
        assertEquals(2,Grid.intSqrt(6));
        assertEquals(2,Grid.intSqrt(7));
        assertEquals(2,Grid.intSqrt(8));
        assertEquals(3,Grid.intSqrt(9));
    }

    @Test
    public void testIsSquareOfNaturalNumber() {
        assertTrue(Grid.isSquareOfNaturalNumber(1));
        assertTrue(Grid.isSquareOfNaturalNumber(4));
        assertTrue(Grid.isSquareOfNaturalNumber(9));
        assertTrue(Grid.isSquareOfNaturalNumber(25));

        assertFalse(Grid.isSquareOfNaturalNumber(2));
        assertFalse(Grid.isSquareOfNaturalNumber(3));
        assertFalse(Grid.isSquareOfNaturalNumber(5));
        assertFalse(Grid.isSquareOfNaturalNumber(6));
    }

    @Test
    public void testBlocksPerEdge() {
        assertEquals(1,Grid.blocksPerEdge(1));
        assertEquals(2,Grid.blocksPerEdge(4));
        assertEquals(3,Grid.blocksPerEdge(9));
    }

    @Test
    public void testBlockAt() {
        assertEquals(0,grid1.blockAt(0,0));

        assertEquals(0,grid4.blockAt(0, 0));
        assertEquals(0,grid4.blockAt(0,1));
        assertEquals(0,grid4.blockAt(1,0));
        assertEquals(0,grid4.blockAt(1,1));

        assertEquals(1,grid4.blockAt(0,2));
        assertEquals(1,grid4.blockAt(0,3));
        assertEquals(1,grid4.blockAt(1,2));
        assertEquals(1,grid4.blockAt(1,3));

        assertEquals(2,grid4.blockAt(2,0));
        assertEquals(2,grid4.blockAt(2,1));
        assertEquals(2,grid4.blockAt(3,0));
        assertEquals(2,grid4.blockAt(3,1));

        assertEquals(3,grid4.blockAt(2,2));
        assertEquals(3,grid4.blockAt(2,3));
        assertEquals(3,grid4.blockAt(3,2));
        assertEquals(3,grid4.blockAt(3,3));
    }

    @Test
    public void testCellInBlockAt(){
        assertEquals(0,grid1.cellInBlockAt(0,0));

        assertEquals(0,grid4.cellInBlockAt(0,0));
        assertEquals(1,grid4.cellInBlockAt(1,0));
        assertEquals(2,grid4.cellInBlockAt(0,1));
        assertEquals(3,grid4.cellInBlockAt(1,1));

        assertEquals(0,grid4.cellInBlockAt(0,2));
        assertEquals(1,grid4.cellInBlockAt(1,2));
        assertEquals(2,grid4.cellInBlockAt(0,3));
        assertEquals(3,grid4.cellInBlockAt(1,3));
    }

    @Test
    public void testBlockSeparator() {
        assertEquals("+---+", grid1.blockSeparator(1));
        assertEquals("+-----+-----+", grid1.blockSeparator(2));
        assertEquals("+-------+-------+-------+", grid1.blockSeparator(3));
    }

    @Test
    public void testToString() {
        assertEquals("+---+"+newLine+"|   |"+newLine+"+---+"+newLine, grid1.toString());
        grid1.setCell(0,0,1);
        assertEquals("+---+"+newLine+"| 1 |"+newLine+"+---+"+newLine, grid1.toString());
    }

    @Test
    public void testGetRandomCell() {
        // A random cell out of grid1 has to be cell(0,0) with value 0
        Cell pseudoRandomCell = grid1.getRandomCell();
        assertEquals(0, pseudoRandomCell.getColumn());
        assertEquals(0, pseudoRandomCell.getRow());
        assertEquals(0,pseudoRandomCell.getValue());

        // A random cell out of grid4 has to be unset
        Cell realRandomCell = grid4.getRandomCell();
        assertTrue(realRandomCell.isUnSet());
    }

    @Test
    public void testGetSymmetricCell() {
        Cell cell, symCell;

        cell = grid4.getCell(0,0);
        symCell = grid4.getSymmetricCell(cell);
        assertEquals(grid4.getCell(3, 3),symCell);

        cell = grid4.getCell(0,3);
        symCell = grid4.getSymmetricCell(cell);
        assertEquals(grid4.getCell(3,0),symCell);

        cell = grid4.getCell(1,2);
        symCell = grid4.getSymmetricCell(cell);
        assertEquals(grid4.getCell(2,1),symCell);
    }


/* Methods */
    private boolean assertReachAllCells(Grid grid) {
        int cellsPerEdge=grid.getSize();
        for (int row = 0; row < grid.getSize(); row++) {
            for (int column = 0; column < grid.getSize(); column++) {
                assertCellIsAt(row,column,grid);
            }
        }
        return true;
    }

    public boolean assertCellIsAt( int row, int column, Grid grid){
        assertEquals(row,grid.getCell(row, column).getRow());
        assertEquals(column,grid.getCell(row, column).getColumn());
        return true;
    }

}