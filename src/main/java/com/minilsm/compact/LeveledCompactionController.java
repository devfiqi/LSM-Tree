package com.minilsm.compact;

import com.minilsm.LsmStorageState;

import java.util.List;

/**
 * Controller for RocksDB-style leveled compaction.
 * Equivalent to Rust's LeveledCompactionController struct.
 */
public class LeveledCompactionController {

    private final LeveledCompactionOptions options;

    public LeveledCompactionController(LeveledCompactionOptions options) {
        this.options = options;
    }

    /** Finds SSTables in the given level that overlap with the given SSTable IDs. */
    public List<Integer> findOverlappingSsts(LsmStorageState snapshot, List<Integer> sstIds, int inLevel) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Generates a compaction task based on current state, or null if none needed. */
    public LeveledCompactionTask generateCompactionTask(LsmStorageState snapshot) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Applies the result of a compaction, returning the new state and list of SSTs to remove.
     */
    public CompactionResult applyCompactionResult(LsmStorageState snapshot,
                                                   LeveledCompactionTask task,
                                                   List<Integer> output,
                                                   boolean inRecovery) {
        throw new UnsupportedOperationException("TODO");
    }

    public LeveledCompactionOptions getOptions() {
        return options;
    }
}
