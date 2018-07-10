package storage;

import model.Resume;

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

    public Resume get(String uuid) {
        int indexOfResume = findIndex(uuid);
        if (indexOfResume >= 0) {
            return storage[indexOfResume];
        }
        return null;
    }

    public void update(Resume r) {
        int indexOfResume = findIndex(r.getUuid());
        if (indexOfResume < 0) {
            System.out.println("ERROR: Resume doesn't exist");
            return;
        } else storage[indexOfResume] = r;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, numElements);
    }

    protected abstract int findIndex(String uuid);
}
