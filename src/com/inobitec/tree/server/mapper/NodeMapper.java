package com.inobitec.tree.server.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.inobitec.tree.shared.model.Node;

@Mapper
public interface NodeMapper {

    Integer addRootNode(Node node);

    Node getNodeById(Integer id);

    Integer addChildNode(@Param("node") Node node, @Param("parentId") Integer parentId);

    void editNodeById(@Param("node") Node node, @Param("id") Integer id);

    void deleteNodeById(Integer id);
    
    List<Node> getAllNodes();
}
