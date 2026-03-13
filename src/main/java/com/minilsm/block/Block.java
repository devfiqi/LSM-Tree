package com.minilsm.block;

import java.util.ArrayList;
import java.util.List;

/**
 * A block is the smallest unit of read and caching in the LSM tree.
 * Equivalent to Rust's Block struct.
 *
 * Binary format:
 * [entry_0] [entry_1] ... [entry_n] [offset_0: u16] [offset_1: u16] ... [offset_n: u16] [num_entries: u16]
 */
public class Block {

    /** Serialized key-value pairs. */
    private final byte[] data;

    /** Offsets of each key-value entry within data. */
    private final List<Integer> offsets;

    public Block(byte[] data, List<Integer> offsets) {
        this.data = data;
        this.offsets = offsets;
    }

    /**
     * Encodes the block to a byte array for storage.
     * Format: [data bytes] [offset_0: u16] ... [offset_n: u16] [num_entries: u16]
     */
    public byte[] encode() {
        throw new UnsupportedOperationException("TODO");
    }

    /**
     * Decodes a block from a byte array.
     */
    public static Block decode(byte[] encoded) {
        throw new UnsupportedOperationException("TODO");
    }

    public byte[] getData() {
        return data;
    }

    public List<Integer> getOffsets() {
        return offsets;
    }
}
