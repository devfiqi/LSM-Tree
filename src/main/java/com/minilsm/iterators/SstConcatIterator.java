package com.minilsm.iterators;

import com.minilsm.key.KeySlice;
import com.minilsm.table.SsTable;
import com.minilsm.table.SsTableIterator;

import java.io.IOException;
import java.util.List;

/**
 * Concatenates multiple non-overlapping SSTable iterators in key order.
 * Equivalent to Rust's SstConcatIterator struct.
 */
public class SstConcatIterator implements StorageIterator {

    /** Current SSTable iterator (null if exhausted). */
    private SsTableIterator current;

    /** Index of the next SSTable to open. */
    private int nextSstIdx;

    /** All SSTables in sorted order. */
    private final List<SsTable> sstables;

    private SstConcatIterator(SsTableIterator current, int nextSstIdx, List<SsTable> sstables) {
        this.current = current;
        this.nextSstIdx = nextSstIdx;
        this.sstables = sstables;
    }

    /** Creates an iterator positioned at the first entry across all SSTables. */
    public static SstConcatIterator createAndSeekToFirst(List<SsTable> sstables) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Creates an iterator seeking to the first key >= target across all SSTables. */
    public static SstConcatIterator createAndSeekToKey(List<SsTable> sstables, KeySlice key) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public byte[] key() {
        return current.key();
    }

    @Override
    public byte[] value() {
        return current.value();
    }

    @Override
    public boolean isValid() {
        return current != null && current.isValid();
    }

    @Override
    public void next() throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int numActiveIterators() {
        return 1;
    }
}
