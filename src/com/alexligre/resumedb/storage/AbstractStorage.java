package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.exception.ExistStorageException;
import com.alexligre.resumedb.exception.NotExistStorageException;
import com.alexligre.resumedb.exception.StorageException;
import com.alexligre.resumedb.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected final static int STORAGE_LIMIT = 10_000;
    protected int numElements = 0;

    public void clear() {
        clearStorage();
        numElements = 0;
    }

    public void update(Resume resume) {
        boolean result = updated(resume);
        if (result == false) {
            throw new NotExistStorageException(resume.getUuid());
        }
    }

    public void save(Resume resume) {
        int index = findIndex(resume.getUuid());

        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        if (numElements == STORAGE_LIMIT) {
            throw new StorageException("Storage is full", resume.getUuid());
        }

        addToStorage(resume, index);
        numElements++;
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index > -1) {
            return getResume(index);
        }
        throw new NotExistStorageException(uuid);
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index > -1) {
            deleteFromStorage(index);
            numElements--;
            return;
        }
        throw new NotExistStorageException(uuid);
    }

    public Resume[] getAll() {
        return existsResumes();
    }

    public int size() {
        return numElements;
    }

    protected abstract Resume[] existsResumes();

    protected abstract void deleteFromStorage(int index);

    protected abstract Resume getResume(int index);

    protected abstract void addToStorage(Resume resume, int index);

    protected abstract int findIndex(String uuid);

    protected abstract boolean updated(Resume resume);

    protected abstract void clearStorage();
}


