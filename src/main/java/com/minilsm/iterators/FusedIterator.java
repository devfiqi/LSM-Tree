package com.minilsm.iterators;

import java.io.IOException;

/**
 * Error-safe wrapper around a StorageIterator.
 * Equivalent to Rust's FusedIterator struct.
 *
 * Once next() returns an error or the iterator becomes invalid,
 * all subsequent calls to next() are no-ops and isValid() returns false.
 */
public class FusedIterator implements StorageIterator {

    private final StorageIterator inner;
    private boolean hasErrored;

    public FusedIterator(StorageIterator inner) {
        this.inner = inner;
        this.hasErrored = false;
    }

    @Override
    public byte[] key() {
        return inner.key();
    }

    @Override
    public byte[] value() {
        return inner.value();
    }

    @Override
    public boolean isValid() {
        return !hasErrored && inner.isValid();
    }

    @Override
    public void next() throws IOException {
        if (hasErrored) {
            throw new IOException("Iterator has previously errored");
        }
        if (!inner.isValid()) {
            hasErrored = true;
            return;
        }
        try {
            inner.next();
        } catch (IOException e) {
            hasErrored = true;
            throw e;
        }
    }

    @Override
    public int numActiveIterators() {
        return inner.numActiveIterators();
    }
}
