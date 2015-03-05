package de.htwg.sudoku.model;

/**
 * A House is a logical collection of Cells.
 * It is an abstraction over Rows, Colums, and Blocks in a Sudoku puzzle.
 * Its size is the number of cells it references.
 */
public class House {

/* Fields */
    private int size;
    private Cell[] cells;

/* Constructors */
    public House(int size) {
        setSize(size);
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

    public void setCells(Cell[] cells) {
        this.cells = cells;
    }

    public Cell getCell(int index) {
        return getCells()[index];
    }

    public void setCell(int index, Cell cell) {
        getCells()[index] = cell;
    }


/* Methods */
}
