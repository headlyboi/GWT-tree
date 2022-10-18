package com.inobitec.tree.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.inobitec.tree.client.TreeService;
import com.inobitec.tree.shared.model.Node;

public class TreeServiceImpl extends RemoteServiceServlet
        implements TreeService {

    private static final long serialVersionUID = -4136337623971180253L;

    private static NodeCache childCache = new NodeCache();

    public Node addRootNode(Node child) throws IllegalArgumentException {
        return childCache.addRootNode(child);
    }

    public Node addChildNode(Node child, Integer parentId) throws IllegalArgumentException {
        return childCache.addChildNode(child, parentId);
    }

}
