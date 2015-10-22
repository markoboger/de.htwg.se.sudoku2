package de.htwg.sudoku.model.impl;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import java.util.BitSet;

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

    @Test
    public void testCandidates() {
        BitSet expected1 = new BitSet(2);
        expected1.set(1,2,true);
        assertEquals(expected1, grid1.candidates(0, 0));

        BitSet expected2 = new BitSet(5);
        expected2.set(1,5,true);
        assertEquals(expected2, grid4.candidates(0, 0));

        BitSet expected3 = new BitSet(5);
        expected3.set(2,5,true);
        grid4.getCell(0,1).setValue(1);
        assertEquals(expected3, grid4.candidates(0, 0));
    }

    @Test
    public void testGetCandidate() {
        assertEquals(1, grid1.getCandidate(0, 0));

        grid4.getCell(0,1).setValue(1);
        grid4.getCell(0,2).setValue(2);
        grid4.getCell(0,3).setValue(3);
        assertEquals(4, grid4.getCandidate(0, 0));
    }

    @Test
    public void testReset() {
        grid1.setCell(0,0,1);
        grid1.reset();

        assertEquals(0, grid1.getCell(0, 0).getValue());
    }

    @Test
    public void testFillSymmetrically() {
        Cell cell, symCell;
        grid4.fillSymmetrically();

        cell =grid4.getRandomCell();
        symCell = grid4.getSymmetricCell(cell);
        assertTrue(cell.isSet()==symCell.isSet());
        assertTrue(cell.isUnSet()==symCell.isUnSet());

        grid9.fillSymmetrically();

        cell =grid9.getRandomCell();
        symCell = grid9.getSymmetricCell(cell);
        assertTrue(cell.isSet()==symCell.isSet());
        assertTrue(cell.isUnSet()==symCell.isUnSet());
    }

    @Test
    public void testCreate() {
        Cell cell, symCell;
        grid4.create();

        cell =grid4.getRandomCell();
        symCell = grid4.getSymmetricCell(cell);
        assertTrue(cell.isSet()==symCell.isSet());
        assertTrue(cell.isUnSet()==symCell.isUnSet());
        assertTrue(cell.isGiven()==symCell.isGiven());

        grid9.create();

        cell =grid9.getRandomCell();
        symCell = grid9.getSymmetricCell(cell);
        assertTrue(cell.isSet()==symCell.isSet());
        assertTrue(cell.isUnSet()==symCell.isUnSet());
        assertTrue(cell.isGiven()==symCell.isGiven());
    }

    @Test
    public void testSolve() {
        assertTrue(grid1.solve());
        assertEquals(2,grid1.getSteps());
        assertEquals("+---+"+newLine+"| 1 |"+newLine+"+---+"+newLine, grid1.toString());
        assertFalse(grid4.isSolved());
        assertTrue(grid4.solve());
        assertTrue(grid4.isSolved());
        assertTrue(grid9.solve());
        assertTrue(grid9.isSolved());
    }
    @Test
    public void testSolveFilled() {
        grid1.setCell(0, 0, 1);
        assertTrue(grid1.solve());
        assertEquals(2,grid1.getSteps());
        grid4.setCell(0, 0, 1);
        assertFalse(grid4.isSolved());
        assertTrue(grid4.solve());
        assertTrue(grid4.isSolved());
        grid4.setCell(0, 0, 1);
        assertTrue(grid9.solve());
        assertTrue(grid9.isSolved());
    }

    @Test
    public void testSolve2() {
        assertFalse(grid1.solve(2));
        assertTrue(grid4.solve(2));
    }
    
    @Test
    public void testParseStringToGrid() {
    	grid1.parseStringToGrid(grid1.toString());
    	grid1.setCell(0,0,1);
    	grid1.parseStringToGrid(grid1.toString());
    	assertTrue(grid1.parseStringToGrid("."));
    	assertTrue(grid1.parseStringToGrid("1"));
    	grid4.parseStringToGrid(grid4.toString());
    	assertFalse(grid4.parseStringToGrid("123456789"));
    	assertTrue(grid4.parseStringToGrid("1234234134214321"));
    	assertTrue(grid4.parseStringToGrid("12..2...342..32."));
    	assertFalse(grid4.parseStringToGrid("nogridhere"));
    }

    @Test
    public void testRemovePairsGridCreateStrategy(){
    	assertTrue(GridCreateStrategyFactory.getStrategy() instanceof RemovePairsGridCreateStrategy);
    	
    	GridCreateStrategyFactory.setStrategy(new RemovePairsGridCreateStrategy());
    	grid1.createStrategy = GridCreateStrategyFactory.getInstance();
    	assertTrue(grid1.createStrategy instanceof RemovePairsGridCreateStrategy);
    	grid1.create();
    	grid1.solve();
    	assertTrue(grid1.isSolved());
    	
    	GridCreateStrategyFactory.setStrategy(new RemovePairsGridCreateStrategy());
    	grid4.createStrategy = GridCreateStrategyFactory.getInstance();
    	assertTrue(grid4.createStrategy instanceof RemovePairsGridCreateStrategy);
    	grid4.create();
    	grid4.solve();
    	assertTrue(grid4.isSolved());
    	
    	GridCreateStrategyFactory.setStrategy(new RemovePairsGridCreateStrategy());
    	grid9.createStrategy = GridCreateStrategyFactory.getInstance();
    	assertTrue(grid9.createStrategy instanceof RemovePairsGridCreateStrategy);
    	grid9.create();
    	grid9.solve();
    	assertTrue(grid9.isSolved());
    }
    
    
    @Test
    public void testRandomGridCreateStrategy(){
    	GridCreateStrategyFactory.setStrategy(new RandomGridCreateStrategy());
    	grid4.createStrategy = GridCreateStrategyFactory.getInstance();
    	assertTrue(grid4.createStrategy instanceof RandomGridCreateStrategy);
    	grid4.create();
    	   	
    	GridCreateStrategyFactory.setStrategy(new RandomGridCreateStrategy());
    	grid9.createStrategy = GridCreateStrategyFactory.getInstance();
    	assertTrue(grid9.createStrategy instanceof RandomGridCreateStrategy);
    	grid9.create();
    }


    /* Methods */
    private boolean assertReachAllCells(Grid grid) {
        int cellsPerEdge=grid.getSize();
        for (int row = 0; row < cellsPerEdge; row++) {
            for (int column = 0; column < cellsPerEdge; column++) {
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