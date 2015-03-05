package de.htwg.sudoku.model;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

public class GridTest {

/* Fields */
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

        grid1.setCell(0,0,1);
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