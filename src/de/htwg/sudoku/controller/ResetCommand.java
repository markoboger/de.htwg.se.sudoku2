package de.htwg.sudoku.controller;

import de.htwg.sudoku.model.Grid;
import de.htwg.util.command.UndoableCommand;
import de.htwg.util.memento.Originator;


public class ResetCommand extends Originator implements UndoableCommand {
/* Fields */
    private Grid grid;

/* Constructors */
    public ResetCommand(Grid grid) {
        this.grid = grid;
    }

/* Getter and Setter */
/* Methods */
    public void doCommand() {
        setMemento(grid.toString("."));
        grid.reset();
    }

    public void undoCommand() {
        grid.parseStringToGrid(getMemento());
    }
}
