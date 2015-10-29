package de.htwg.sudoku.controller.impl;

import de.htwg.sudoku.model.ICell;
import de.htwg.util.command.UndoableCommand;

public class SetValueCommand implements UndoableCommand {

    private ICell cell;
    private int value;

    public SetValueCommand(ICell cell, int value) {
        this.cell = cell;
        this.value = value;
    }

    @Override
    public void doCommand() {
        cell.setValue(value);
    }

    @Override
    public void undoCommand() {
        cell.setValue(0);
    }
    
    @Override
    public void redoCommand() {
        cell.setValue(value);
    }

}
