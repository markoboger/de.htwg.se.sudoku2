package de.htwg.sudoku.controller.impl;

import de.htwg.sudoku.model.IGrid;
import de.htwg.util.command.UndoableCommand;
import de.htwg.util.memento.Originator;

public class CreateCommand extends Originator implements UndoableCommand {

/* Fields */
    IGrid grid;

/* Constructors */
    public CreateCommand(IGrid grid) {
        this.grid = grid;
    }

/* Getter and Setter */
/* Methods */
    public void doCommand() {
        setMemento(grid.toString("."));
        grid.create();
    }

    public void undoCommand() {
        grid.parseStringToGrid(getMemento());
    }
    
    public void redoCommand() {
        setMemento(grid.toString("."));
        grid.create();
    }
}
