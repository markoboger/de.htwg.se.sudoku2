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
    private boolean given;
    private boolean showCandidates;

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

    public boolean isSet() {
        return value == 0 ? false : true;
    }

    public boolean isUnSet() {
        return !isSet();
    }

    public void setGiven(boolean b) {
        given = b;
    }

    public boolean isGiven() {
        return given;
    }

    public void setShowCandidates(boolean showCandidates) {
        this.showCandidates = showCandidates;
    }

    public boolean isShowCandidates() {
        return showCandidates;
    }

    /* Methods */
    public void reset() {
        setValue(0);
        setGiven(false);
        setShowCandidates(false);
    }

    public void toggleShowCandidates() {
        showCandidates = !showCandidates;
    }

    public String mkString() {
        return "(" + row + "," + column + ") = " + value;
    }

    public String toString(String zero) {
        if (value == 0) {
            return zero;
        } else {
            return "" + value;
        }
    }
}
