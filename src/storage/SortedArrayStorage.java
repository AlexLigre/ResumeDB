package storage;

import model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int findIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, numElements, searchKey);
    }

    @Override
    public void save(Resume r) {
        int index = Arrays.binarySearch(storage, 0, numElements, r);
        if (numElements == storage.length) {
            System.out.println("ERROR: Storage is full");
            return;
        }

        if (index >= 0 & index < numElements) {
            System.out.println("ERROR: Resume is already exists");
            return;
        }

        index = -index - 1;
        if (index < numElements) {
            for (int i = numElements - 1; i >= index; i--) {
                storage[i + 1] = storage[i];
            }
        }
        storage[index] = r;
        numElements++;
    }

    @Override
    public void delete(String uuid) {
        int index = findIndex(uuid);
        if (index < 0) {
            System.out.println("ERROR: Resume doesn't exist");
            return;
        }

        storage[index] = null;
        for (int i = index; i < numElements; i++) {
            storage[i] = storage[i + 1];
        }
        storage[numElements - 1] = null;
        numElements--;
    }
}