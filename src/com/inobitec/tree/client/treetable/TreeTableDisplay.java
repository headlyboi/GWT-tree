package com.inobitec.tree.client.treetable;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.inobitec.tree.client.event.command.SelectedNodeCommand;
import com.inobitec.tree.shared.model.Node;

public interface TreeTableDisplay extends IsWidget {

    void setAllRootItems(List<Node> nodeList);

    void setAllChildItems(List<Node> nodeList);

    void clearAllRoodAndChildItems();

    Node getSelectedNode();
    
    void setSelectedNodeCommand(SelectedNodeCommand selectedNodeCommand);
}
