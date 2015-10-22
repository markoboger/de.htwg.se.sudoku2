package de.htwg.sudoku.controller.impl;

import de.htwg.sudoku.model.IGrid;
import de.htwg.util.command.UndoableCommand;
import de.htwg.util.memento.Originator;

public class SolveCommand extends Originator implements UndoableCommand {

/* Fields */
    private IGrid grid;
    private boolean result=false;

/* Constructors */
    public SolveCommand(IGrid grid) {
        this.grid = grid;
    }

/* Getter and Setter */
    public boolean getResult() {
        return result;
    }

/* Methods */
    public void doCommand() {
        result = false;
        setMemento(grid.toString("."));
        result = grid.solve();
    }

    public void undoCommand() {
        grid.parseStringToGrid(getMemento());
    }
    
    public void redoCommand() {
        result = false;
        setMemento(grid.toString("."));
        result = grid.solve();
    }
}
