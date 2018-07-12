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
    protected void addToStorage(Resume r, int index) {
        index = -index - 1;
        if (index < numElements) {
            System.arraycopy(storage, index, storage, index + 1, numElements - index);
        }
        storage[index] = r;
        numElements++;
    }

    @Override
    protected void deleteFromStorage(int index) {
        storage[index] = null;
        System.arraycopy(storage, index + 1, storage, index, numElements - index);
        storage[numElements - 1] = null;
        numElements--;
    }
}