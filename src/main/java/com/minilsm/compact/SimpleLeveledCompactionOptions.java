package com.minilsm.compact;

/**
 * Configuration for simplified leveled compaction.
 * Equivalent to Rust's SimpleLeveledCompactionOptions struct.
 */
public record SimpleLeveledCompactionOptions(
        int sizeRatioPercent,
        int level0FileNumCompactionTrigger,
        int maxLevels
) {}
