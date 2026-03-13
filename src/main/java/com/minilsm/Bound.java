package com.minilsm;

/**
 * Represents a bound for range queries.
 * Equivalent to Rust's std::ops::Bound<T>.
 */
public sealed interface Bound {

    record Included(byte[] key) implements Bound {}

    record Excluded(byte[] key) implements Bound {}

    record Unbounded() implements Bound {}
}
