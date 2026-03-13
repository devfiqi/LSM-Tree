package com.minilsm.block;

import com.minilsm.key.KeySlice;

/**
 * Builds a block by accumulating key-value pairs up to a size threshold.
 * Equivalent to Rust's BlockBuilder struct.
 */
public class BlockBuilder {

    /** Offsets of each key-value entry within the data buffer. */
    private final java.util.List<Integer> offsets;

    /** All serialized key-value pairs. */
    private final java.io.ByteArrayOutputStream data;

    /** Target block size in bytes. */
    private final int blockSize;

    /** First key added to this block. */
    private final com.minilsm.key.KeyVec firstKey;

    public BlockBuilder(int blockSize) {
        this.offsets = new java.util.ArrayList<>();
        this.data = new java.io.ByteArrayOutputStream();
        this.blockSize = blockSize;
        this.firstKey = com.minilsm.key.KeyVec.create();
    }

    /**
     * Returns the estimated size of the block (data + offsets + num_entries).
     */
    private int estimatedSize() {
        return data.size() + offsets.size() * Short.BYTES + Short.BYTES;
    }

    /**
     * Adds a key-value pair to the block.
     * Returns false if the block is full and the entry was NOT added
     * (unless the block is empty — always accept the first entry).
     */
    public boolean add(KeySlice key, byte[] value) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Returns true if the block has no entries. */
    public boolean isEmpty() {
        return offsets.isEmpty();
    }

    /** Finalizes the block and returns an immutable Block. */
    public Block build() {
        throw new UnsupportedOperationException("TODO");
    }
}
