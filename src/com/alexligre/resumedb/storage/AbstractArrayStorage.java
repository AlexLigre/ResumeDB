package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.exception.NotExistStorageException;
import com.alexligre.resumedb.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    @Override
    protected void clearStorage() {
        Arrays.fill(storage, null);
    }

    @Override
    protected boolean updated(Resume resume) {
        int index = findIndex(resume.getUuid());
        if (index > -1) {
            storage[index] = resume;
            return true;
        }
        return false;
    }

    @Override
    protected Resume getResume(int index) {
        return storage[index];
    }

    @Override
    protected Resume[] existsResumes() {
        return Arrays.copyOfRange(storage, 0, numElements);
    }

    protected abstract void deleteFromStorage(int index);

}
