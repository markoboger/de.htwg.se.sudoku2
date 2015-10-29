package de.htwg.sudoku.model;

public interface ICell {

	/* Getter and Setter */

	/**
	 * Set the value of a cell.
	 * 
	 * @param value
	 */
	void setValue(int value);

	/**
	 * @return the value of the cell.
	 */
	int getValue();

	/**
	 * A given cell is part of the initial puzzle. Given cells can not be set to
	 * a different value and should have a somewhat highlighted representation
	 * in the UI.
	 * 
	 * @return
	 */
	boolean isGiven();

	/**
	 * A set cell already contains a value different from 0.
	 * 
	 * @return
	 */
	boolean isSet();

	/**
	 * An unset cell contains a value 0.
	 * 
	 * @return
	 */
	boolean isUnSet();

	/**
	 * Set the value showCandidates to b.
	 * 
	 * @param b
	 */
	void setShowCandidates(boolean b);

	/**
	 * @return the value of showCandidates.
	 */
	boolean isShowCandidates();

	/* Methods */
	/**
	 * @return a rich textual representation. This is intended for status line
	 *         of debug, not for the grid.
	 */
	String mkString();

	/**
	 * invert the value of showCandidates.
	 */
	void toggleShowCandidates();

}
