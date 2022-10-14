package com.inobitec.tree.server;

import java.util.HashMap;

import com.inobitec.tree.shared.model.Child;

public class ChildCache {

    private HashMap<String, Child> childMap = new HashMap<>();

    private int id = 0;

    private int parentId;

    public Child addRootNode(Child child) {
        if (childMap.containsKey(child.getName())) {
            return null;
        }
        child.setId(id++);
        child.setParentId(-1);
        childMap.put(child.getName(), child);
        return child;
    }
}
