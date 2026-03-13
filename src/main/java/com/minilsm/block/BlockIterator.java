package com.minilsm.block;

import com.minilsm.key.KeySlice;
import com.minilsm.key.KeyVec;

/**
 * Iterates over entries within a single Block.
 * Equivalent to Rust's BlockIterator struct.
 */
public class BlockIterator {

    /** The block being iterated. */
    private final Block block;

    /** Current key (empty = invalid iterator). */
    private final KeyVec key;

    /** Start and end offsets of the current value within block data. */
    private int valueRangeStart;
    private int valueRangeEnd;

    /** Current entry index in [0, numEntries). */
    private int idx;

    /** First key in the block (cached). */
    private final KeyVec firstKey;

    private BlockIterator(Block block) {
        this.block = block;
        this.key = KeyVec.create();
        this.valueRangeStart = 0;
        this.valueRangeEnd = 0;
        this.idx = 0;
        this.firstKey = KeyVec.create();
    }

    /** Creates an iterator positioned at the first entry of the block. */
    public static BlockIterator createAndSeekToFirst(Block block) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Creates an iterator and seeks to the first key >= target. */
    public static BlockIterator createAndSeekToKey(Block block, KeySlice target) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Returns the current key. */
    public KeySlice key() {
        return key.asKeySlice();
    }

    /** Returns the current value. */
    public byte[] value() {
        return java.util.Arrays.copyOfRange(
                block.getData(), valueRangeStart, valueRangeEnd);
    }

    /** Returns true if the iterator is positioned at a valid entry. */
    public boolean isValid() {
        return !key.isEmpty();
    }

    /** Seeks to the first entry. */
    public void seekToFirst() {
        throw new UnsupportedOperationException("TODO");
    }

    /** Advances to the next entry. */
    public void next() {
        throw new UnsupportedOperationException("TODO");
    }

    /** Seeks to the first entry with key >= target. */
    public void seekToKey(KeySlice target) {
        throw new UnsupportedOperationException("TODO");
    }
}
