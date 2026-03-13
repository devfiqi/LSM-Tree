package com.minilsm.table;

import com.minilsm.block.BlockIterator;
import com.minilsm.iterators.StorageIterator;
import com.minilsm.key.KeySlice;

import java.io.IOException;

/**
 * Iterates over all entries in an SSTable by traversing blocks sequentially.
 * Equivalent to Rust's SsTableIterator struct.
 */
public class SsTableIterator implements StorageIterator {

    /** The SSTable being iterated. */
    private final SsTable table;

    /** Current block iterator. */
    private BlockIterator blockIter;

    /** Current block index within the SSTable. */
    private int blockIdx;

    private SsTableIterator(SsTable table, BlockIterator blockIter, int blockIdx) {
        this.table = table;
        this.blockIter = blockIter;
        this.blockIdx = blockIdx;
    }

    /** Creates an iterator positioned at the first entry of the SSTable. */
    public static SsTableIterator createAndSeekToFirst(SsTable table) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Seeks the iterator to the first entry. */
    public void seekToFirst() throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Creates an iterator seeking to the first key >= target. */
    public static SsTableIterator createAndSeekToKey(SsTable table, KeySlice key) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Seeks to the first key >= target. */
    public void seekToKey(KeySlice key) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public byte[] key() {
        return blockIter.key().raw();
    }

    @Override
    public byte[] value() {
        return blockIter.value();
    }

    @Override
    public boolean isValid() {
        return blockIter.isValid();
    }

    @Override
    public void next() throws IOException {
        throw new UnsupportedOperationException("TODO");
    }
}
