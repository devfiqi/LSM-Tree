package com.minilsm.key;

import java.util.Arrays;

/**
 * Immutable owned key — wraps a byte[] that will not change.
 * Equivalent to Rust's KeyBytes = Key<Bytes>.
 */
public final class KeyBytes implements Comparable<KeyBytes> {

    private final byte[] data;

    private KeyBytes(byte[] data) {
        this.data = data;
    }

    public static KeyBytes fromBytes(byte[] bytes) {
        return new KeyBytes(bytes);
    }

    public static KeyBytes empty() {
        return new KeyBytes(new byte[0]);
    }

    public KeySlice asKeySlice() {
        return KeySlice.fromSlice(data);
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
    public int compareTo(KeyBytes other) {
        return Arrays.compareUnsigned(this.data, other.data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KeyBytes kb)) return false;
        return Arrays.equals(data, kb.data);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(data);
    }

    @Override
    public String toString() {
        return "KeyBytes(" + new String(data) + ")";
    }
}
