package com.inobitec.tree.server;

import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.inobitec.tree.client.TreeService;
import com.inobitec.tree.server.dao.NodeDao;
import com.inobitec.tree.server.repository.NodeRepository;
import com.inobitec.tree.shared.model.Node;

public class TreeServiceImpl extends RemoteServiceServlet
        implements TreeService {

    private static final long serialVersionUID = -4136337623971180253L;

    private static NodeRepository nodeRepository = new NodeDao();
    
    @Override
    public Node addRootNode(Node node) {
        return nodeRepository.addRootNode(node);
    }

    @Override
    public Node addChildNode(Node node, Integer parentId) {
        return nodeRepository.addChildNode(node, parentId);
    }

    @Override
    public Node editNodeById(Node node, Integer id) {
        return nodeRepository.editNodeById(node, id);
    }

    @Override
    public void deleteNodeById(Integer id) {
        nodeRepository.deleteNodeById(id);
    }

    @Override
    public List<Node> getAllNodes() {
        return nodeRepository.getAllNodes();
    }
}
