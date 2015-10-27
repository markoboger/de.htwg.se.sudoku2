package de.htwg.sudoku.aview;

import de.htwg.sudoku.controller.GameStatus;

import java.util.*;

/**
 * A text that represents the game status to the user
 */
public class StatusMessage {
/* Fields */
    public static final Map<GameStatus,String> text = new HashMap<GameStatus, String>();

/* Constructors */
    private StatusMessage() {

    }

    static {
        text.put(GameStatus.WELCOME,"Welcome to HTWG Sudoku");
        text.put(GameStatus.CREATE, "A new Sudoku puzzle was created successfully");
        text.put(GameStatus.CELL_SET_SUCCESS,"The cell was set successfully ");
        text.put(GameStatus.CELL_SET_FAIL,"The cell is already set ");
        text.put(GameStatus.RESET, "The Sudoku puzzle was reset");
        text.put(GameStatus.ILLEGAL_ARGUMENT, "This is not a valid entry");
        text.put(GameStatus.SHOW_CANDIDATES,"Possible values for this cell are: ");
        text.put(GameStatus.SOLVE_SUCCESS, "The Sudoku was solved successfully");
        text.put(GameStatus.SOLVE_FAIL, "Can not solve this Sudoku: ");
        text.put(GameStatus.REDO, "Redid last change");
        text.put(GameStatus.UNDO, "Undid last change");
        text.put(GameStatus.COPY, "Copied the Sudoku to the clipboard");
        text.put(GameStatus.PASTE, "Pasted the Sudoku from the clipboard");
    }

}
