package de.htwg.sudoku.controller;

/**
 * Stati for Controller and View
 */
public enum  GameStatus {
    WELCOME,
    ILLEGAL_ARGUMENT,
    CELL_SET_SUCCESS,
    CELL_SET_FAIL,
    CREATE,
    RESET,
    REDO, 
    UNDO,
    COPY,
    PAST,
    SHOW_CANDIDATES,
    SOLVE_SUCCESS,
    SOLVE_FAIL
}
