package com.inobitec.tree.client;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.inobitec.tree.shared.model.Node;

@RemoteServiceRelativePath("tree")
public interface TreeService extends RemoteService {
    Node addRootNode(Node node);

    Node addChildNode(Node node, Integer parentId) ;

    Node editNode(Node node, Integer id);

    void deleteNode(Integer id);
    
    List<Node> getAllNodes();
}
