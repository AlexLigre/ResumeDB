package storage;

import model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        Arrays.fill(storage, 0, numElements, null);
        numElements = 0;
    }

    public void save(Resume r) {
        if (numElements == storage.length) {
            System.out.println("ERROR: Storage is full");
            return;
        }
        if (findIndex(r.getUuid()) >= 0) {
            System.out.println("ERROR: Resume is already exists");
            return;
        }
        storage[numElements++] = r;
    }

    public void update(Resume r) {
        int indexOfResume = findIndex(r.getUuid());
        if (indexOfResume < 0) {
            System.out.println("ERROR: Resume doesn't exist");
            return;
        } else storage[indexOfResume] = r;
    }

    public void delete(String uuid) {
        int indexOfResume = findIndex(uuid);
        if (indexOfResume < 0) {
            System.out.println("ERROR: Resume doesn't exist");
            return;
        } else if (indexOfResume == numElements - 1) {
            storage[numElements - 1] = null;
            numElements--;
        } else {
            storage[indexOfResume] = storage[numElements - 1];
            storage[numElements - 1] = null;
            numElements--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, numElements);
    }

    protected int findIndex(String uuid) {
        for (int i = 0; i < numElements; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}