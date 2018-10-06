package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.model.Resume;

import java.util.Arrays;
import java.util.List;

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    public List<Resume> getAllSorted() {
        Arrays.sort(storage, 0, size);
        return Arrays.asList(storage).subList(0, size);
    }

    @Override
    protected void addToStorage(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    protected Integer getSearchKey(String fullname) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getFullName().equals(fullname)) {
                return i;
            }
        }
        return -1;
    }
}
