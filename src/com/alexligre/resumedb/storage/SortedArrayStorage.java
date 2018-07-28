package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addToStorage(Resume resume, int index) {
        index = -index - 1;
        if (index < numElements) {
            System.arraycopy(storage, index, storage, index + 1, numElements - index);
        }
        storage[index] = resume;
    }

    @Override
    protected void deleteFromStorage(int index) {
        if (index != numElements - 1) {
            System.arraycopy(storage, index + 1, storage, index, numElements - index);
        }
    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, numElements, searchKey);
    }
}