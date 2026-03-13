package com.minilsm.iterators;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Merges multiple sorted StorageIterators using a priority queue (min-heap).
 * Equivalent to Rust's MergeIterator struct.
 *
 * When keys are equal, prefers the iterator with the smaller index
 * (i.e., the more recent data source).
 */
public class MergeIterator implements StorageIterator {

    /**
     * Wrapper that pairs an iterator with its index for tie-breaking.
     */
    private record HeapEntry(int index, StorageIterator iter) implements Comparable<HeapEntry> {
        @Override
        public int compareTo(HeapEntry other) {
            int cmp = Arrays.compareUnsigned(this.iter.key(), other.iter.key());
            if (cmp != 0) return cmp;
            return Integer.compare(this.index, other.index);
        }
    }

    private final PriorityQueue<HeapEntry> heap;
    private HeapEntry current;

    private MergeIterator(PriorityQueue<HeapEntry> heap, HeapEntry current) {
        this.heap = heap;
        this.current = current;
    }

    /** Creates a MergeIterator from a list of iterators. */
    public static MergeIterator create(List<StorageIterator> iters) {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public byte[] key() {
        return current.iter().key();
    }

    @Override
    public byte[] value() {
        return current.iter().value();
    }

    @Override
    public boolean isValid() {
        return current != null && current.iter().isValid();
    }

    @Override
    public void next() throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    @Override
    public int numActiveIterators() {
        int count = 0;
        if (current != null) count += current.iter().numActiveIterators();
        for (HeapEntry entry : heap) {
            count += entry.iter().numActiveIterators();
        }
        return count;
    }
}
