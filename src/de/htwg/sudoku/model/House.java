package de.htwg.sudoku.model;

import java.util.BitSet;

/**
 * A House is a logical collection of Cells.
 * It is an abstraction over Rows, Columns, and Blocks in a Sudoku puzzle.
 * Its size is the number of cells it references.
 */
public class House {

/* Fields */
    private int size;
    private int blockSize;
    private Cell[] cells;

/* Constructors */
    public House(int size) {
        setSize(size);
        setBlockSize((int) Math.sqrt(size));
        cells = new Cell[getSize()];
        for (int index = 0; index < getSize(); index++) {
            getCells()[index] = new Cell(0, index);
        }
    }

/* Getter and Setter */
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Cell[] getCells() {
        return cells;
    }

    public Cell getCell(int index) {
        return getCells()[index];
    }

    public void setCell(int index, Cell cell) {
        getCells()[index] = cell;
    }

    public int getBlockSize() {
        return blockSize;
    }

    protected void setBlockSize(int blockSize) {
        this.blockSize = blockSize;
    }


/* Methods */

    /**
     * returns the values that are not yet used in this house as set.
     */
    public BitSet candidates() {
        BitSet candidates = new BitSet(getSize() + 1);
        candidates.set(1, getSize() + 1, true);
        for (int index = 0; index < getSize(); index++) {
            candidates.set(getCells()[index].getValue(), false);
        }
        return candidates;
    }

    public int countSetCells() {
        int count = 0;
        for (int index = 0; index < getSize(); index++) {
            if (getCells()[index].getValue() > 0) {
                count++;
            }
        }
        return count;
    }

    public int countUnsetCells() {
        int count = 0;
        for (int index = 0; index < getSize(); index++) {
            if (getCells()[index].getValue() == 0) {
                count++;
            }
        }
        return count;
    }


    /**
     * returns a String of the form | 1 2 3 | 4 5 6 | 7 8 9 |
     */
    @Override
    public String toString() {
        return toString(" ");
    }

    public String toString(String zero) {
        String result = "|";
        for (int index = 0; index < size; index++) {
            result += " " + getCells()[index].toString(zero);
            if (((index + 1) % blockSize) == 0) {
                result += " |";
            }
        }
        return result;
    }
}
