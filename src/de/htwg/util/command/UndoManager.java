package de.htwg.util.command;

import java.util.Deque;
import java.util.LinkedList;

/**
 * The UndoManager holds a Stack of Commands. When a Command is added,it
 * executes the doCommand. When undo is called it calls the top of the Stack and
 * calls undoCommand
 */
public class UndoManager {
	/* Fields */
	private static Deque<UndoableCommand> undoStack = new LinkedList<UndoableCommand>();
	private static Deque<UndoableCommand> redoStack = new LinkedList<UndoableCommand>();

	private static UndoableCommand topCommand;

	/* Methods */
	public void doCommand(UndoableCommand newCommand) {
		newCommand.doCommand();
		undoStack.push(newCommand);
	}

	public void undoCommand() {
		if (!undoStack.isEmpty()) {
			topCommand = undoStack.pop();
			topCommand.undoCommand();
			redoStack.push(topCommand);
		}
	}

	public void redoCommand() {
		if (!redoStack.isEmpty()) {
			topCommand = redoStack.pop();
			topCommand.redoCommand();
		}
	}

	public void reset() {
		undoStack.clear();
	}
}
