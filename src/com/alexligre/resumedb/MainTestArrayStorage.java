package com.alexligre.resumedb;

import com.alexligre.resumedb.model.Resume;
import com.alexligre.resumedb.storage.*;
/**
 * Test for storage.ArrayStorage
 */
public class MainTestArrayStorage {
    //static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();
    static final SortedArrayStorage ARRAY_STORAGE = new SortedArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));

        printAll();
        ARRAY_STORAGE.delete(r1.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        if (ARRAY_STORAGE.size() != 0) {
            System.out.println("\nGet All");
            for (Resume r : ARRAY_STORAGE.getAll()) {
                System.out.println(r);
            }
        } else System.out.println("\nStorage is empty");
    }
}
