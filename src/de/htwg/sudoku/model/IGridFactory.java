package de.htwg.sudoku.model;

public interface IGridFactory {

    IGrid create(int size);

	static IGridFactory getInstance() {
		return null;
	}
    
}
