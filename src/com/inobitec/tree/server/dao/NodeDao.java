package com.inobitec.tree.server.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionManager;
import org.mybatis.cdi.Transactional;

import com.inobitec.tree.server.mapper.NodeMapper;
import com.inobitec.tree.server.repository.NodeRepository;
import com.inobitec.tree.shared.model.Node;

public class NodeDao implements NodeRepository {

    private static final String resource = "com/inobitec/tree/server/resources/mybatis-config.xml";

    private NodeMapper nodeMapper;

    public NodeDao() {
        try {
            build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void build() throws IOException {
        Reader reader = Resources.getResourceAsReader(resource);
        SqlSessionManager sqlSession = SqlSessionManager.newInstance(reader);
        sqlSession.getConfiguration().addMapper(NodeMapper.class);
        nodeMapper = sqlSession.getMapper(NodeMapper.class);
        reader.close();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Node addRootNode(Node node) {
        nodeMapper.addRootNode(node);
        return node;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Node addChildNode(Node node, Integer parentId) {
        nodeMapper.addChildNode(node, parentId);
        return node;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Node editNodeById(Node node, Integer id) {
        nodeMapper.editNodeById(node, id);
        return node;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteNodeById(Integer id) {
        nodeMapper.deleteNodeById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Node> getAllNodes() {
        List<Node> nodeList = Collections.emptyList();
        nodeList = nodeMapper.getAllNodes();
        return nodeList;
    }
}
