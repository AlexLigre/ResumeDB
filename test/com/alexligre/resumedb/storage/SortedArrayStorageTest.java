package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.model.Resume;
import org.junit.Before;

public class SortedArrayStorageTest extends AbstractArrayStorageTest{

    @Before
    public void init() {
        ARRAY_STORAGE = fillStorage(NUM_OF_RESUMES);
    }

    private SortedArrayStorage fillStorage(int numOfResumes) {
        SortedArrayStorage result = new SortedArrayStorage();
        for (int i = 0; i < numOfResumes; i++) {
            result.save(new Resume("id#" + (i + 1)));
        }
        return result;
    }

}