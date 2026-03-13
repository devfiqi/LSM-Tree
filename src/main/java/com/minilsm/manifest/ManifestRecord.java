package com.minilsm.manifest;

import com.minilsm.compact.CompactionTask;

import java.util.List;

/**
 * A record in the manifest log, tracking structural changes for recovery.
 * Equivalent to Rust's ManifestRecord enum.
 */
public sealed interface ManifestRecord {

    /** A memtable was flushed to an SSTable. */
    record Flush(int sstId) implements ManifestRecord {}

    /** A new memtable was created. */
    record NewMemtable(int memtableId) implements ManifestRecord {}

    /** A compaction was performed. */
    record Compaction(CompactionTask task, List<Integer> outputSstIds) implements ManifestRecord {}
}
