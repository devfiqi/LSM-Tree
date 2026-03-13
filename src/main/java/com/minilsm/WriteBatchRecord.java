package com.minilsm;

/**
 * A record in a write batch — either a put or a delete.
 * Equivalent to Rust's WriteBatchRecord enum.
 */
public sealed interface WriteBatchRecord {

    record Put(byte[] key, byte[] value) implements WriteBatchRecord {}

    record Del(byte[] key) implements WriteBatchRecord {}
}
