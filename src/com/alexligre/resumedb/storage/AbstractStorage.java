package com.alexligre.resumedb.storage;

import com.alexligre.resumedb.exception.ExistStorageException;
import com.alexligre.resumedb.exception.NotExistStorageException;
import com.alexligre.resumedb.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected abstract void doDelete(Object searchKey);

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doSave(Resume resume, Object searchKey);

    protected abstract void doUpdate(Resume resume, Object searchKey);

    protected abstract Object getSearchKey(String fullName);

    protected abstract boolean isExist(Object searchKey);

    public void delete(String fullName) {
        Object searchKey = getExistedKey(fullName);
        doDelete(searchKey);
    }

    public Resume get(String fullName) {
        Object searchKey = getExistedKey(fullName);
        return doGet(searchKey);
    }

    public void save(Resume resume) {
        Object searchKey = getNotExistedKey(resume.getFullName());
        doSave(resume, searchKey);
    }

    public void update(Resume resume) {
        Object searchKey = getExistedKey(resume.getFullName());
        doUpdate(resume, searchKey);
    }

    private Object getExistedKey(String fullName) {
        Object searchKey = getSearchKey(fullName);
        if (!isExist(searchKey)) {
            throw new NotExistStorageException(fullName);
        }
        return searchKey;
    }

    private Object getNotExistedKey(String fullName) {
        Object searchKey = getSearchKey(fullName);
        if (isExist(searchKey)) {
            throw new ExistStorageException(fullName);
        }
        return searchKey;
    }
}
