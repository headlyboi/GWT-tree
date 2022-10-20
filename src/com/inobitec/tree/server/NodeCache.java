package com.inobitec.tree.server;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

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
//TODO написать дай рутов дай детей все сломалось
    
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

    public Node editNode(Node node, Integer id) {
        Node oldNode = childMap.get(id);
        oldNode.setName(node.getName());
        oldNode.setIp(node.getIp());
        oldNode.setPort(node.getPort());
        return oldNode;
    }

    public void deleteNode(Integer id) {
        childMap.remove(id);
        for (int i = id + 1; i < childMap.size(); i++) {
            if (childMap.get(i).getParentId() == id) {
                childMap.remove(i);
            }
        }
    }

    public List<Node> getAllNodes() {
        List<Node> nodes = childMap.values().stream().collect(Collectors.toList());
        for (Node node : nodes) {
            System.out.print(node.getIp() + "");
            System.out.println(node.getName());
        }
        return nodes;
    }
}
