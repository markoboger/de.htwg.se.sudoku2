package de.htwg.sudoku.controller.impl;

import de.htwg.sudoku.model.IGrid;
import de.htwg.util.command.UndoableCommand;
import de.htwg.util.memento.Originator;


public class ResetCommand extends Originator implements UndoableCommand {
/* Fields */
    private IGrid grid;

/* Constructors */
    public ResetCommand(IGrid grid) {
        this.grid = grid;
    }

/* Getter and Setter */
/* Methods */
    @Override
    public void doCommand() {
        setMemento(grid.toString("."));
        grid.reset();
    }

    @Override
    public void undoCommand() {
        grid.parseStringToGrid(getMemento());
    }
    
    @Override
    public void redoCommand() {
        setMemento(grid.toString("."));
        grid.reset();
    }
}
