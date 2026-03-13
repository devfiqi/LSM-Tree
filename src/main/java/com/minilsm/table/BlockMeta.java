package com.minilsm.table;

import com.minilsm.key.KeyBytes;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * Metadata for a data block within an SSTable.
 * Equivalent to Rust's BlockMeta struct.
 */
public class BlockMeta {

    /** Offset of this block in the SSTable file. */
    private final int offset;

    /** First key in the block. */
    private final KeyBytes firstKey;

    /** Last key in the block. */
    private final KeyBytes lastKey;

    public BlockMeta(int offset, KeyBytes firstKey, KeyBytes lastKey) {
        this.offset = offset;
        this.firstKey = firstKey;
        this.lastKey = lastKey;
    }

    /** Encodes a list of block metadata into a byte buffer. */
    public static void encodeBlockMeta(List<BlockMeta> blockMeta, java.io.ByteArrayOutputStream buf) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Decodes block metadata from a ByteBuffer. */
    public static List<BlockMeta> decodeBlockMeta(ByteBuffer buf) {
        throw new UnsupportedOperationException("TODO");
    }

    public int getOffset() {
        return offset;
    }

    public KeyBytes getFirstKey() {
        return firstKey;
    }

    public KeyBytes getLastKey() {
        return lastKey;
    }
}
