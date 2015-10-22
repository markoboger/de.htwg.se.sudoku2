package de.htwg.sudoku.model.impl;

import de.htwg.sudoku.model.IGrid;
import de.htwg.sudoku.model.IGridFactory;

public class GridFactory implements IGridFactory {
	
	static GridFactory instance = new GridFactory();
	
	private GridFactory() {
		
	}

    @Override
    public IGrid create(int size) {
        return new Grid(size);
    }
    
    public static GridFactory getInstance() {
    	return instance;
    }

}
