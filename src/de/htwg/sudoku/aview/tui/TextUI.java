package de.htwg.sudoku.aview.tui;

import de.htwg.sudoku.controller.SudokuController;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObserver;

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
        if (line.matches(".")) {
            continu = processSingleCharInput(line);
        } else
            // if the command line has the form 12, get the candidates of cell (1,2)
            if (line.matches("[0-9][0-9]")) {
                processDoubleCharInput(line);
            } else
                // if the command line has the form 123, set the cell (1,2) to value 3
                if (line.matches("[0-9][0-9][0-9]")) {
                    processTrippleCharInput(line);
                } else
                    LOGGER.entry("Illegal command: " + line);
        return continu;
    }

    private void processDoubleCharInput(String line) {
        int[] arg = readToArray(line);
        controller.showCandidates(arg[0], arg[1]);
    }

    private void processTrippleCharInput(String line) {
        int[] arg = readToArray(line);
        controller.setValue(arg[0], arg[1], arg[2]);
    }

    private boolean processSingleCharInput(String line) {
        switch (line) {
            case "q":
                return false;
            case "r":
                controller.reset();
                break;
            case "n":
                controller.create();
                break;
            case "s":
                controller.solve();
                break;
            case "u":
                controller.undo();
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
            default:
                LOGGER.entry("Illegal command: " + line);
        }
        return true;
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
                + "Possible commands: q-quit, n-new, r-reset, s-solve, u-undo .,+,#-size, xy-show (x,y), xyz-set (x,y) to z");
    }
}
