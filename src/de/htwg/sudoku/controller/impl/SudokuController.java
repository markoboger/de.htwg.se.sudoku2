package de.htwg.sudoku.controller.impl;

import de.htwg.sudoku.controller.GameStatus;
import de.htwg.sudoku.controller.ISudokuController;
import de.htwg.sudoku.controller.SizeChangedEvent;
import de.htwg.sudoku.model.ICell;
import de.htwg.sudoku.model.IGrid;
import de.htwg.sudoku.model.IGridFactory;
import de.htwg.util.command.UndoManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Inject;

import de.htwg.util.observer.Observable;

import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.BitSet;

/**
 * Main controller of Sudoku
 */
public class SudokuController extends Observable implements ISudokuController {
	/* Fields */
	private static final Logger LOGGER = LogManager.getLogger(SudokuController.class.getName());
	
	private UndoManager undoManager = new UndoManager();

	private GameStatus status = GameStatus.WELCOME;
	private String statusText = "";
	private IGrid grid;
	private IGridFactory gridFactory;
	private int highlighted;
	private static final int NORMALGRID = 9;
	private boolean showCandidates = false;

	/* Constructors */
	@Inject
	public SudokuController(IGridFactory gridFactory) {
		this.gridFactory = gridFactory;
		this.grid = gridFactory.create(NORMALGRID);
	}

	/* Getter and Setter */
	@Override
	public void setGrid(int size) {
		try {
			this.grid = gridFactory.create(size);
			undoManager.reset();
		} catch (IllegalArgumentException e) {
			LOGGER.info("Setting Grid to wrong size", e);
			status = GameStatus.ILLEGAL_ARGUMENT;
			statusText = e.getMessage();
		}
		notifyObservers();
	}

	@Override
	public int getSize() {
		return grid.getSize();
	}

	@Override
	public void setValue(int row, int column, int value) {
		ICell cell = grid.getCell(row, column);
		if (cell.isUnSet()) {
			undoManager.doCommand(new SetValueCommand(cell, value));
			status = GameStatus.CELL_SET_SUCCESS;
			statusText = cell.mkString();
		} else {
			status = GameStatus.CELL_SET_FAIL;
			statusText = cell.mkString();
		}
		notifyObservers();
	}

	@Override
	public GameStatus getStatus() {
		return status;
	}

	@Override
	public int getBlockSize() {
		return grid.getBlockSize();
	}

	@Override
	public String getStatusText() {
		return statusText;
	}

	@Override
	public String getGridString() {
		return grid.toString();
	}

	@Override
	public int getValue(int row, int column) {
		return grid.getCell(row, column).getValue();
	}

	@Override
	public boolean isHighlighted(int row, int column) {
		return grid.candidates(row, column).get(highlighted);
	}

	@Override
	public boolean isGiven(int row, int column) {
		return grid.getCell(row, column).isGiven();
	}

	@Override
	public boolean isShowCandidates(int row, int column) {
		return grid.getCell(row, column).isShowCandidates();
	}

	@Override
	public boolean isCandidate(int row, int column, int candidate) {
		return grid.candidates(row, column).get(candidate);
	}

	@Override
	public boolean isSet(int row, int column) {
		return grid.getCell(row, column).isSet();
	}

	@Override
	public int blockAt(int row, int column) {
		return grid.blockAt(row, column);
	}


	@Override
	public void refresh() {
		notifyObservers();
	}

	@Override
	public void undo() {
		undoManager.undoCommand();
		notifyObservers();
	}

	@Override
	public void redo() {
		undoManager.redoCommand();
		status = GameStatus.REDO;
		notifyObservers();
	}

	@Override
	public void reset() {
		undoManager.doCommand(new ResetCommand(grid));
		status = GameStatus.RESET;
		statusText = "";
		notifyObservers();
	}

	@Override
	public void create() {
		undoManager.doCommand(new CreateCommand(grid));
		grid.create();
		status = GameStatus.CREATE;
		statusText = "";
		notifyObservers();
	}

	@Override
	public void resetSize(int newSize) {
		this.grid = gridFactory.create(newSize);
		reset();
		SizeChangedEvent event = new SizeChangedEvent();
		notifyObservers(event);
	}

	@Override
	public void showCandidates(int row, int column) {
		ICell cell = grid.getCell(row, column);
		cell.toggleShowCandidates();
		BitSet set = grid.candidates(row, column);
		status = GameStatus.SHOW_CANDIDATES;
		statusText = cell.mkString() + " : " + set.toString();
		notifyObservers();
	}

	@Override
	public void showAllCandidates() {
		showCandidates = !showCandidates;
		for (int row = 0; row < grid.getSize(); row++) {
			for (int col = 0; col < grid.getSize(); col++) {
				grid.getCell(row, col).setShowCandidates(showCandidates);
			}
		}
		notifyObservers();
	}

	@Override
	public void highlight(int value) {
		highlighted = value;
		notifyObservers();
	}

	@Override
	public void solve() {
		boolean result;
		SolveCommand command = new SolveCommand(grid);
		undoManager.doCommand(command);

		result = command.getResult();
		if (result) {
			status = GameStatus.SOLVE_SUCCESS;
		} else {
			status = GameStatus.SOLVE_FAIL;
			statusText = "tried in " + grid.getSteps() + " steps";
		}
		notifyObservers();
	}

	@Override
	public void copy() {
		StringSelection gridString = new StringSelection(grid.toString("0"));
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(gridString, null);
		status = GameStatus.COPY;
		notifyObservers();
	}

	@Override
	public void paste() {
		Transferable transferable = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
		if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			String input;
			try {
				input = (String) transferable.getTransferData(DataFlavor.stringFlavor);
				grid.parseStringToGrid(input);
			} catch (UnsupportedFlavorException e1) {
			    LOGGER.info(e1);
				statusText = "Could not read from Clipboard";
			} catch (IOException e1) {
			    LOGGER.info(e1);
				statusText = "Could not read from Clipboard";
			}
		}
		status = GameStatus.PASTE;
		notifyObservers();
	}

	@Override
	public void parseStringToGrid(String gridString) {
		grid.parseStringToGrid(gridString);
		notifyObservers();

	}
	
	@Override
	public String toJson() {
		return grid.toJson();
	}

}
