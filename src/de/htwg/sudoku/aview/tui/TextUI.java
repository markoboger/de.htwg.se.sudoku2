package de.htwg.sudoku.aview.tui;

import de.htwg.sudoku.controller.SudokuController;
import util.observer.Event;
import util.observer.IObserver;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class TextUI implements IObserver {

    private static final int DOTSIZE = 1;
    private static final int PLUSSIZE = 4;
    private static final int HASHSIZE = 9;
    private static final String NEWLINE = System.getProperty("line.separator");

    private static final Logger logger = LogManager.getLogger(TextUI.class.getName());

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
        if (line.startsWith("q")) {
            continu = false;
        } else if ( line.startsWith("r")) {
                controller.reset();

        } else if ( line.startsWith("n")) {
            controller.create();
        } else if ( line.startsWith(".")) {
            controller.setGrid(DOTSIZE);
        } else if ( line.startsWith("+")) {
            controller.setGrid(PLUSSIZE);
        } else if ( line.startsWith("#")) {
            controller.setGrid(HASHSIZE);
        } else
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
                        logger.entry("Illegal command");
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
        logger.entry(NEWLINE + controller.getGridString());
        logger.entry(NEWLINE + StatusMessage.text.get(controller.getStatus()) + controller.getStatusText());
        logger.entry(NEWLINE
                + "Possible commands: q-quit, n-new, r-reset, .,+,#-size, xy-show (x,y), xyz-set (x,y) to z");
    }
}
