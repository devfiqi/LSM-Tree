package com.minilsm.mvcc;

import java.util.TreeMap;

/**
 * Tracks active reader timestamps to determine the safe garbage collection point.
 * Equivalent to Rust's Watermark struct.
 */
public class Watermark {

    /** Maps timestamp to reference count of active readers at that timestamp. */
    private final TreeMap<Long, Integer> readers;

    public Watermark() {
        this.readers = new TreeMap<>();
    }

    /** Registers a new reader at the given timestamp. */
    public void addReader(long ts) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Unregisters a reader at the given timestamp. */
    public void removeReader(long ts) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Returns the number of retained snapshots (distinct timestamps with active readers). */
    public int numRetainedSnapshots() {
        return readers.size();
    }

    /**
     * Returns the minimum timestamp of all active readers (the watermark),
     * or null if there are no active readers.
     */
    public Long watermark() {
        if (readers.isEmpty()) return null;
        return readers.firstKey();
    }
}
