package de.htwg.sudoku.controller;

import de.htwg.sudoku.model.Cell;
import de.htwg.sudoku.model.Grid;
import de.htwg.util.command.UndoManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import de.htwg.util.observer.Observable;

import java.util.BitSet;

/**
 * Main controller of Sudoku
 */
public class SudokuController extends Observable {
/* Fields */
    private static final Logger LOGGER = LogManager.getLogger(SudokuController.class.getName());

    private GameStatus status = GameStatus.WELCOME;
    private String statusText = "";
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
            LOGGER.info("Setting Grid to wrong size",e);
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
            UndoManager.doCommand(new SetValueCommand(cell,value));
            status = GameStatus.CELL_SET_SUCCESS;
            statusText = cell.mkString();
        } else {
            status = GameStatus.CELL_SET_FAIL;
            statusText = cell.mkString();
        }
        notifyObservers();
    }

    public void undo() {
        UndoManager.undoCommand();
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

/* Methods */
    public void reset() {
        grid.reset();
        status = GameStatus.RESET;
        statusText="";
        notifyObservers();
    }

    public void create() {
        grid.create();
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

    public void solve() {
        boolean result;
        result = grid.solve();
        if (result) {
            status = GameStatus.SOLVE_SUCCESS;
        } else {
            status = GameStatus.SOLVE_FAIL;
            statusText = "tried in " + grid.getSteps() + " steps";
        }
        notifyObservers();
    }
}
