package com.minilsm.manifest;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * Append-only manifest log for recording structural changes.
 * Used for recovery after crash.
 * Equivalent to Rust's Manifest struct.
 */
public class Manifest {

    private final java.io.OutputStream file;

    private Manifest(java.io.OutputStream file) {
        this.file = file;
    }

    /** Creates a new manifest file. */
    public static Manifest create(Path path) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Recovers the manifest, returning the manifest and all records. */
    public static ManifestRecovery recover(Path path) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Adds a record to the manifest (requires holding the state lock). */
    public void addRecord(ManifestRecord record) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Adds a record during initialization (no state lock needed). */
    public void addRecordWhenInit(ManifestRecord record) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Recovery result containing the manifest and all recovered records. */
    public record ManifestRecovery(Manifest manifest, List<ManifestRecord> records) {}
}
