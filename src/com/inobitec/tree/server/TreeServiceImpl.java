package com.inobitec.tree.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.inobitec.tree.client.TreeService;
import com.inobitec.tree.shared.model.Node;

public class TreeServiceImpl extends RemoteServiceServlet
        implements TreeService {

    private static final long serialVersionUID = -4136337623971180253L;

    private static NodeCache childCache = new NodeCache();

    @Override
    public Node addRootNode(Node node){
        return childCache.addRootNode(node);
    }

    @Override
    public Node addChildNode(Node node, Integer parentId){
        return childCache.addChildNode(node, parentId);
    }

    @Override
    public Node editNode(Node node, Integer id){
        return childCache.editNode(node, id);
    }
    
    @Override
    public void deleteNode(Integer id) {
        childCache.deleteNode(id);
    }

    @Override
    public List<Node> getAllNodes(){
        return childCache.getAllNodes();
    }
}
