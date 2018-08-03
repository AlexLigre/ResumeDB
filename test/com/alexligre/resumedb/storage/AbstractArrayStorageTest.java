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
    Resume resume_6 = new Resume("id#6");
    Resume resume_0 = new Resume("id#0");

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void initTest() {
        storage.clear();
        storage.save(resume_1);
        storage.save(resume_2);
        storage.save(resume_3);
        storage.save(resume_4);
        storage.save(resume_5);
    }

    @Test
    public void size() {
        assertEquals(5, storage.size());
    }

    @Test()
    public void clear() {
        storage.clear();
        assertEquals(0, storage.size());
    }

    @Test
    public void save() {
        storage.save(resume_6);
        assertEquals(resume_6, storage.get("id#6"));
        try {
            storage.save(resume_6);
            fail("Resume id#6 saved twice");
        } catch (ExistStorageException e) {
            assertEquals("Resume id#6 already exists", e.getMessage());
        }
        try {
            storage.save(resume_0);
            fail("Resume id#0 saved");
        } catch (StorageException e) {
            assertEquals("Storage is full", e.getMessage());
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void get() {
        assertEquals(resume_5, storage.get("id#5"));
        storage.clear();
        assertEquals(resume_5, storage.get("id#5"));

    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete("id#3");
        storage.get("id#3");
    }

    @Test
    public void getAll() {
        Resume[] rArr = {resume_1, resume_2, resume_3, resume_4, resume_5};
        assertArrayEquals(rArr, storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void update() {
        storage.update(resume_6);
        assertEquals(resume_6, storage.get("id#6"));
    }
}
