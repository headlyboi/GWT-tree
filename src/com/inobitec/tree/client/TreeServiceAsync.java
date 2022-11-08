package com.inobitec.tree.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.inobitec.tree.shared.model.Node;

public interface TreeServiceAsync {
    void addRootNode(Node node, AsyncCallback<Node> callback);

    void addChildNode(Node node, Integer parentId, AsyncCallback<Node> callback);

    void editNodeById(Node node, Integer id, AsyncCallback<Node> callback);

    void deleteNodeById(Integer id, AsyncCallback<Void> callback);

    void getAllNodes(AsyncCallback<List<Node>> callback);
}
