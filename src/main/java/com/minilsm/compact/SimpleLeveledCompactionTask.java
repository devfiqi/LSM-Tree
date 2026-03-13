package com.minilsm.compact;

import java.util.List;

/**
 * A simple leveled compaction task.
 * Equivalent to Rust's SimpleLeveledCompactionTask struct.
 *
 * @param upperLevel null means L0
 */
public record SimpleLeveledCompactionTask(
        Integer upperLevel,
        List<Integer> upperLevelSstIds,
        int lowerLevel,
        List<Integer> lowerLevelSstIds,
        boolean isLowerLevelBottomLevel
) {}
