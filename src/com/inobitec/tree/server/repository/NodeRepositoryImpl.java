package com.inobitec.tree.server.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import com.inobitec.tree.shared.model.Node;

public class NodeRepositoryImpl implements NodeRepository {
    // key - node id
    private ConcurrentMap<Integer, Node> nodeMap = new ConcurrentHashMap<>();

    private static int lastId = 1;

    public Node addRootNode(Node node) {
        Node value = new Node();
        value.setId(lastId++);
        value.setParentId(-1);
        value.setName(node.getName());
        value.setIp(node.getIp());
        value.setPort(node.getPort());
        nodeMap.put(value.getId(), value);
        return value;
    }

    public Node addChildNode(Node node, Integer parentId) {
        Node value = new Node();
        if (!nodeMap.containsKey(parentId)) {
            return null;
        }
        value.setId(lastId++);
        value.setParentId(parentId);
        value.setName(node.getName());
        value.setIp(node.getIp());
        value.setPort(node.getPort());
        nodeMap.put(value.getId(), value);
        return value;
    }

    public Node editNodeById(Node node, Integer id) {
        if (nodeMap.get(id) == null) {
            return null;
        }
        Node oldNode = nodeMap.get(id);
        oldNode.setName(node.getName());
        oldNode.setIp(node.getIp());
        oldNode.setPort(node.getPort());
        return oldNode;
    }

    public void deleteNodeById(Integer id) {
        List<Integer> keysToDelete = new ArrayList<>();
        keysToDelete.add(id);

        Collection<Node> nodes = nodeMap.values();
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
