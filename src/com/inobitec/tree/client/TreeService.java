package com.inobitec.tree.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.inobitec.tree.shared.model.Node;

@RemoteServiceRelativePath("tree")
public interface TreeService extends RemoteService {
    Node addRootNode(Node node) throws IllegalArgumentException;

    Node addChildNode(Node node, Integer parentId) throws IllegalArgumentException;

    Node editNode(Node node, Integer id) throws IllegalArgumentException;

    void deleteNode(Integer id) throws IllegalArgumentException;
    
    List<Node> getAllNodes()throws IllegalArgumentException;
}
