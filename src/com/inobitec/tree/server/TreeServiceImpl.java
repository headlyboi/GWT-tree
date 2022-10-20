package com.inobitec.tree.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.inobitec.tree.client.TreeService;
import com.inobitec.tree.shared.model.Node;

public class TreeServiceImpl extends RemoteServiceServlet
        implements TreeService {

    private static final long serialVersionUID = -4136337623971180253L;

    private static NodeCache childCache = new NodeCache();

    public Node addRootNode(Node node) throws IllegalArgumentException {
        return childCache.addRootNode(node);
    }

    public Node addChildNode(Node node, Integer parentId) throws IllegalArgumentException {
        return childCache.addChildNode(node, parentId);
    }

    public Node editNode(Node node, Integer id) throws IllegalArgumentException {
        return childCache.editNode(node, id);
    }
    
    public void deleteNode(Integer id) {
        childCache.deleteNode(id);
    }

    @Override
    public List<Node> getAllNodes() throws IllegalArgumentException {
        return childCache.getAllNodes();
    }
}
