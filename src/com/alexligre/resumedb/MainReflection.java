package com.alexligre.resumedb;

import com.alexligre.resumedb.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Resume resume = new Resume();
        System.out.println(resume);

        Method method = resume.getClass().getMethod("toString");
        System.out.println(method.invoke(resume));
    }
}
