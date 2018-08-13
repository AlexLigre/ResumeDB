package com.alexligre.resumedb.storage;

public abstract class AbstractStorage implements Storage{
    protected final static int STORAGE_LIMIT = 10_000;
    protected int numElements = 0;

    public int size() {
        return numElements;
    }
}
