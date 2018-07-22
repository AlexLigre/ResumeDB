package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.exception.ExistStorageException;
import com.alexligre.resumedb.exception.NotExistStorageException;
import com.alexligre.resumedb.exception.StorageException;
import com.alexligre.resumedb.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected final static int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int numElements = 0;

    public int size() {
        return numElements;
    }

    public void clear() {
        Arrays.fill(storage, 0, numElements, null);
        numElements = 0;
    }

    public void save(Resume r) {
        if (numElements == storage.length) {
            throw new StorageException("Storage is full", r.getUuid());
        }

        int index = findIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        }

        addToStorage(r, index);
        numElements++;
    }

    protected abstract void addToStorage(Resume r, int index);

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, numElements);
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        } else storage[index] = r;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }

        deleteFromStorage(index);

        storage[numElements - 1] = null;
        numElements--;
    }

    protected abstract void deleteFromStorage(int index);

    protected abstract int findIndex(String uuid);
}
