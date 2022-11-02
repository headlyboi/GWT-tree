package com.inobitec.tree.server.dao;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.inobitec.tree.server.mapper.NodeMapper;
import com.inobitec.tree.server.repository.NodeRepository;
import com.inobitec.tree.shared.model.Node;

public class NodeDao implements NodeRepository {

    private static final String resource = "com/inobitec/tree/server/resources/mybatis-config.xml";

    private SqlSessionFactory sqlSessionFactory;

    private Reader reader;

    private NodeMapper nodeMapper;

    private List<Node> nodeListCache;

    public NodeDao() {
        try {
            build();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void build() throws IOException {
        reader = Resources.getResourceAsReader(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        sqlSessionFactory.getConfiguration().addMapper(NodeMapper.class);
        reader.close();
    }

    @Override
    public Node addRootNode(Node node) {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            nodeMapper = sqlSession.getMapper(NodeMapper.class);
            nodeMapper.addRootNode(node);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return node;
    }

    @Override
    public Node addChildNode(Node node, Integer parentId) {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            nodeMapper = sqlSession.getMapper(NodeMapper.class);
            nodeMapper.addChildNode(node, parentId);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return node;
    }

    @Override
    public Node editNodeById(Node node, Integer id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            nodeMapper = sqlSession.getMapper(NodeMapper.class);
            nodeMapper.editNodeById(node, id);
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return node;
    }

    @Override
    public void deleteNodeById(Integer id) {
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            nodeMapper = sqlSession.getMapper(NodeMapper.class);
            nodeMapper.deleteNodeById(id);
            List<Integer> keysToDelete = new ArrayList<>();
            for (Node node : nodeListCache) {
                if (node.getParentId().equals(id) || keysToDelete.contains(node.getParentId())) {
                    keysToDelete.add(node.getId());
                }
            }
            for (Integer key : keysToDelete) {
                nodeMapper.deleteNodeById(key);
            }
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
    }

    @Override
    public List<Node> getAllNodes() {

        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession();
            nodeMapper = sqlSession.getMapper(NodeMapper.class);
            nodeListCache = nodeMapper.getAllNodes();
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return nodeListCache;
    }
}
