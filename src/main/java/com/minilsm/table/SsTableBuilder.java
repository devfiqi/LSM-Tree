package com.minilsm.table;

import com.github.benmanes.caffeine.cache.Cache;
import com.minilsm.block.Block;
import com.minilsm.block.BlockBuilder;
import com.minilsm.key.KeySlice;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Builds an SSTable by accumulating key-value pairs into blocks.
 * Equivalent to Rust's SsTableBuilder struct.
 */
public class SsTableBuilder {

    /** Current block being built. */
    private BlockBuilder builder;

    /** First key across all blocks. */
    private byte[] firstKey;

    /** Last key across all blocks. */
    private byte[] lastKey;

    /** Accumulated block data. */
    private final ByteArrayOutputStream data;

    /** Metadata for all completed blocks. */
    private final List<BlockMeta> meta;

    /** Target block size in bytes. */
    private final int blockSize;

    public SsTableBuilder(int blockSize) {
        this.builder = new BlockBuilder(blockSize);
        this.firstKey = new byte[0];
        this.lastKey = new byte[0];
        this.data = new ByteArrayOutputStream();
        this.meta = new ArrayList<>();
        this.blockSize = blockSize;
    }

    /**
     * Adds a key-value pair. Splits to a new block when the current one is full.
     */
    public void add(KeySlice key, byte[] value) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Returns the estimated size of the SSTable (data blocks only). */
    public int estimatedSize() {
        return data.size();
    }

    /**
     * Builds the SSTable and writes it to disk.
     */
    public SsTable build(int id, Cache<Long, Block> blockCache, Path path) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Test helper — builds without a block cache. */
    public SsTable buildForTest(Path path) throws IOException {
        return build(0, null, path);
    }
}
