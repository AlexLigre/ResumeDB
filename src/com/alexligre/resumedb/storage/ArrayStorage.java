package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.model.Resume;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addToStorage(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected void deleteFromStorage(int index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
