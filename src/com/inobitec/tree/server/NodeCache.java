package com.inobitec.tree.server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import com.inobitec.tree.shared.model.Node;

public class NodeCache {

    private ConcurrentMap<Integer, Node> nodeMap = new ConcurrentHashMap<>();

    private static int lastId = 0;

    public Node addRootNode(Node child) {
        Node value = new Node();
        value.setId(lastId++);
        value.setParentId(-1);
        value.setName(child.getName());
        value.setIp(child.getIp());
        value.setPort(child.getPort());
        nodeMap.put(value.getId(), value);
        return value;
    }

    public Node addChildNode(Node child, Integer parentId) {
        Node value = new Node();
        if (!nodeMap.containsKey(parentId)) {
            return null;
        }
        value.setId(lastId++);
        value.setParentId(parentId);
        value.setName(child.getName());
        value.setIp(child.getIp());
        value.setPort(child.getPort());
        nodeMap.put(value.getId(), value);
        return value;
    }

    public Node editNode(Node node, Integer id) {
        Node oldNode = nodeMap.get(id);
        oldNode.setName(node.getName());
        oldNode.setIp(node.getIp());
        oldNode.setPort(node.getPort());
        return oldNode;
    }

//TODO делит прикольный
    public void deleteNode(Integer id) {
        List<Integer> keysToDelete = new ArrayList<>();
        keysToDelete.add(id);

        List<Node> nodes = nodeMap.values().stream().collect(Collectors.toList());
        for (Node node : nodes) {
            if (node.getParentId().equals(id) || keysToDelete.contains(node.getParentId())) {
                keysToDelete.add(node.getId());
            }
        }

        for (Integer key : keysToDelete) {
            nodeMap.remove(key);
        }
    }

    public List<Node> getAllNodes() {
        List<Node> nodes = nodeMap.values().stream().collect(Collectors.toList());
        return nodes;
    }
}
