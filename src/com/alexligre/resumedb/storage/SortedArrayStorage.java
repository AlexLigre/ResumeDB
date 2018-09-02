package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addToStorage(Resume resume, int index) {
        index = -index - 1;
        if (index < nElements) {
            System.arraycopy(storage, index, storage, index + 1, nElements - index);
        }
        storage[index] = resume;
    }

    @Override
    protected void deleteFromStorage(int index) {
        if (index != nElements - 1) {
            System.arraycopy(storage, index + 1, storage, index, nElements - index);
            storage[nElements - 1] = null;
        }

    }

    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, nElements, searchKey);
    }
}