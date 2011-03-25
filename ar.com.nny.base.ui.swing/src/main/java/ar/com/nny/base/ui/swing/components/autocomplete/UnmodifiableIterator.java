package ar.com.nny.base.ui.swing.components.autocomplete;

import java.util.Iterator;

/** An convenience class to aid in writing iterators that cannot be modified. */
public abstract class UnmodifiableIterator implements Iterator {
    /** Throws UnsupportedOperationException */
    public void remove() {
        throw new UnsupportedOperationException();
    }
}