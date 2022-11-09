package com.inobitec.tree.client.treetable;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.inobitec.tree.client.event.command.Command;
import com.inobitec.tree.shared.model.Node;

public interface TreeTableDisplay extends IsWidget {

    void setNodes(List<Node> nodeList);
    
    void setCommand(Command command);
    
    Node getSelectedNode();
}
