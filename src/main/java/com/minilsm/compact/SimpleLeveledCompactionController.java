package com.minilsm.compact;

import com.minilsm.LsmStorageState;

import java.util.List;

/**
 * Controller for simplified leveled compaction.
 * Equivalent to Rust's SimpleLeveledCompactionController struct.
 */
public class SimpleLeveledCompactionController {

    private final SimpleLeveledCompactionOptions options;

    public SimpleLeveledCompactionController(SimpleLeveledCompactionOptions options) {
        this.options = options;
    }

    /** Generates a compaction task based on current state, or null if none needed. */
    public SimpleLeveledCompactionTask generateCompactionTask(LsmStorageState snapshot) {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Applies the result of a compaction, returning the new state and list of SSTs to remove.
     */
    public CompactionResult applyCompactionResult(LsmStorageState snapshot,
                                                   SimpleLeveledCompactionTask task,
                                                   List<Integer> output) {
        throw new UnsupportedOperationException("TODO");
    }

    public SimpleLeveledCompactionOptions getOptions() {
        return options;
    }
}
