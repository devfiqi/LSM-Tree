package com.minilsm.compact;

import java.util.List;

/**
 * A leveled compaction task.
 * Equivalent to Rust's LeveledCompactionTask struct.
 *
 * @param upperLevel null means L0
 */
public record LeveledCompactionTask(
        Integer upperLevel,
        List<Integer> upperLevelSstIds,
        int lowerLevel,
        List<Integer> lowerLevelSstIds,
        boolean isLowerLevelBottomLevel
) {}
