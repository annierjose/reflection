package com.jalfonso.utils.reflection;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Reflection {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static  <F, O> F getField (final O object, final String fieldName, Class<F> fClass) throws IllegalAccessException, IOException, NoSuchFieldException {
        return getField(object, fieldName, fClass, object.getClass());
    }

    public static  <F, O, P> F getField (final O object, final String fieldName, Class<F> fClass, Class<P> parentClass) throws IllegalAccessException, IOException, NoSuchFieldException {
        Field field = parentClass.getDeclaredField(fieldName);
        field.setAccessible(true);
        return objectMapper.readValue(objectMapper.writeValueAsString(field.get(object)), fClass);
    }

    public static  <F, O> void setField (final O object, final String fieldName, F value) throws IllegalAccessException, IOException, NoSuchFieldException {
        setField(object, fieldName, value, object.getClass());
    }

    public static  <F, O, P> void setField (final O object, final String fieldName, F value, Class<P> parentClass) throws IllegalAccessException, IOException, NoSuchFieldException {
        Field field = parentClass.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(object, value);
    }

    public static <P> Method getMethod (final String methodName, Class<P> fClass, Class<?>... params) {
        Method method = null;
        try {
            method = fClass.getDeclaredMethod(methodName, params);
            method.setAccessible(true);
        } catch(NoSuchMethodException e) {
            e.printStackTrace();
        }
        return method;
    }

    public static <R, O> R methodInvoke (final O object, Method method, Class<R> rClass, Object... params) throws InvocationTargetException, IllegalAccessException, IOException {
        return objectMapper.readValue(objectMapper.writeValueAsString(method.invoke(object, params)), rClass);
    }

    public static <O> void voidMethodInvoke (final O object, Method method, Object... params) throws InvocationTargetException, IllegalAccessException {
        method.invoke(object, params);
    }
}
