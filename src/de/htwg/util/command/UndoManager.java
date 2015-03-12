package de.htwg.util.command;

import java.util.List;
import java.util.Stack;

/**
 * The UndoManager holds a Stack of Commands.
 * When a Command is added,it executes the doCommand.
 * When undo is called it calls the top of the Stack and calls undoCommand
 */
public class UndoManager {
/* Fields */
    private static Stack<UndoableCommand> undoStack = new Stack<UndoableCommand>();
    private static UndoableCommand topCommand;

/* Methods */
    public static void doCommand(UndoableCommand newCommand) {
        newCommand.doCommand();
        undoStack.push(newCommand);
    }

    public static void undoCommand() {
        if (!undoStack.empty()) {
            topCommand = undoStack.pop();
            topCommand.undoCommand();
        }
    }
}
