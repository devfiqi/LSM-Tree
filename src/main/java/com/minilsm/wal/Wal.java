package com.minilsm.wal;

import com.minilsm.key.KeySlice;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Write-Ahead Log for crash recovery.
 * Equivalent to Rust's Wal struct.
 */
public class Wal {

    private final BufferedOutputStream file;

    private Wal(BufferedOutputStream file) {
        this.file = file;
    }

    /** Creates a new WAL file at the given path. */
    public static Wal create(Path path) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Recovers entries from a WAL file into the given skiplist. */
    public static Wal recover(Path path, ConcurrentSkipListMap<byte[], byte[]> skiplist) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Logs a single key-value write. */
    public void put(byte[] key, byte[] value) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Logs a batch of key-value writes. */
    public void putBatch(List<Map.Entry<KeySlice, byte[]>> data) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Flushes and syncs the WAL to disk. */
    public void sync() throws IOException {
        throw new UnsupportedOperationException("TODO");
    }
}
