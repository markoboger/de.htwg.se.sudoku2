package de.htwg.sudoku.model.impl;

import de.htwg.sudoku.model.ICell;

/**
 * A cell is the most fundamental field in a Sudoku puzzle.
 * It can be set to a value.
 * It has a row and column as coordinates.
 */
public class Cell implements ICell{

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
    @Override
    public int getValue() {
        return value;
    }

    @Override
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

    @Override
    public boolean isSet() {
        return value == 0 ? false : true;
    }

    @Override
    public boolean isUnSet() {
        return !isSet();
    }

    public void setGiven(boolean b) {
        given = b;
    }

    @Override
    public boolean isGiven() {
        return given;
    }

    @Override
    public void setShowCandidates(boolean showCandidates) {
        this.showCandidates = showCandidates;
    }

    @Override
    public boolean isShowCandidates() {
        return showCandidates;
    }

    /* Methods */
    public void reset() {
        setValue(0);
        setGiven(false);
        setShowCandidates(false);
    }

    @Override
    public void toggleShowCandidates() {
        showCandidates = !showCandidates;
    }

    @Override
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
