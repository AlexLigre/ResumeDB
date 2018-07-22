package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.model.Resume;
import org.junit.Before;

public class ArrayStorageTest extends AbstractArrayStorageTest{
    @Before
    public void init() {
        ARRAY_STORAGE = fillStorage(NUM_OF_RESUMES);
    }

    private ArrayStorage fillStorage(int numOfResumes) {
        ArrayStorage result = new ArrayStorage();
        for (int i = 0; i < numOfResumes; i++) {
            result.save(new Resume("id#" + (i + 1)));
        }
        return result;
    }
}