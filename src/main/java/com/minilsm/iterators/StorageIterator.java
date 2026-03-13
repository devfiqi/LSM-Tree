package com.minilsm.iterators;

import java.io.IOException;

/**
 * Core iterator interface for the LSM tree.
 * Equivalent to Rust's StorageIterator trait.
 *
 * All iterators in the system implement this interface, enabling
 * composition (merge, concat, fuse) of different iterator types.
 */
public interface StorageIterator {

    /** Returns the current key. Only valid when {@link #isValid()} is true. */
    byte[] key();

    /** Returns the current value. Only valid when {@link #isValid()} is true. */
    byte[] value();

    /** Returns true if the iterator is positioned at a valid entry. */
    boolean isValid();

    /** Advances the iterator to the next entry. */
    void next() throws IOException;

    /** Returns the number of underlying active iterators. Default is 1. */
    default int numActiveIterators() {
        return 1;
    }
}
