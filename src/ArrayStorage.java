import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {

    Resume[] storage = new Resume[10000];
    private int numElements = 0;

    void clear() {
        for (int i = 0; i < numElements; i++) {
            storage[i] = null;
        }
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
        Resume r = new Resume();

        if (findIndex(uuid) >= 0) {
            for (int i = 0; i < numElements; i++) {
                if (storage[i].uuid.equals(uuid)) {
                    r = storage[i];
                    break;
                }
            }
        }//else System.out.println("ERROR: Resume doesn't exist");
        //return null;
        else {
            r.uuid = "ERROR: Resume doesn't exist";
        }
        return r;
    }

    void update(Resume r) {
        if (get(r.uuid) == null) {
            System.out.println("ERROR: Resume doesn't exist");
            return;
        }
        int indexOfResume = findIndex(r.uuid);
        storage[indexOfResume] = r;

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

    void delete(String uuid) {
        if (findIndex(uuid) < 0) {
            System.out.println("ERROR: Resume doesn't exist");
            return;
        }
        for (int i = 0; i < numElements; i++) {
            if (storage[i].uuid.equals(uuid)) {
                System.arraycopy(storage, (i + 1), storage, i, numElements - (i + 1));
                numElements--;
                break;
            }

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
}
