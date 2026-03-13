package com.minilsm;

import com.minilsm.compact.CompactionOptions;

/**
 * Configuration for the LSM storage engine.
 * Equivalent to Rust's LsmStorageOptions struct.
 */
public record LsmStorageOptions(
        int blockSize,
        int targetSstSize,
        int numMemtableLimit,
        CompactionOptions compactionOptions,
        boolean enableWal,
        boolean serializable
) {}
