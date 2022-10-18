package com.inobitec.tree.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.inobitec.tree.shared.model.Node;

public interface TreeServiceAsync {
    void addRootNode(Node child, AsyncCallback<Node> callback)
            throws IllegalArgumentException;
    void addChildNode(Node child, Integer parentId, AsyncCallback<Node> callback) 
            throws IllegalArgumentException;
}
