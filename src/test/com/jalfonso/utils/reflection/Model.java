package com.jalfonso.utils.reflection;

public class Model extends ModelParent {
    private Long id;
    private String value;

    public Model(Long id, String value) {
        super(id);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer length() {
        return value.length();
    }
}
