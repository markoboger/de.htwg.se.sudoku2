package de.htwg.sudoku.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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

}