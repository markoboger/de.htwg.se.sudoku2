package de.htwg.util.Memento;

/**
 * An Originator holds a reference to a Memento, can get and set the Memento
 */
public class Originator {
/* Fields */
    private Memento memento;

/* Constructors */
/* Getter and Setter */
    public void setMemento(String state) {
        this.memento = new Memento(state);
    }

    public String getMemento() {
        return memento.getState();
    }

/* Methods */
}
