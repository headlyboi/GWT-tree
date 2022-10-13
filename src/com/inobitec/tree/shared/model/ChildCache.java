package com.inobitec.tree.shared.model;

import java.util.HashMap;

public class ChildCache {

    private HashMap<String, Child> childMap = new HashMap<>();

    public HashMap<String, Child> getChildMap() {
        return childMap;
    }

    public void setChildMap(HashMap<String, Child> childMap) {
        this.childMap = childMap;
    }

}
