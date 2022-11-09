package com.inobitec.tree.client.crudpanel;

import com.google.gwt.user.client.ui.IsWidget;
import com.inobitec.tree.client.event.handler.CrudHandler;
import com.inobitec.tree.shared.model.Node;

public interface CrudPanelDisplay extends IsWidget {

    void setSelectedNode(Node selectedNode);

    void setActiveButtons(boolean bool);

    Node getNodeData();

    Integer getSelectedId();

    Node getSelectedNode();

    void setCrudHandler(CrudHandler crudHandler);
}
