package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.exception.NotExistStorageException;
import com.alexligre.resumedb.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    List<Resume> storage = new ArrayList<>();

    @Override
    protected Resume[] existsResumes() {
        Resume[] result = new Resume[numElements];
        storage.subList(0, numElements - 1).toArray(result);
        return result;
    }

    @Override
    protected void deleteFromStorage(int index) {
        storage.remove(index);
    }

    @Override
    protected Resume getResume(int index) {
        return storage.get(index);
    }

    @Override
    protected void clearStorage() {
        storage.clear();
    }

    @Override
    protected boolean updated(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index > -1) {
            storage.set(index, resume);
            return true;
        }
        return false;
    }

    @Override
    protected void addToStorage(Resume resume, int index) {
        storage.add(resume);
    }

    @Override
    protected int findIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return storage.indexOf(resume);
    }
}