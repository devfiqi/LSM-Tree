package com.minilsm.compact;

import com.minilsm.LsmStorageState;

import java.util.List;

/**
 * Dispatches compaction operations to the appropriate strategy controller.
 * Equivalent to Rust's CompactionController enum.
 */
public class CompactionController {

    private final LeveledCompactionController leveled;
    private final SimpleLeveledCompactionController simple;
    private final TieredCompactionController tiered;
    private final boolean noCompaction;

    private CompactionController(LeveledCompactionController leveled,
                                  SimpleLeveledCompactionController simple,
                                  TieredCompactionController tiered,
                                  boolean noCompaction) {
        this.leveled = leveled;
        this.simple = simple;
        this.tiered = tiered;
        this.noCompaction = noCompaction;
    }

    /** Creates a controller from compaction options. */
    public static CompactionController create(CompactionOptions options) {
        return switch (options) {
            case CompactionOptions.Leveled l ->
                    new CompactionController(new LeveledCompactionController(l.options()), null, null, false);
            case CompactionOptions.Simple s ->
                    new CompactionController(null, new SimpleLeveledCompactionController(s.options()), null, false);
            case CompactionOptions.Tiered t ->
                    new CompactionController(null, null, new TieredCompactionController(t.options()), false);
            case CompactionOptions.NoCompaction n ->
                    new CompactionController(null, null, null, true);
        };
    }

    /** Generates a compaction task based on current state, or null if none needed. */
    public CompactionTask generateCompactionTask(LsmStorageState snapshot) {
        if (noCompaction) return null;
        if (leveled != null) {
            var task = leveled.generateCompactionTask(snapshot);
            return task != null ? new CompactionTask.Leveled(task) : null;
        }
        if (simple != null) {
            var task = simple.generateCompactionTask(snapshot);
            return task != null ? new CompactionTask.Simple(task) : null;
        }
        if (tiered != null) {
            var task = tiered.generateCompactionTask(snapshot);
            return task != null ? new CompactionTask.Tiered(task) : null;
        }
        return null;
    }

    /**
     * Applies the result of a compaction.
     */
    public CompactionResult applyCompactionResult(LsmStorageState snapshot,
                                                   CompactionTask task,
                                                   List<Integer> output,
                                                   boolean inRecovery) {
        return switch (task) {
            case CompactionTask.Leveled l ->
                    leveled.applyCompactionResult(snapshot, l.task(), output, inRecovery);
            case CompactionTask.Simple s ->
                    simple.applyCompactionResult(snapshot, s.task(), output);
            case CompactionTask.Tiered t ->
                    tiered.applyCompactionResult(snapshot, t.task(), output);
            case CompactionTask.ForceFullCompaction f ->
                    throw new UnsupportedOperationException("TODO");
        };
    }

    /** Returns true if flushes should go to L0 (leveled/simple) vs tiers (tiered). */
    public boolean flushToL0() {
        return tiered == null;
    }
}
