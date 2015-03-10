package de.htwg.sudoku.aview.tui;

import de.htwg.sudoku.controller.SudokuController;
import util.observer.Event;
import util.observer.IObserver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TextUI implements IObserver {

    private static final int SMALL_SIZE = 1;
    private static final int MEDIUM_SIZE = 4;
    private static final int LARGE_SIZE = 9;
    private static final String NEWLINE = System.getProperty("line.separator");

    private static final Logger LOGGER = LogManager.getLogger(TextUI.class.getName());

    private SudokuController controller;

    public TextUI(SudokuController controller) {
        this.controller = controller;
        controller.addObserver(this);
    }

    // @Override
    public void update(Event e) {
        printTUI();
    }

    public boolean processInputLine(String line) {
        boolean continu = true;
        switch (line) {
            case "q":
                continu = false;
                break;
            case "r":
                controller.reset();
                break;
            case "n":
                controller.create();
                break;
            case ".":
            case "-":
                controller.setGrid(SMALL_SIZE);
                break;
            case "+":
                controller.setGrid(MEDIUM_SIZE);
                break;
            case "#":
            case "*":
                controller.setGrid(LARGE_SIZE);
                break;
        }
        // if the command line has the form 123, set the cell (1,2) to value 3
        if (line.matches("[0-9][0-9][0-9]")) {
            int[] arg = readToArray(line);
            controller.setValue(arg[0], arg[1], arg[2]);
        } else
            // if the command line has the form 12, get the candidates of cell (1,2)
            if (line.matches("[0-9][0-9]")) {
                int[] arg = readToArray(line);
                controller.showCandidates(arg[0], arg[1]);
            } else
                {
                    LOGGER.entry("Illegal command: " + line);
                }
        return continu;
    }

    private int[] readToArray(String line) {
        Pattern p = Pattern.compile("[0-9]");
        Matcher m = p.matcher(line);
        int[] result = new int[line.length()];
        for (int i = 0; i < result.length; i++) {
            m.find();
            result[i] = Integer.parseInt(m.group());
        }
        return result;
    }

    public void printTUI() {
        LOGGER.entry(NEWLINE + controller.getGridString());
        LOGGER.entry(NEWLINE + StatusMessage.text.get(controller.getStatus()) + controller.getStatusText());
        LOGGER.entry(NEWLINE
                + "Possible commands: q-quit, n-new, r-reset, .,+,#-size, xy-show (x,y), xyz-set (x,y) to z");
    }
}
