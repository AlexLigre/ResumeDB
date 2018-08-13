package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.exception.ExistStorageException;
import com.alexligre.resumedb.exception.NotExistStorageException;
import com.alexligre.resumedb.exception.StorageException;
import com.alexligre.resumedb.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
        storage.clear();
        numElements = 0;
    }

    @Override
    public void update(Resume resume) {
        Resume result = findResume(resume.getUuid());
        if (result != null) {
            storage.put(resume.getUuid(), resume);
            return;
        }
        throw new NotExistStorageException(resume.getUuid());
    }

    @Override
    public void save(Resume resume) {
        Resume result = findResume(resume.getUuid());
        if (result != null) {
            throw new ExistStorageException(resume.getUuid());
        }
        if (numElements == STORAGE_LIMIT) {
            throw new StorageException("Storage is full", resume.getUuid());
        }
        storage.put(resume.getUuid(), resume);
        numElements++;
    }

    @Override
    public Resume get(String uuid) {
        Resume result = findResume(uuid);
        if (result != null) {
            return result;
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        Resume result = findResume(uuid);
        if (result != null) {
            storage.remove(uuid);
            numElements--;
            return;
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public Resume[] getAll() {
        Resume[] result = new Resume[numElements];
        storage.values().toArray(result);
        return result;
    }

    private Resume findResume(String uuid) {
        return storage.get(uuid);
    }
}
