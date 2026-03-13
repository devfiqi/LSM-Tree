package com.minilsm.memtable;

import com.minilsm.iterators.StorageIterator;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Iterator over MemTable entries within a range.
 * Equivalent to Rust's MemTableIterator struct.
 *
 * Unlike the Rust version, no self-referencing is needed — Java's GC
 * keeps the underlying map alive as long as the iterator exists.
 */
public class MemTableIterator implements StorageIterator {

    /** Iterator over the submap entries. */
    private final Iterator<Map.Entry<byte[], byte[]>> iter;

    /** Current key-value pair (null if iterator is exhausted). */
    private Map.Entry<byte[], byte[]> current;

    public MemTableIterator(Iterator<Map.Entry<byte[], byte[]>> iter) {
        this.iter = iter;
        if (iter.hasNext()) {
            this.current = iter.next();
        } else {
            this.current = null;
        }
    }

    @Override
    public byte[] key() {
        if (current == null) throw new IllegalStateException("Iterator is invalid");
        return current.getKey();
    }

    @Override
    public byte[] value() {
        if (current == null) throw new IllegalStateException("Iterator is invalid");
        return current.getValue();
    }

    @Override
    public boolean isValid() {
        return current != null;
    }

    @Override
    public void next() throws IOException {
        if (iter.hasNext()) {
            current = iter.next();
        } else {
            current = null;
        }
    }
}
