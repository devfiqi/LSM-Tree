package com.minilsm.iterators;

import java.io.IOException;
import java.util.Arrays;

/**
 * Merges exactly two sorted iterators, preferring iterator A when keys are equal.
 * Equivalent to Rust's TwoMergeIterator struct.
 */
public class TwoMergeIterator implements StorageIterator {

    private final StorageIterator a;
    private final StorageIterator b;

    private TwoMergeIterator(StorageIterator a, StorageIterator b) {
        this.a = a;
        this.b = b;
    }

    /** Creates a TwoMergeIterator from two iterators. Deduplicates keys, preferring A. */
    public static TwoMergeIterator create(StorageIterator a, StorageIterator b) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Returns true if iterator A should be used for the current position. */
    private boolean chooseA() {
        if (!a.isValid()) return false;
        if (!b.isValid()) return true;
        return Arrays.compareUnsigned(a.key(), b.key()) <= 0;
    }

    @Override
    public byte[] key() {
        if (chooseA()) return a.key();
        return b.key();
    }

    @Override
    public byte[] value() {
        if (chooseA()) return a.value();
        return b.value();
    }

    @Override
    public boolean isValid() {
        return a.isValid() || b.isValid();
    }

    @Override
    public void next() throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int numActiveIterators() {
        return a.numActiveIterators() + b.numActiveIterators();
    }
}
