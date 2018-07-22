package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.exception.NotExistStorageException;
import com.alexligre.resumedb.model.Resume;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public abstract class AbstractArrayStorageTest {

    protected static final int NUM_OF_RESUMES = 5;

    Storage ARRAY_STORAGE;
    Resume[] rArr = {new Resume("id#1"),
            new Resume("id#2"),
            new Resume("id#3"),
            new Resume("id#4"),
            new Resume("id#5"),};

    @Test
    public void size() {
        assertEquals(5, ARRAY_STORAGE.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void clear() {
        ARRAY_STORAGE.clear();
        ARRAY_STORAGE.get("id#1");
    }

    @Test
    public void save() {
        ARRAY_STORAGE.save(new Resume("id#125"));
        assertEquals("id#125", ARRAY_STORAGE.get("id#125").getUuid());
    }

    @Test
    public void get() {
        assertEquals("id#5", ARRAY_STORAGE.get("id#5").getUuid());
    }

    @Test
    public void getAll() {
        assertArrayEquals(rArr, ARRAY_STORAGE.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void update() {
        ARRAY_STORAGE.update(new Resume("id#125"));
        assertEquals("id#125", ARRAY_STORAGE.get("id#125").getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        ARRAY_STORAGE.delete("id#3");
        ARRAY_STORAGE.get("id#3");
    }


}
