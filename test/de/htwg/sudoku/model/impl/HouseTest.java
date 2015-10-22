package de.htwg.sudoku.model.impl;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.BitSet;

public class HouseTest {
/* Fields */
    House house1;
    House house2;
    House house4;
    House house9;
    House fullHouse;

/* Setup */
    @Before
    public void setUp() {
        house1 = new House(1);
        house2 = new House(2);
        house4 = new House(4);
        house9 = new House(9);

        fullHouse = new House(2);
        fullHouse.getCell(0).setValue(1);
        fullHouse.getCell(1).setValue(2);
    }

/* Tests */

    @Test
    public void testGetSize() {
        assertEquals(1,house1.getSize());
        assertEquals(2,house2.getSize());
    }

    @Test
    public void testadd() {
        assertEquals(1,house1.getSize());
        house1.setCell(0,new Cell(0,0));
        assertEquals(1,house1.getSize());
    }

    @Test
    public void testGetCell() {
        assertEquals(1, fullHouse.getCell(0).getValue());
        assertEquals(2, fullHouse.getCell(1).getValue());
    }

    @Test
    public void testGetBlockSize() {
        assertEquals(1, house1.getBlockSize());
        assertEquals(2, house4.getBlockSize());
        assertEquals(3, house9.getBlockSize());
    }


    @Test
    public void testCandidates() {
        BitSet expected1 = new BitSet(2);
        expected1.set(1,2,true);
        assertEquals(expected1, house1.candidates());

        BitSet expected2 = new BitSet(3);
        expected2.set(1,3,true);
        assertEquals(expected2,house2.candidates());

        BitSet expected3 = new BitSet(5);
        expected3.set(1,5,true);
        assertEquals(expected3,house4.candidates());
    }

    @Test
    public void testToStringWithZero() {
        assertEquals("| . |", house1.toString("."));
        assertEquals("| . . | . . |", house4.toString("."));
        assertEquals("| . . . | . . . | . . . |", house9.toString("."));
    }

    @Test
    public void testToString() {
        assertEquals("|   |", house1.toString());
        house1.getCell(0).setValue(1);
        assertEquals("| 1 |", house1.toString());
    }

    @Test
    public void testCountSetCells() {
        assertEquals(0,house1.countSetCells());
        house1.getCell(0).setValue(1);
        assertEquals(1,house1.countSetCells());

        assertEquals(0,house4.countSetCells());
        house4.getCell(0).setValue(1);
        assertEquals(1,house4.countSetCells());
        house4.getCell(1).setValue(2);
        assertEquals(2,house4.countSetCells());
        house4.getCell(2).setValue(3);
        assertEquals(3,house4.countSetCells());
        house4.getCell(3).setValue(4);
        assertEquals(4,house4.countSetCells());

    }

}