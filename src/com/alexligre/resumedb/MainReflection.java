package com.alexligre.resumedb;

import com.alexligre.resumedb.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new123123");
        System.out.println(r);

        System.out.println("********");
        //invoke r.toString via reflection

        Method []methods = r.getClass().getMethods();
        for (Method m:methods) {
            if(m.getName().equals("toString")){
                System.out.println(m.invoke(r));
                break;
            }
        }

    }
}
