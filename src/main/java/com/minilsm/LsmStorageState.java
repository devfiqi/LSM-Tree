package com.minilsm;

import com.minilsm.memtable.MemTable;
import com.minilsm.table.SsTable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents the current state of the LSM storage engine.
 * Equivalent to Rust's LsmStorageState struct.
 *
 * This is treated as an immutable snapshot — modifications create a new state
 * object to enable lock-free reads via copy-on-write.
 */
public class LsmStorageState {

    /** Current writable memtable. */
    private final MemTable memtable;

    /** Immutable memtables awaiting flush (latest to earliest). */
    private final List<MemTable> immMemtables;

    /** Level 0 SSTable IDs (latest to earliest). */
    private final List<Integer> l0Sstables;

    /** Levels L1 to L_max. Each entry is (levelId, list of SSTable IDs). */
    private final List<Map.Entry<Integer, List<Integer>>> levels;

    /** All SSTable objects by ID. */
    private final Map<Integer, SsTable> sstables;

    public LsmStorageState(MemTable memtable,
                           List<MemTable> immMemtables,
                           List<Integer> l0Sstables,
                           List<Map.Entry<Integer, List<Integer>>> levels,
                           Map<Integer, SsTable> sstables) {
        this.memtable = memtable;
        this.immMemtables = immMemtables;
        this.l0Sstables = l0Sstables;
        this.levels = levels;
        this.sstables = sstables;
    }

    /** Creates the initial state with an empty memtable. */
    public static LsmStorageState create(LsmStorageOptions options) {
        int numLevels = switch (options.compactionOptions()) {
            case com.minilsm.compact.CompactionOptions.Leveled l -> l.options().maxLevels();
            case com.minilsm.compact.CompactionOptions.Simple s -> s.options().maxLevels();
            case com.minilsm.compact.CompactionOptions.Tiered t -> 0;
            case com.minilsm.compact.CompactionOptions.NoCompaction n -> 1;
        };

        List<Map.Entry<Integer, List<Integer>>> levels = new ArrayList<>();
        for (int i = 1; i <= numLevels; i++) {
            levels.add(Map.entry(i, new ArrayList<>()));
        }

        return new LsmStorageState(
                MemTable.create(0),
                new ArrayList<>(),
                new ArrayList<>(),
                levels,
                new HashMap<>()
        );
    }

    public MemTable getMemtable() { return memtable; }
    public List<MemTable> getImmMemtables() { return immMemtables; }
    public List<Integer> getL0Sstables() { return l0Sstables; }
    public List<Map.Entry<Integer, List<Integer>>> getLevels() { return levels; }
    public Map<Integer, SsTable> getSstables() { return sstables; }
}
