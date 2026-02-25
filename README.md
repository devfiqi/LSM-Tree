# LSM-Tree Storage Engine

**Language:** Go  
**Goal:** Understand how Cassandra and RocksDB work under the hood

## Objectives

Goal is to understand:

### 1. Write Optimization
- Why LSM-trees are fast for writes
- Write amplification vs read amplification tradeoffs
- When to use LSM vs B-tree

### 2. Core Components
- Memtable (in-memory writes)
- SSTables (sorted string tables on disk)
- Compaction (merging SSTables)

### 3. Storage Internals
- How Cassandra stores data
- How RocksDB works
- File formats and encoding

### 4. Performance Tradeoffs
- Read amplification problem
- Compaction strategies
- Bloom filters for optimization

---

## Architecture Plan
```
┌─────────────────┐
│   Write Path    │
└────────┬────────┘
         │
         ▼
    ┌─────────┐
    │ Memtable│ (In-memory, sorted)
    └────┬────┘
         │ (when full)
         ▼
    ┌─────────┐
    │ SSTable │ (On-disk, immutable)
    └────┬────┘
         │
         ▼
    ┌─────────────┐
    │ Compaction  │ (Merge SSTables)
    └─────────────┘

┌─────────────────┐
│   Read Path     │
└────────┬────────┘
         │
         ├─► Check Memtable
         ├─► Check SSTable L0
         ├─► Check SSTable L1
         └─► Check SSTable L2...
```

---

## Resources

- DDIA Chapter 3: Storage and Retrieval (LSM-trees section)
- "LSM-based Storage Techniques: A Survey" paper
- RocksDB architecture documentation
- Cassandra storage engine documentation

---

## Related Projects

- [Distributed KV Store](https://github.com/devfiqi/kv-store) - Replication and partitioning
- MapReduce Implementation - Distributed processing
- Rate Limiter - High-throughput systems

This project completes the storage layer understanding.
