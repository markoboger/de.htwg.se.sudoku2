package de.htwg.sudoku.aview.gui;

import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JToggleButton;

import de.htwg.sudoku.controller.ISudokuController;

public class HighlightButton extends JToggleButton {

	private static final long serialVersionUID = -4549749110922325962L;
	private String label;

    public HighlightButton(String label, final ISudokuController controller,
            final int value) {
        super(label);
        this.label = label;
        this.addMouseListener(new MouseAdapter() {
        	@Override
            public void mousePressed(MouseEvent arg0) {
                controller.highlight(value);
            }
        });
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public Insets getInsets() {
        return new Insets(0, Constances.HORIZONTAL_INSET_SIZE, 0,
                Constances.HORIZONTAL_INSET_SIZE);
    }
}
