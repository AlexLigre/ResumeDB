package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.exception.StorageException;
import com.alexligre.resumedb.model.Resume;
import org.junit.Test;

import static com.alexligre.resumedb.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.Assert.fail;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    Storage storage;

    public AbstractArrayStorageTest(Storage storage) {
        super(storage);
        this.storage = storage;
    }

    @Test(expected = StorageException.class)
    public void saveStorageException() {
        try {
            for (int i = storage.size(); i < STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (Exception e) {
            fail("Test was failed");
        }
        storage.save(new Resume());
    }
}
