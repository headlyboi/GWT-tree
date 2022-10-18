package com.inobitec.tree.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.inobitec.tree.shared.model.Node;

@RemoteServiceRelativePath("tree")
public interface TreeService extends RemoteService {
    Node addRootNode(Node child) throws IllegalArgumentException;

    Node addChildNode(Node child, Integer parentId) throws IllegalArgumentException;
}
