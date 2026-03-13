package com.minilsm.compact;

import java.util.List;
import java.util.Map;

/**
 * A tiered compaction task.
 * Equivalent to Rust's TieredCompactionTask struct.
 */
public record TieredCompactionTask(
        List<Map.Entry<Integer, List<Integer>>> tiers,
        boolean bottomTierIncluded
) {}
