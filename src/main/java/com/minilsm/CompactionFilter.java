package com.minilsm;

/**
 * Filter applied during compaction to drop unwanted keys.
 * Equivalent to Rust's CompactionFilter enum.
 */
public sealed interface CompactionFilter {

    record Prefix(byte[] prefix) implements CompactionFilter {}
}
