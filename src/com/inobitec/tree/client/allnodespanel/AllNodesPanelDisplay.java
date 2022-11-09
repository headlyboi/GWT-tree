package com.inobitec.tree.client.allnodespanel;

import java.util.List;

import com.google.gwt.user.client.ui.IsWidget;
import com.inobitec.tree.client.event.command.Command;
import com.inobitec.tree.shared.model.Node;

public interface AllNodesPanelDisplay extends IsWidget {

    void setAllNodesTable(List<Node> nodeList);
    
    void setCommand(Command command);
}
