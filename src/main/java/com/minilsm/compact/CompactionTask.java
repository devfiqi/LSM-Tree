package com.minilsm.compact;

import java.util.List;

/**
 * A compaction task describing which SSTables to merge.
 * Equivalent to Rust's CompactionTask enum.
 */
public sealed interface CompactionTask {

    record Leveled(LeveledCompactionTask task) implements CompactionTask {}
    record Simple(SimpleLeveledCompactionTask task) implements CompactionTask {}
    record Tiered(TieredCompactionTask task) implements CompactionTask {}
    record ForceFullCompaction(List<Integer> l0Sstables, List<Integer> l1Sstables) implements CompactionTask {}

    /** Returns true if this compaction targets the bottom level. */
    default boolean compactToBottomLevel() {
        return switch (this) {
            case Leveled l -> l.task().isLowerLevelBottomLevel();
            case Simple s -> s.task().isLowerLevelBottomLevel();
            case Tiered t -> t.task().bottomTierIncluded();
            case ForceFullCompaction f -> true;
        };
    }
}
