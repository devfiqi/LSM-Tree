package com.minilsm;

import com.minilsm.iterators.FusedIterator;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Public API for the Mini-LSM storage engine.
 * Equivalent to Rust's MiniLsm struct.
 *
 * Manages background flush and compaction threads.
 */
public class MiniLsm implements AutoCloseable {

    /** The core storage engine. */
    private final LsmStorageInner inner;

    /** Notification queue for the flush thread. */
    private final LinkedBlockingQueue<Object> flushNotifier;

    /** Notification queue for the compaction thread. */
    private final LinkedBlockingQueue<Object> compactionNotifier;

    /** Background flush thread. */
    private Thread flushThread;

    /** Background compaction thread. */
    private Thread compactionThread;

    private MiniLsm(LsmStorageInner inner) {
        this.inner = inner;
        this.flushNotifier = new LinkedBlockingQueue<>();
        this.compactionNotifier = new LinkedBlockingQueue<>();
    }

    /** Opens or creates the database at the given path. */
    public static MiniLsm open(Path path, LsmStorageOptions options) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Retrieves the value for a key, or null if not found. */
    public byte[] get(byte[] key) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Inserts a key-value pair. */
    public void put(byte[] key, byte[] value) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Deletes a key. */
    public void delete(byte[] key) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Performs a batch write of puts and deletes. */
    public void writeBatch(List<WriteBatchRecord> batch) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Range scan. */
    public FusedIterator scan(Bound lower, Bound upper) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Syncs all data to disk. */
    public void sync() throws IOException {
        inner.sync();
    }

    /** Forces a memtable flush. */
    public void forceFlush() throws IOException {
        inner.forceFlushNextImmMemtable();
    }

    /** Forces a full compaction. */
    public void forceFullCompaction() throws IOException {
        inner.forceFullCompaction();
    }

    /** Returns the inner storage engine (for advanced usage). */
    public LsmStorageInner getInner() {
        return inner;
    }

    @Override
    public void close() throws IOException {
        // Signal background threads to stop
        compactionNotifier.offer(new Object());
        flushNotifier.offer(new Object());

        if (compactionThread != null) {
            try { compactionThread.join(); } catch (InterruptedException ignored) {}
        }
        if (flushThread != null) {
            try { flushThread.join(); } catch (InterruptedException ignored) {}
        }
    }
}
