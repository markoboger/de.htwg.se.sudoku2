package de.htwg.sudoku.controller;

import de.htwg.sudoku.model.Grid;
import de.htwg.util.command.UndoableCommand;
import de.htwg.util.memento.Memento;
import de.htwg.util.memento.Originator;

public class CreateCommand extends Originator implements UndoableCommand {

/* Fields */
    Grid grid;

/* Constructors */
    public CreateCommand(Grid grid) {
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
}
