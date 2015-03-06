package de.htwg.sudoku.aview.tui;

import de.htwg.sudoku.controller.GameStatus;

import java.util.*;

/**
 * A text that represents the game status to the user
 */
public class StatusMessage {
/* Fields */
    static HashMap<GameStatus,String> text = new HashMap<GameStatus, String>();

/* Constructors */
    public StatusMessage() {
        text.put(GameStatus.WELCOME,"Welcome to HTWG Sudoku");
        text.put(GameStatus.CREATE, "A new Sudoku puzzle was created successfully");
        text.put(GameStatus.CELL_SET_SUCCESS,"The cell was set successfully ");
        text.put(GameStatus.CELL_SET_FAIL,"The cell is already set ");
        text.put(GameStatus.RESET, "The Sudoku puzzle was reset");
        text.put(GameStatus.ILLEGAL_ARGUMENT, "This is not a valid entry");
        text.put(GameStatus.SHOW_CANDIDATES,"Possible values for this cell are: ");
    }

}
