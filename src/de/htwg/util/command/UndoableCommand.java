package de.htwg.util.command;

/**
 * The UndoableCommand allows do and undo operations
 */
public interface UndoableCommand {
    public void doCommand();
    public void undoCommand();
    public void redoCommand();
}
