package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.exception.ExistStorageException;
import com.alexligre.resumedb.exception.NotExistStorageException;
import com.alexligre.resumedb.exception.StorageException;
import com.alexligre.resumedb.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    List<Resume> storage = new ArrayList<>();

    @Override
    public void clear() {
        storage.clear();
        numElements = 0;
    }

    @Override
    public void update(Resume resume) {
        int index = storage.indexOf(resume);
        if (index > -1) {
            storage.set(index, resume);
            return;
        }
        throw new NotExistStorageException(resume.getUuid());
    }

    @Override
    public void save(Resume resume) {
        int index = storage.indexOf(resume);
        if (index >= 0) {
            throw new ExistStorageException(resume.getUuid());
        }
        if (numElements == STORAGE_LIMIT) {
            throw new StorageException("Storage is full", resume.getUuid());
        }
        storage.add(resume);
        numElements++;
    }

    @Override
    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index > -1) {
            return storage.get(index);
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index > -1) {
            storage.remove(index);
            numElements--;
            return;
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public Resume[] getAll() {
        Resume[] result = new Resume[numElements];
        storage.subList(0, numElements - 1).toArray(result);
        return result;
    }

    private int findIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return storage.indexOf(resume);
    }
}