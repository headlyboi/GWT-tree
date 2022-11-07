package com.inobitec.tree.client.crudpanel;

import com.google.gwt.user.client.ui.IsWidget;
import com.inobitec.tree.client.event.handler.DeleteHandler;
import com.inobitec.tree.shared.model.Node;

public interface CrudPanelDisplay extends IsWidget {

    void setDeleteHandler(DeleteHandler handler);

    void setSelectedNode(Node selectedNode);

    void setSelectedId(Integer id);

    void setActiveButtons(boolean bool);

}
