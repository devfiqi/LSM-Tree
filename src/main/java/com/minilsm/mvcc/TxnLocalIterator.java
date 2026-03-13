package com.minilsm.mvcc;

import com.minilsm.iterators.StorageIterator;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Iterator over a transaction's local write buffer.
 * Equivalent to Rust's TxnLocalIterator struct.
 */
public class TxnLocalIterator implements StorageIterator {

    /** Reference to the local storage map. */
    private final ConcurrentSkipListMap<byte[], byte[]> map;

    /** Iterator over the submap entries. */
    private final Iterator<Map.Entry<byte[], byte[]>> iter;

    /** Current key-value pair. */
    private Map.Entry<byte[], byte[]> current;

    public TxnLocalIterator(ConcurrentSkipListMap<byte[], byte[]> map,
                             Iterator<Map.Entry<byte[], byte[]>> iter) {
        this.map = map;
        this.iter = iter;
        if (iter.hasNext()) {
            this.current = iter.next();
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
