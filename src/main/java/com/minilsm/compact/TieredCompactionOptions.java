package com.minilsm.compact;

/**
 * Configuration for universal/tiered compaction.
 * Equivalent to Rust's TieredCompactionOptions struct.
 */
public record TieredCompactionOptions(
        int numTiers,
        int maxSizeAmplificationPercent,
        int sizeRatio,
        int minMergeWidth,
        Integer maxMergeWidth
) {}
