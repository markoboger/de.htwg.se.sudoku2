package de.htwg.sudoku.controller;

import de.htwg.sudoku.model.Grid;
import de.htwg.util.command.UndoableCommand;
import de.htwg.util.memento.Originator;

public class SolveCommand extends Originator implements UndoableCommand {

/* Fields */
    private Grid grid;
    private boolean result=false;

/* Constructors */
    public SolveCommand(Grid grid) {
        this.grid = grid;
    }

/* Getter and Setter */
    public boolean getResult() {
        return result;
    }
/* Methods */
    public void doCommand() {
    setMemento(grid.toString("."));
    result = grid.solve();
}

    public void undoCommand() {
        grid.parseStringToGrid(getMemento());
    }
}
