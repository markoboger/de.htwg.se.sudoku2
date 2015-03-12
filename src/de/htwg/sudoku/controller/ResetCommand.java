package de.htwg.sudoku.controller;

import de.htwg.sudoku.model.Grid;
import de.htwg.util.command.UndoableCommand;
import de.htwg.util.memento.Memento;


public class ResetCommand implements UndoableCommand {
/* Fields */
    private Memento memento;
    private Grid grid;

/* Constructors */
    public ResetCommand(Grid grid) {
        this.grid = grid;
    }

/* Getter and Setter */
/* Methods */
    public void doCommand() {
        memento = new Memento(grid.toString("."));
        grid.reset();
    }

    public void undoCommand() {
        grid.parseStringToGrid(memento.getState(), ".");
    }
}
