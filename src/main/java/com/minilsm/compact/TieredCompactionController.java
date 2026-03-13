package com.minilsm.compact;

import com.minilsm.LsmStorageState;

import java.util.List;

/**
 * Controller for universal/tiered compaction.
 * Equivalent to Rust's TieredCompactionController struct.
 */
public class TieredCompactionController {

    private final TieredCompactionOptions options;

    public TieredCompactionController(TieredCompactionOptions options) {
        this.options = options;
    }

    /** Generates a compaction task based on current state, or null if none needed. */
    public TieredCompactionTask generateCompactionTask(LsmStorageState snapshot) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Applies the result of a compaction, returning the new state and list of SSTs to remove.
     */
    public CompactionResult applyCompactionResult(LsmStorageState snapshot,
                                                   TieredCompactionTask task,
                                                   List<Integer> output) {
        throw new UnsupportedOperationException("TODO");
    }

    public TieredCompactionOptions getOptions() {
        return options;
    }
}
