package de.htwg.sudoku.controller.logwrapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import de.htwg.sudoku.controller.GameStatus;
import de.htwg.sudoku.controller.ISudokuController;
import de.htwg.sudoku.model.IGridFactory;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.Observable;
import de.htwg.util.observer.IObserver;

@Singleton
public class SudokuController extends Observable implements ISudokuController {

	private static final Logger LOGGER = LogManager.getLogger(SudokuController.class.getName());
	private ISudokuController realController;
	private long startTime;

	@Inject
	public SudokuController(IGridFactory gridFactory) {
		realController = new de.htwg.sudoku.controller.impl.SudokuController(gridFactory);
	}

	private void pre() {
		LOGGER.debug("Controller method " + getMethodName(1) + " was called.");
		startTime = System.nanoTime();
	}

	private void post() {
		long endTime = System.nanoTime();
		long duration = endTime - startTime;
		LOGGER.debug("Controller method " + getMethodName(1) + " was finished in " + duration + " nanoSeconds.");
	}

	private static String getMethodName(final int depth) {
		final StackTraceElement[] stack = Thread.currentThread().getStackTrace();
		return stack[2 + depth].getMethodName();
	}

	@Override
	public void setValue(int row, int column, int value) {
		pre();
		realController.setValue(row, column, value);
		post();
	}

	@Override
	public void solve() {
		pre();
		realController.solve();
		post();
	}

	@Override
	public void reset() {
		pre();
		realController.reset();
		post();
	}

	@Override
	public void create() {
		pre();
		realController.create();
		post();
	}

	@Override
	public GameStatus getStatus() {
		return realController.getStatus();
	}

	@Override
	public String getGridString() {
		pre();
		String result = realController.getGridString();
		post();
		return result;
	}

	@Override
	public void undo() {
		pre();
		realController.undo();
		post();
	}

	@Override
	public void redo() {
		pre();
		realController.redo();
		post();
	}

	@Override
	public void copy() {
		pre();
		realController.copy();
		post();
	}

	@Override
	public void paste() {
		pre();
		realController.paste();
		post();
	}

	@Override
	public int getValue(int row, int column) {
		return realController.getValue(row, column);
	}

	@Override
	public void showCandidates(int row, int column) {
		pre();
		realController.showCandidates(row, column);
		post();
	}

	@Override
	public void highlight(int value) {
		pre();
		realController.highlight(value);
		post();
	}

	@Override
	public int getSize() {
		return realController.getSize();
	}

	@Override
	public int getBlockSize() {
		return realController.getBlockSize();
	}

	@Override
	public int blockAt(int row, int column) {
		return realController.blockAt(row, column);
	}

	@Override
	public void showAllCandidates() {
		pre();
		realController.showAllCandidates();
		post();
	}

	@Override
	public boolean isGiven(int row, int column) {
		return realController.isGiven(row, column);
	}

	@Override
	public boolean isHighlighted(int row, int column) {
		return realController.isHighlighted(row, column);
	}

	@Override
	public boolean isSet(int row, int column) {
		return realController.isSet(row, column);
	}

	@Override
	public boolean isShowCandidates(int row, int column) {
		return realController.isShowCandidates(row, column);
	}

	@Override
	public boolean isCandidate(int row, int column, int candidate) {
		return realController.isCandidate(row, column, candidate);
	}

	@Override
	public void parseStringToGrid(String gridString) {
		pre();
		realController.parseStringToGrid(gridString);
		post();
	}

	@Override
	public void resetSize(int newSize) {
		pre();
		realController.resetSize(newSize);
		post();
	}

	@Override
	public void addObserver(IObserver s) {
		pre();
		realController.addObserver(s);
		post();
	}

	@Override
	public void removeObserver(IObserver s) {
		pre();
		realController.removeObserver(s);
		post();
	}

	@Override
	public void removeAllObservers() {
		pre();
		realController.removeAllObservers();
		post();
	}

	@Override
	public void notifyObservers() {
		pre();
		realController.notifyObservers();
		post();
	}

	@Override
	public void notifyObservers(Event e) {
		pre();
		realController.notifyObservers(e);
		post();
	}

	@Override
	public String getStatusText() {
		return realController.getStatusText();
	}

	@Override
	public void setGrid(int newSize) {
		pre();
		realController.setGrid(newSize);
		post();

	}

	@Override
	public void refresh() {
		realController.refresh();

	}

	@Override
	public String toJson() {
		return realController.toJson();
	}

}