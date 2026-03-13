package com.minilsm.mvcc;

import com.minilsm.LsmStorageInner;

import java.util.TreeMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * MVCC (Multi-Version Concurrency Control) coordinator.
 * Equivalent to Rust's LsmMvccInner struct.
 */
public class LsmMvccInner {

    /** Lock for serializing write operations. */
    private final ReentrantLock writeLock = new ReentrantLock();

    /** Lock for serializing commit operations. */
    private final ReentrantLock commitLock = new ReentrantLock();

    /** Current timestamp and watermark tracking. */
    private long ts;
    private final Watermark watermark;
    private final ReentrantLock tsLock = new ReentrantLock();

    /** Committed transaction data, keyed by commit timestamp. */
    private final TreeMap<Long, CommittedTxnData> committedTxns = new TreeMap<>();
    private final ReentrantLock committedTxnsLock = new ReentrantLock();

    public LsmMvccInner(long initialTs) {
        this.ts = initialTs;
        this.watermark = new Watermark();
    }

    /** Returns the latest commit timestamp. */
    public long latestCommitTs() {
        tsLock.lock();
        try {
            return ts;
        } finally {
            tsLock.unlock();
        }
    }

    /** Updates the commit timestamp. */
    public void updateCommitTs(long newTs) {
        tsLock.lock();
        try {
            this.ts = newTs;
        } finally {
            tsLock.unlock();
        }
    }

    /** Returns the current watermark (minimum active reader timestamp). */
    public long watermark() {
        tsLock.lock();
        try {
            Long w = watermark.watermark();
            return w != null ? w : ts;
        } finally {
            tsLock.unlock();
        }
    }

    /** Creates a new transaction. */
    public Transaction newTxn(LsmStorageInner inner, boolean serializable) {
        throw new UnsupportedOperationException("TODO");
    }

    public ReentrantLock getWriteLock() { return writeLock; }
    public ReentrantLock getCommitLock() { return commitLock; }
    public Watermark getWatermark() { return watermark; }
}
