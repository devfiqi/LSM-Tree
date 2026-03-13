package com.minilsm.mvcc;

import java.util.Set;

/**
 * Data tracked for committed transactions (for serializable validation).
 * Equivalent to Rust's CommittedTxnData struct.
 */
public record CommittedTxnData(
        Set<Integer> keyHashes,
        long readTs,
        long commitTs
) {}
