package com.wz.common;

import java.util.ArrayList;
import java.util.List;

public class NestedInteger {
    private int value;

    public NestedInteger() {
    }

    public NestedInteger(int value) {
        this.value = value;
    }

    public boolean isInteger() {
        return true;
    }

    public Integer getInteger() {
        return value;
    }

    public void setInteger(int value) {
        this.value = value;
    }

    public void add(NestedInteger ni) {

    }

    public List<NestedInteger> getList() {
        return new ArrayList<>();
    }

}
