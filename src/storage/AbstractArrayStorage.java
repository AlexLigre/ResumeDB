package storage;

import model.Resume;

public abstract class AbstractArrayStorage implements Storage {
    protected final static int STORAGE_LIMIT = 10_000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int numElements = 0;

    @Override
    public int size() {
        return numElements;
    }

    public Resume get(String uuid) {
        int indexOfResume = findIndex(uuid);
        if (indexOfResume >= 0) {
            return storage[indexOfResume];
        }
        return null;
    }

    protected abstract int findIndex(String uuid);
}
