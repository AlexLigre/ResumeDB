package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.exception.ExistStorageException;
import com.alexligre.resumedb.exception.NotExistStorageException;
import com.alexligre.resumedb.exception.StorageException;
import com.alexligre.resumedb.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected final static int STORAGE_LIMIT = 6;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int numElements = 0;

    public int size() {
        return numElements;
    }

    public void clear() {
        Arrays.fill(storage, 0, numElements, null);
        numElements = 0;
    }

    public void save(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }

        if (numElements == storage.length) {
            throw new StorageException("Storage is full", resume.getUuid());
        }

        addToStorage(resume, index);
        numElements++;
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index > -1) {
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, numElements);
    }

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
            return;
        }
        throw new NotExistStorageException(resume.getUuid());
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index >-1) {
            deleteFromStorage(index);
            storage[numElements - 1] = null;
            numElements--;
            return;
        }
        throw new NotExistStorageException(uuid);

    }

    protected abstract void addToStorage(Resume resume, int index);

    protected abstract void deleteFromStorage(int index);

    protected abstract int findIndex(String uuid);
}
