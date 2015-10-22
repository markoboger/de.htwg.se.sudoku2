package de.htwg.sudoku.controller;

/**
 * Stati for Controller and View
 */
public enum  GameStatus {
    WELCOME,
    CREATE,
    CELL_SET_SUCCESS,
    CELL_SET_FAIL,
    RESET,
    ILLEGAL_ARGUMENT,
    SHOW_CANDIDATES,
    SOLVE_SUCCESS,
    SOLVE_FAIL,
    REDO, 
    UNDO,
    COPY,
    PASTE
}
