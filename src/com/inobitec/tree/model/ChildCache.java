package com.inobitec.tree.model;

import java.util.HashMap;
import java.util.Map;

public class ChildCache {

    private Map<String, Child> hashMap = new HashMap<>();

    public ChildCache() {
        hashMap.put("rootNode", new Child());
    }

    public Child getChildByKey(String name) {
        return hashMap.get(name);
    }

    public void putChild(String K, Child V) {
        hashMap.put(K, V);  
    }
}
