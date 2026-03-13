package com.minilsm.mvcc;

import com.minilsm.Bound;
import com.minilsm.LsmStorageInner;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A transaction with snapshot isolation.
 * Equivalent to Rust's Transaction struct.
 */
public class Transaction {

    /** The read timestamp for this transaction's snapshot. */
    private final long readTs;

    /** Reference to the storage engine. */
    private final LsmStorageInner inner;

    /** Local write buffer (not visible to other transactions until commit). */
    private final ConcurrentSkipListMap<byte[], byte[]> localStorage;

    /** Whether this transaction has been committed. */
    private final AtomicBoolean committed = new AtomicBoolean(false);

    /**
     * Key hashes for serializable validation.
     * First set = read key hashes, second set = write key hashes.
     * Null if not in serializable mode.
     */
    private final ReentrantLock keyHashesLock;
    private final Set<Integer> readKeyHashes;
    private final Set<Integer> writeKeyHashes;

    public Transaction(long readTs, LsmStorageInner inner, boolean serializable) {
        this.readTs = readTs;
        this.inner = inner;
        this.localStorage = new ConcurrentSkipListMap<>(java.util.Arrays::compareUnsigned);

        if (serializable) {
            this.keyHashesLock = new ReentrantLock();
            this.readKeyHashes = new HashSet<>();
            this.writeKeyHashes = new HashSet<>();
        } else {
            this.keyHashesLock = null;
            this.readKeyHashes = null;
            this.writeKeyHashes = null;
        }
    }

    /** Retrieves a value by key (checks local storage first, then LSM). */
    public byte[] get(byte[] key) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Range scan over both local storage and the LSM tree. */
    public TxnIterator scan(Bound lower, Bound upper) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Writes a key-value pair to local storage. */
    public void put(byte[] key, byte[] value) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Deletes a key (writes empty value tombstone to local storage). */
    public void delete(byte[] key) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Commits the transaction, flushing local writes to the LSM tree. */
    public void commit() throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    public long getReadTs() { return readTs; }
    public boolean isCommitted() { return committed.get(); }
}
