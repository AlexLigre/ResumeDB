package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage implements Storage {
    protected final static int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int numElements = 0;

    @Override
    public int size() {
        return numElements;
    }

    public void clear() {
        Arrays.fill(storage, 0, numElements, null);
        numElements = 0;
    }

    public void save(Resume r) {
        if (numElements == storage.length) {
            System.out.println("ERROR: Storage is full");
            return;
        }

        int index = findIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("ERROR: Resume is already exists");
            return;
        }

        addToStorage(r, index);
        numElements++;
    }

    protected abstract void addToStorage(Resume r, int index);

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index >= 0) {
            return storage[index];
        }
        System.out.print("ERROR: Resume doesn't exist  ");
        return null;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, numElements);
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index < 0) {
            System.out.println("ERROR: Resume doesn't exist");
        } else storage[index] = r;
    }

    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: Resume doesn't exist");
            return;
        }

        deleteFromStorage(index);

        storage[numElements - 1] = null;
        numElements--;
    }

    protected abstract void deleteFromStorage(int index);
    protected abstract int findIndex(String uuid);
}
