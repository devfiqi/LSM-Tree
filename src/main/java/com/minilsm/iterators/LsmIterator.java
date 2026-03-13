package com.minilsm.iterators;

import java.io.IOException;

/**
 * Top-level iterator over the entire LSM tree.
 * Equivalent to Rust's LsmIterator struct.
 *
 * Wraps an inner iterator (typically a MergeIterator over MemTableIterators
 * merged with SSTable iterators) and provides the final read path.
 */
public class LsmIterator implements StorageIterator {

    /** The inner merged iterator. */
    private final StorageIterator inner;

    private LsmIterator(StorageIterator inner) {
        this.inner = inner;
    }

    /** Creates a new LsmIterator, skipping deleted entries (empty values). */
    public static LsmIterator create(StorageIterator inner) throws IOException {
        throw new UnsupportedOperationException("TODO");
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
        return inner.isValid();
    }

    @Override
    public void next() throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int numActiveIterators() {
        return inner.numActiveIterators();
    }
}
