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
    private GameStatus status = GameStatus.WELCOME;
    private String statusText = "";
    private int highlighted = 0;
    private Grid grid;

/* Constructors */
    public SudokuController(int size) {
        setGrid(size);
    }

/* Getter and Setter */
    public void setGrid(int size) {
        try {
            this.grid = new Grid(size);
        } catch (IllegalArgumentException e){
            status = GameStatus.ILLEGAL_ARGUMENT;
            statusText= e.getMessage();
        }
        notifyObservers();
    }

    public int getSize() {
        return grid.getSize();
    }

    public void setValue(int row, int column, int value) {
        Cell cell = grid.getCell(row, column);
        if (cell.isUnSet()) {
            cell.setValue(value);
            status = GameStatus.CELL_SET_SUCCESS;
            statusText = cell.mkString();
        } else {
            status = GameStatus.CELL_SET_FAIL;
            statusText = cell.mkString();
        }
        notifyObservers();
    }

    public GameStatus getStatus() {
        return status;
    }

    public String getStatusText() {
        return statusText;
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
        status = GameStatus.RESET;
        statusText="";
        notifyObservers();
    }

    public void create() {
        grid.create();
        highlighted = 0;
        status = GameStatus.CREATE;
        statusText="";
        notifyObservers();
    }

    public void showCandidates(int row, int column) {
        Cell cell = grid.getCell(row, column);
        cell.toggleShowCandidates();
        BitSet set = grid.candidates(row, column);
        status = GameStatus.SHOW_CANDIDATES;
        statusText = cell.mkString() +" : "+ set.toString();
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
