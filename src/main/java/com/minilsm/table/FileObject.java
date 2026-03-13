package com.minilsm.table;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Abstraction over a file on disk for SSTable storage.
 * Equivalent to Rust's FileObject struct.
 */
public class FileObject {

    private final RandomAccessFile file;
    private final long size;

    private FileObject(RandomAccessFile file, long size) {
        this.file = file;
        this.size = size;
    }

    /** Reads len bytes starting at offset from the file. */
    public byte[] read(long offset, int len) throws IOException {
        byte[] buf = new byte[len];
        synchronized (file) {
            file.seek(offset);
            file.readFully(buf);
        }
        return buf;
    }

    /** Returns the file size in bytes. */
    public long size() {
        return size;
    }

    /** Creates a new file, writes data, and returns a FileObject. */
    public static FileObject create(Path path, byte[] data) throws IOException {
        Files.write(path, data);
        RandomAccessFile raf = new RandomAccessFile(path.toFile(), "r");
        return new FileObject(raf, data.length);
    }

    /** Opens an existing file for reading. */
    public static FileObject open(Path path) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(path.toFile(), "r");
        long size = raf.length();
        return new FileObject(raf, size);
    }

    public void close() throws IOException {
        if (file != null) {
            file.close();
        }
    }
}
