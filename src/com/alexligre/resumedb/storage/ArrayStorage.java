package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addToStorage(Resume resume, int index) {
        storage[nElements] = resume;
    }

    @Override
    protected void deleteFromStorage(int index) {
        storage[index] = storage[nElements - 1];
        storage[nElements - 1] = null;
    }

    @Override
    protected int findIndex(String uuid) {
        for (int i = 0; i < nElements; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
