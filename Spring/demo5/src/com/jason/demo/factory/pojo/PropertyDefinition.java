package com.jason.demo.factory.pojo;

/**
 * Created by cjs on 2016/11/14.
 */
public class PropertyDefinition {

    private String name;
    private String ref;

    public PropertyDefinition(String name, String ref) {
        this.name = name;
        this.ref = ref;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRef() {
        return ref;
    }
    public void setRef(String ref) {
        this.ref = ref;
    }

}