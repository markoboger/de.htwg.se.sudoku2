package de.htwg.sudoku.model;

/**
 * The Grid is the playing field of a Sudoku puzzle.
 * It consists of Cells.
 * Cells are organized in Houses.
 * The Grid has a size, which is the number of cells in a row or column. Size must be 1, 4, or 9.
 */
public class Grid {

/* Fields */
    private static final int MAXSIZE = 9;

    private int size;

    private Cell[][] cells;
    private House[] rows;
    private House[] columns;
    private House[] blocks;

/* Constructors */
    public Grid(int size) {
        if (size <= 1 || MAXSIZE < size) {
            throw new IllegalArgumentException(
                    "size must be between 1 and " + MAXSIZE);
        }
        if (Math.round(Math.sqrt(size))==Math.sqrt(size)) {
            throw new IllegalArgumentException(
                    "size must be a square of a natural number");
        }
        setSize(size);

        // create Cell and Houses
        cells = new Cell[getSize()][getSize()];
        rows = new House[getSize()];
        columns = new House[getSize()];
        blocks = new House[getSize()];
    }

/* Getter and Setter */
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    public Cell getCell(int row, int column) {
        return cells[row][column];
    }

    public void setCell(int row, int column, int value) {
        cells[row][column].setValue(value);
    }

    public House[] getRows() {
        return rows;
    }

    public void setRows(House[] rows) {
        this.rows = rows;
    }

    public House[] getColumns() {
        return columns;
    }

    public void setColumns(House[] columns) {
        this.columns = columns;
    }

    public House[] getBlocks() {
        return blocks;
    }

    public void setBlocks(House[] blocks) {
        this.blocks = blocks;
    }


/* Methods */
}
