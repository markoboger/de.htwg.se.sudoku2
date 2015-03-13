package de.htwg.util.memento;

/**
 * A memento stores the state of some Object in a String,
 * so that its state can be restored from it in an undo operation
 */
public class Memento {
/* Fields */
    private String state;

/* Constructors */
    public Memento(String state) {
        this.state = state;
    }

/* Getter and Setter */
    public String getState() {
        return state;
    }

/* Methods */
}
