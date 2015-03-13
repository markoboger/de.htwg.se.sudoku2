package de.htwg.util.memento;

/**
 * An Originator holds a reference to a memento, can get and set the memento
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
