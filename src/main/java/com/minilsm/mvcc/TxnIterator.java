package com.minilsm.mvcc;

import com.minilsm.iterators.FusedIterator;
import com.minilsm.iterators.LsmIterator;
import com.minilsm.iterators.StorageIterator;
import com.minilsm.iterators.TwoMergeIterator;

import java.io.IOException;

/**
 * Iterator that merges a transaction's local writes with the LSM tree.
 * Equivalent to Rust's TxnIterator struct.
 */
public class TxnIterator implements StorageIterator {

    /** Reference to the owning transaction (to keep it alive). */
    private final Transaction txn;

    /** Inner iterator: merge of local writes and LSM iterator. */
    private final TwoMergeIterator inner;

    public TxnIterator(Transaction txn, TwoMergeIterator inner) {
        this.txn = txn;
        this.inner = inner;
    }

    /** Creates a TxnIterator, skipping deleted entries. */
    public static TxnIterator create(Transaction txn, TwoMergeIterator inner) throws IOException {
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
