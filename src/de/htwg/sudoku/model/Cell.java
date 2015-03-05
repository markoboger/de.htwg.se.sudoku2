package de.htwg.sudoku.model;

/**
 * A cell is the most fundamental field in a Sudoku puzzle.
 * It can be set to a value.
 * It has a row and column as coordinates.
 */
public class Cell {

/* Fields */
    private int value;
    private int row;
    private int column;

/* Constructors */
    public Cell(int row, int column) {
        setRow(row);
        setColumn(column);
    }

/* Getter and Setter */
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

/* Methods */
}
