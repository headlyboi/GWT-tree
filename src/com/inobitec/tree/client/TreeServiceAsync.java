package com.inobitec.tree.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.inobitec.tree.shared.model.Node;

public interface TreeServiceAsync {
    void addRootNode(Node node, AsyncCallback<Node> callback)
            throws IllegalArgumentException;

    void addChildNode(Node node, Integer parentId, AsyncCallback<Node> callback)
            throws IllegalArgumentException;

    void editNode(Node node, Integer id, AsyncCallback<Node> callback)
            throws IllegalArgumentException;

    void deleteNode(Integer id, AsyncCallback<Void> callback)
            throws IllegalArgumentException;

    void getAllNodes(AsyncCallback<List<Node>> callback)
            throws IllegalArgumentException;
}
