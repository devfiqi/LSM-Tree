package com.minilsm.table;

import com.github.benmanes.caffeine.cache.Cache;
import com.minilsm.block.Block;
import com.minilsm.key.KeyBytes;
import com.minilsm.key.KeySlice;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 * A Sorted String Table (SSTable) — an immutable, sorted, on-disk data structure.
 * Equivalent to Rust's SsTable struct.
 *
 * File format:
 * [block_0] [block_1] ... [block_n] [meta_block] [meta_offset: u32] [bloom_filter] [bloom_offset: u32]
 */
public class SsTable {

    private final FileObject file;
    private final List<BlockMeta> blockMeta;
    private final int blockMetaOffset;
    private final int id;
    private final Cache<Long, Block> blockCache;
    private final KeyBytes firstKey;
    private final KeyBytes lastKey;
    private final Bloom bloom;
    private final long maxTs;

    SsTable(FileObject file, List<BlockMeta> blockMeta, int blockMetaOffset,
            int id, Cache<Long, Block> blockCache, KeyBytes firstKey,
            KeyBytes lastKey, Bloom bloom, long maxTs) {
        this.file = file;
        this.blockMeta = blockMeta;
        this.blockMetaOffset = blockMetaOffset;
        this.id = id;
        this.blockCache = blockCache;
        this.firstKey = firstKey;
        this.lastKey = lastKey;
        this.bloom = bloom;
        this.maxTs = maxTs;
    }

    /** Opens an SSTable for testing (no block cache). */
    public static SsTable openForTest(FileObject file) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Opens an SSTable from a file. */
    public static SsTable open(int id, Cache<Long, Block> blockCache, FileObject file) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Creates a metadata-only SSTable (for compaction state tracking). */
    public static SsTable createMetaOnly(int id, long fileSize, KeyBytes firstKey, KeyBytes lastKey) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Reads a block by index from disk. */
    public Block readBlock(int blockIdx) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Reads a block by index, using the cache if available. */
    public Block readBlockCached(int blockIdx) throws IOException {
        throw new UnsupportedOperationException("TODO");
    }

    /** Finds the block index that may contain the given key. */
    public int findBlockIdx(KeySlice key) {
        throw new UnsupportedOperationException("TODO");
    }

    /** Returns the number of blocks in this SSTable. */
    public int numOfBlocks() {
        return blockMeta.size();
    }

    public KeyBytes firstKey() {
        return firstKey;
    }

    public KeyBytes lastKey() {
        return lastKey;
    }

    public long tableSize() {
        return file.size();
    }

    public int sstId() {
        return id;
    }

    public long maxTs() {
        return maxTs;
    }

    public Bloom getBloom() {
        return bloom;
    }

    public List<BlockMeta> getBlockMeta() {
        return blockMeta;
    }
}
