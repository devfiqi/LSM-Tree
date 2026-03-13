package com.minilsm.key;

import java.util.Arrays;

/**
 * Borrowed key reference — wraps a byte[] without owning it.
 * Equivalent to Rust's KeySlice<'a> = Key<&'a [u8]>.
 */
public record KeySlice(byte[] raw) implements Comparable<KeySlice> {

    public static final boolean TS_ENABLED = false;

    public static KeySlice fromSlice(byte[] slice) {
        return new KeySlice(slice);
    }

    public static KeySlice fromSlice(byte[] slice, int offset, int len) {
        return new KeySlice(Arrays.copyOfRange(slice, offset, offset + len));
    }

    public KeyVec toKeyVec() {
        return KeyVec.fromVec(Arrays.copyOf(raw, raw.length));
    }

    public KeyBytes toKeyBytes() {
        return KeyBytes.fromBytes(Arrays.copyOf(raw, raw.length));
    }

    public int length() {
        return raw.length;
    }

    public boolean isEmpty() {
        return raw.length == 0;
    }

    /** Test helper — returns 0 since TS_ENABLED is false. */
    public long forTestingTs() {
        return 0;
    }

    @Override
    public int compareTo(KeySlice other) {
        return Arrays.compareUnsigned(this.raw, other.raw);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KeySlice ks)) return false;
        return Arrays.equals(raw, ks.raw);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(raw);
    }

    @Override
    public String toString() {
        return "KeySlice(" + new String(raw) + ")";
    }
}
