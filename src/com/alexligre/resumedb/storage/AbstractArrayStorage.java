package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.exception.ExistStorageException;
import com.alexligre.resumedb.exception.NotExistStorageException;
import com.alexligre.resumedb.exception.StorageException;
import com.alexligre.resumedb.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected final static int STORAGE_LIMIT = 10_000;
    protected int nElements = 0;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    public void clear() {
        Arrays.fill(storage, 0, nElements, null);
        nElements = 0;
    }

    public void update(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
            return;
        }
        throw new NotExistStorageException(resume.getUuid());
    }

    public void save(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }

        if (nElements == storage.length) {
            throw new StorageException("Storage is full", resume.getUuid());
        }

        addToStorage(resume, index);
        nElements++;
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index > -1) {
            return storage[index];
        }
        throw new NotExistStorageException(uuid);
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index > -1) {
            deleteFromStorage(index);
            storage[nElements - 1] = null;
            nElements--;
            return;
        }
        throw new NotExistStorageException(uuid);
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, nElements);
    }

    public int size() {
        return nElements;
    }

    protected abstract void deleteFromStorage(int index);

    protected abstract void addToStorage(Resume resume, int index);

    protected abstract int findIndex(String uuid);
}
