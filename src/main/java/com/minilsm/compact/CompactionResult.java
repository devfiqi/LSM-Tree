package com.minilsm.compact;

import com.minilsm.LsmStorageState;

import java.util.List;

/**
 * Result of applying a compaction — the new state and SSTs to delete.
 */
public record CompactionResult(
        LsmStorageState newState,
        List<Integer> sstsToRemove
) {}
