package com.minilsm.compact;

/**
 * Configuration for RocksDB-style leveled compaction.
 * Equivalent to Rust's LeveledCompactionOptions struct.
 */
public record LeveledCompactionOptions(
        int levelSizeMultiplier,
        int level0FileNumCompactionTrigger,
        int maxLevels,
        int baseLevelSizeMb
) {}
