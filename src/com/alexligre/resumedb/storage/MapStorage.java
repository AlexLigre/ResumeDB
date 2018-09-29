package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {
    private Map<String, Resume> storage = new HashMap<>();

    @Override
    public void clear() {
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    protected void doDelete(Object searchKey) {
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return null;
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return null;
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return false;
    }
}
