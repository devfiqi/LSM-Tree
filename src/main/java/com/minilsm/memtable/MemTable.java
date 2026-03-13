package com.minilsm.memtable;

import com.minilsm.Bound;
import com.minilsm.key.KeySlice;
import com.minilsm.table.SsTableBuilder;
import com.minilsm.wal.Wal;

import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory sorted key-value store backed by a ConcurrentSkipListMap.
 * Equivalent to Rust's MemTable struct.
 */
public class MemTable {

    /** Concurrent sorted map for key-value storage. */
    private final ConcurrentSkipListMap<byte[], byte[]> map;

    /** Optional write-ahead log. */
    private final Wal wal;

    /** Memtable identifier. */
    private final int id;

    /** Estimated size in bytes (for flush triggering). */
    private final AtomicLong approximateSize;

    private MemTable(int id, ConcurrentSkipListMap<byte[], byte[]> map, Wal wal) {
        this.id = id;
        this.map = map;
        this.wal = wal;
        this.approximateSize = new AtomicLong(0);
    }

    /** Creates a new empty memtable. */
    public static MemTable create(int id) {
        ConcurrentSkipListMap<byte[], byte[]> map = new ConcurrentSkipListMap<>(
                java.util.Arrays::compareUnsigned);
        return new MemTable(id, map, null);
    }

    /** Creates a new memtable with a WAL. */
    public static MemTable createWithWal(int id, Path path) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Recovers a memtable from a WAL file. */
    public static MemTable recoverFromWal(int id, Path path) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Retrieves the value for a key, or null if not found. */
    public byte[] get(byte[] key) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Inserts a key-value pair. Logs to WAL if present. */
    public void put(byte[] key, byte[] value) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Batch insert of key-value pairs. */
    public void putBatch(java.util.List<java.util.Map.Entry<KeySlice, byte[]>> data) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Creates a range scan iterator. */
    public MemTableIterator scan(Bound lower, Bound upper) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Flushes all entries to an SSTable builder. */
    public void flush(SsTableBuilder builder) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Syncs the WAL to disk. */
    public void syncWal() throws IOException {
        if (wal != null) {
            wal.sync();
        }
    }

    public int id() {
        return id;
    }

    public long approximateSize() {
        return approximateSize.get();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }
}
