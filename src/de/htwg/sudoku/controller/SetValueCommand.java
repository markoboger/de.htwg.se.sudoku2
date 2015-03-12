package de.htwg.sudoku.controller;

import de.htwg.sudoku.model.Cell;
import de.htwg.util.command.UndoableCommand;

public class SetValueCommand implements UndoableCommand {

    private Cell cell;
    private int value;

    public SetValueCommand(Cell cell, int value) {
        this.cell = cell;
        this.value = value;
    }

    public void doCommand() {
        cell.setValue(value);
    }

    public void undoCommand() {
        cell.setValue(0);
    }

}
