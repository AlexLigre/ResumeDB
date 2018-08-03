package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.exception.ExistStorageException;
import com.alexligre.resumedb.exception.NotExistStorageException;
import com.alexligre.resumedb.exception.StorageException;
import com.alexligre.resumedb.model.Resume;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    Storage storage;

    Resume resume_1 = new Resume("id#1");
    Resume resume_2 = new Resume("id#2");
    Resume resume_3 = new Resume("id#3");
    Resume resume_4 = new Resume("id#4");
    Resume resume_5 = new Resume("id#5");

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void initTest() {
        storage.clear();
        storage.save(resume_1);
        storage.save(resume_2);
        storage.save(resume_3);
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test()
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void save() {
        storage.save(resume_4);
        assertEquals(resume_4, storage.get("id#4"));
        try {
            storage.save(resume_5);
            fail("Resume id#5 saved");
        } catch (StorageException e) {
            assertEquals("Storage is full", e.getMessage());
        }
    }

    @Test
    public void saveExistStorageException() {
        storage.save(resume_4);
        try {
            storage.save(resume_4);
            fail("Resume id#4 saved twice");
        } catch (ExistStorageException e) {
            assertEquals("Resume id#4 already exists", e.getMessage());
        }
    }

    @Test
    public void saveStorageException() {
        storage.save(resume_4);
        try {
            storage.save(resume_5);
            fail("Resume id#5 saved");
        } catch (StorageException e) {
            assertEquals("Storage is full", e.getMessage());
        }
    }

    @Test
    public void get() {
        assertEquals(resume_3, storage.get("id#3"));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExistStorageException() {
        storage.clear();
        assertEquals(resume_3, storage.get("id#3"));
    }

    @Test
    public void delete() {
        storage.delete("id#3");
        Resume[] excpectedArray = {resume_1, resume_2};
        assertArrayEquals(excpectedArray, storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExistStorageException() {
        storage.delete("id#4");
    }

    @Test
    public void getAll() {
        Resume[] excpectedArray = {resume_1, resume_2, resume_3};
        assertArrayEquals(excpectedArray, storage.getAll());
    }

    @Test
    public void update() {
        storage.update(resume_3);
        assertEquals(resume_3, storage.get("id#3"));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExistStorageException() {
        storage.update(resume_4);
    }
}
