package com.minilsm.key;

import java.util.Arrays;

/**
 * Owned mutable key — wraps a byte[] that can be modified.
 * Equivalent to Rust's KeyVec = Key<Vec<u8>>.
 */
public class KeyVec implements Comparable<KeyVec> {

    private byte[] data;

    private KeyVec(byte[] data) {
        this.data = data;
    }

    public static KeyVec create() {
        return new KeyVec(new byte[0]);
    }

    public static KeyVec fromVec(byte[] key) {
        return new KeyVec(key);
    }

    public void clear() {
        this.data = new byte[0];
    }

    public void append(byte[] extra) {
        byte[] newData = new byte[data.length + extra.length];
        System.arraycopy(data, 0, newData, 0, data.length);
        System.arraycopy(extra, 0, newData, data.length, extra.length);
        this.data = newData;
    }

    public void setFromSlice(KeySlice keySlice) {
        this.data = Arrays.copyOf(keySlice.raw(), keySlice.raw().length);
    }

    public KeySlice asKeySlice() {
        return KeySlice.fromSlice(data);
    }

    public KeyBytes toKeyBytes() {
        return KeyBytes.fromBytes(Arrays.copyOf(data, data.length));
    }

    public byte[] raw() {
        return data;
    }

    public int length() {
        return data.length;
    }

    public boolean isEmpty() {
        return data.length == 0;
    }

    public long forTestingTs() {
        return 0;
    }

    @Override
    public int compareTo(KeyVec other) {
        return Arrays.compareUnsigned(this.data, other.data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KeyVec kv)) return false;
        return Arrays.equals(data, kv.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    @Override
    public String toString() {
        return "KeyVec(" + new String(data) + ")";
    }
}
