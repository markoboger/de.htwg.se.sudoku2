package de.htwg.sudoku.aview.gui;

import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import de.htwg.sudoku.aview.StatusMessage;
import de.htwg.sudoku.controller.GameStatus;

public class StatusPanel extends JPanel {

	private static final long serialVersionUID = -2136188660234901904L;
	private final JLabel statusLabel = new JLabel("");

    public StatusPanel() {
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        add(statusLabel);
    }

    public final void setText(final GameStatus status) {
        statusLabel.setText(" " + StatusMessage.text.get(status));
    }

    public void clear() {
        statusLabel.setText(" ");
    }
}
