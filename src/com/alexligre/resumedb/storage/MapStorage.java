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
    public void save(Resume resume) {
        Resume result = findResumeInMap(resume.getUuid());
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
        Resume result = findResumeInMap(uuid);
        if (result != null) {
            return result;
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    public void delete(String uuid) {
        Resume result = findResumeInMap(uuid);
        if (result != null) {
            storage.remove(uuid);
            numElements--;
            return;
        }
        throw new NotExistStorageException(uuid);
    }

    @Override
    protected Resume[] existsResumes() {
        Resume[] result = new Resume[numElements];
        storage.values().toArray(result);
        return result;
    }

    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected boolean updated(Resume resume) {
        Resume result = findResumeInMap(resume.getUuid());
        if (result != null) {
            storage.put(resume.getUuid(), resume);
            return true;
        }
        return false;
    }

    @Override
    protected void addToStorage(Resume resume, int index) {
        storage.put(resume.getUuid(), resume);
    }

    private Resume findResumeInMap(String uuid) {
        return storage.get(uuid);
    }


    @Override
    protected void deleteFromStorage(int index) {
    }

    @Override
    protected int findIndex(String uuid) {
        return 0;
    }

    @Override
    protected Resume getResume(int index) {
        return null;
    }
}
