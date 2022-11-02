package com.inobitec.tree.server.repository;

import java.util.List;

import com.inobitec.tree.shared.model.Node;

public interface NodeRepository {

    Node addRootNode(Node node);

    Node addChildNode(Node node, Integer parentId);

    Node editNodeById(Node node, Integer id);

    void deleteNodeById(Integer id);

    List<Node> getAllNodes();
}
