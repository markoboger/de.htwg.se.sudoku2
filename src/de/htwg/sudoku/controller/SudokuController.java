package de.htwg.sudoku.controller;

import de.htwg.sudoku.model.Cell;
import de.htwg.sudoku.model.Grid;
import util.observer.Observable;

import java.util.BitSet;

/**
 * Main controller of Sudoku
 */
public class SudokuController extends Observable {
/* Fields */
    private String statusLine = "Welcome to HTWG Sudoku!";
    private int highlighted = 0;
    private Grid grid;

/* Constructors */
    public SudokuController(int size) {
        this.grid = new Grid(size);
    }

/* Getter and Setter */
    public void setValue(int row, int column, int value) {
        Cell cell = grid.getCell(row, column);
        if (cell.isUnSet()) {
            cell.setValue(value);
            statusLine = "The cell " + cell.mkString()
                    + " was successfully set";
        } else {
            statusLine = "The cell " + cell.mkString() + " is already set";
        }
        notifyObservers();
    }

    public String getStatus() {
        return statusLine;
    }

    public String getGridString() {
        return grid.toString();
    }

    public int getValue(int row, int column) {
        return grid.getCell(row, column).getValue();
    }

    public int getCellsPerRow() {
        return grid.getSize();
    }

    public int getBlockSize() {
        return grid.getBlockSize();
    }

    public boolean isGiven(int row, int column) {
        return grid.getCell(row, column).isGiven();
    }

    public boolean isHighlighted(int row, int column) {
        return grid.candidates(row, column).get(highlighted);
    }

    public boolean isSet(int row, int column) {
        return grid.getCell(row, column).isSet();
    }

    public boolean isShowCandidates(int row, int column) {
        return grid.getCell(row, column).isShowCandidates();
    }

    public boolean isCandidate(int row, int column, int candidate) {
        return grid.candidates(row, column).get(candidate);
    }

/* Methods */
    public int blockAt(int row, int column) {
    return grid.blockAt(row, column);
}

    public void exit() {
        System.exit(0);
    }

    public void reset() {
        grid.reset();
        statusLine = "Sudoku was reset";
        notifyObservers();
    }

    public void create() {
        grid.create();
        highlighted = 0;
        statusLine = "New Sudoku Puzzle created";
        notifyObservers();
    }

    public void showCandidates(int row, int column) {
        grid.getCell(row, column).toggleShowCandidates();
        BitSet set = grid.candidates(row, column);
        statusLine = "Candidates at (" + row + "," + column + ") are "
                + set.toString();
        notifyObservers();
    }

    public void showAllCandidates() {
        for (int row = 0; row < grid.getSize(); row++) {
            for (int col = 0; col < grid.getSize(); col++) {
                showCandidates(row, col);
            }
        }
        notifyObservers();
    }

    public void highlight(int value) {
        highlighted = value;
        notifyObservers();
    }

}
