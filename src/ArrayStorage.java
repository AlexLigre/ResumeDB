import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    Resume[] storage = new Resume[10000];
    private int numElements = 0;

    void clear() {
        Arrays.fill(storage, 0, numElements, null);
        numElements = 0;
    }

    void save(Resume r) {
        if (findIndex(r.uuid) >= 0) {
            System.out.println("ERROR: Resume is already exists");
            return;
        }
        if (numElements == storage.length) {
            System.out.println("ERROR: Storage is full");
            return;
        }
        storage[numElements++] = r;
    }

    Resume get(String uuid) {
        int indexOfResume = findIndex(uuid);
        if (indexOfResume >= 0) {
            return storage[indexOfResume];
        }
        return null;
    }

    void update(Resume r) {
        int indexOfResume = findIndex(r.uuid);
        if (indexOfResume < 0) {
            System.out.println("ERROR: Resume doesn't exist");
            return;
        } else storage[indexOfResume] = r;
    }

    void delete(String uuid) {
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
    Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, numElements);
    }

    int size() {
        return numElements;
    }

    private int findIndex(String uuid) {
        int result = -1;
        for (int i = 0; i < numElements; i++) {
            if (storage[i].uuid.equals(uuid)) {
                result = i;
                break;
            }
        }
        return result;
    }
}
