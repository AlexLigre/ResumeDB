package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.exception.StorageException;
import com.alexligre.resumedb.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected final static int STORAGE_LIMIT = 10_000;
    protected int nElements = 0;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected abstract void addToStorage(Resume resume, int index);

    protected abstract void deleteFromStorage(int index);

    protected abstract Integer getSearchKey(String uuid);

    public void clear() {
        Arrays.fill(storage, 0, nElements, null);
        nElements = 0;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, nElements);
    }

    public int size() {
        return nElements;
    }

    @Override
    protected void doSave(Resume resume, Object index) {
        if (nElements == storage.length) {
            throw new StorageException("Storage is full", resume.getUuid());
        }
        addToStorage(resume, (Integer) index);
        nElements++;
    }

    @Override
    protected void doUpdate(Resume resume, Object index) {
        storage[(Integer) index] = resume;
    }

    @Override
    protected void doDelete(Object index) {
        deleteFromStorage((Integer) index);
        storage[nElements - 1] = null;
        nElements--;
    }

    @Override
    protected Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    @Override
    protected boolean isExist(Object index) {
        return (Integer) index >= 0;
    }
}
