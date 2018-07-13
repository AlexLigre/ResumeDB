package com.alexligre.resumedb.storage;
import com.alexligre.resumedb.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addToStorage(Resume r, int index) {
        storage[numElements] = r;
    }

    @Override
    protected void deleteFromStorage(int index) {
        storage[index] = storage[numElements - 1];
    }

    @Override
    protected int findIndex(String uuid) {
        for (int i = 0; i < numElements; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
