package com.jalfonso.utils.reflection;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class ReflectionTest {

    private Model model;

    @Before
    public void initModel() {
        model = new Model(1L, "model");
    }

    @Test
    public void getField() throws IllegalAccessException, NoSuchFieldException, IOException {
        assertEquals("model", Reflection.getField(model, "value", String.class));
    }

    @Test
    public void getField1() throws IllegalAccessException, NoSuchFieldException, IOException {
        assertEquals(Long.valueOf(1L), Reflection.getField(model, "id", Long.class, ModelParent.class));
    }

    @Test
    public void setField() throws IllegalAccessException, NoSuchFieldException, IOException {
        Reflection.setField(model, "value", "ledom");
        assertEquals("ledom", model.getValue());
    }

    @Test
    public void setField1() throws IllegalAccessException, NoSuchFieldException, IOException {
        Reflection.setField(model, "id", 2L, ModelParent.class);
        assertEquals(Long.valueOf(2L), model.getId());
    }

    @Test
    public void getMethod() {
        Method method = Reflection.getMethod("setValue", Model.class, String.class);
        assertEquals("setValue", method.getName());
    }

    @Test
    public void methodInvoke() throws IllegalAccessException, IOException, InvocationTargetException {
        Method method = Reflection.getMethod("length", Model.class);
        assertEquals(Integer.valueOf(5), Reflection.methodInvoke(model, method, Integer.class));
    }

    @Test
    public void voidMethodInvoke() throws InvocationTargetException, IllegalAccessException {
        Method method = Reflection.getMethod("setValue", Model.class, String.class);
        Reflection.voidMethodInvoke(model, method, "ledom");
        assertEquals("ledom", model.getValue());
    }
}
