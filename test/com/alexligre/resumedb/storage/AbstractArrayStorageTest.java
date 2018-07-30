package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.exception.ExistStorageException;
import com.alexligre.resumedb.exception.NotExistStorageException;
import com.alexligre.resumedb.exception.StorageException;
import com.alexligre.resumedb.model.Resume;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {

    protected static final int NUM_OF_RESUMES = 5;

    Storage ARRAY_STORAGE;
    Resume tResume = new Resume("id#125");

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
        ARRAY_STORAGE.save(tResume);
        assertEquals("id#125", ARRAY_STORAGE.get("id#125").getUuid());
    }

    @Test(expected = ExistStorageException.class)
    public void alreadyExists() {
        ARRAY_STORAGE.save(new Resume("id#" + (ARRAY_STORAGE.size() - 1)));
    }

    @Test
    public void overFlow() {
        try{
            ARRAY_STORAGE.save(new Resume("id#" + 1000));
        }catch (StorageException e){
            assertEquals("Storage is full", e.getMessage());
        }

    }

    @Test
    public void get() {
        assertEquals("id#5", ARRAY_STORAGE.get("id#5").getUuid());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        ARRAY_STORAGE.delete("id#3");
        ARRAY_STORAGE.get("id#3");
    }

    @Test
    public void getAll() {
        Resume[] rArr = {
                new Resume("id#1"),
                new Resume("id#2"),
                new Resume("id#3"),
                new Resume("id#4"),
                new Resume("id#5"),};
        assertArrayEquals(rArr, ARRAY_STORAGE.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void update() {
        ARRAY_STORAGE.update(tResume);
        assertEquals("id#125", ARRAY_STORAGE.get("id#125").getUuid());
    }
}
