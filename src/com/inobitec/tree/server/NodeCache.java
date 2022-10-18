package com.inobitec.tree.server;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.inobitec.tree.shared.model.Node;

public class NodeCache {

    private ConcurrentMap<Integer, Node> childMap = new ConcurrentHashMap<>();

    private static int lastId = 0;

    public Node addRootNode(Node child) {
        Node value = new Node();
        value.setId(lastId++);
        value.setParentId(-1);
        value.setName(child.getName());
        value.setIp(child.getIp());
        value.setPort(child.getPort());
        childMap.put(value.getId(), value);
        return value;
    }

    public Node addChildNode(Node child, Integer parentId) {
        Node value = new Node();
        if (!childMap.containsKey(parentId)) {
            return null;
        }
        value.setId(lastId++);
        value.setParentId(parentId);
        value.setName(child.getName());
        value.setIp(child.getIp());
        value.setPort(child.getPort());
        childMap.put(value.getId(), value);
        return value;
    }

    public void printAll() {
        childMap.entrySet().forEach(entry -> {
            System.out.println("id: " + entry.getKey() + " | "
                    + "parentId: " + entry.getValue().getParentId() + " | " +
                    "name: " + entry.getValue().getName() + " | ");
        });
    }
    
}
