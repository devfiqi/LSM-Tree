package com.minilsm.compact;

/**
 * Compaction strategy configuration.
 * Equivalent to Rust's CompactionOptions enum.
 */
public sealed interface CompactionOptions {

    record Leveled(LeveledCompactionOptions options) implements CompactionOptions {}
    record Simple(SimpleLeveledCompactionOptions options) implements CompactionOptions {}
    record Tiered(TieredCompactionOptions options) implements CompactionOptions {}
    record NoCompaction() implements CompactionOptions {}
}
