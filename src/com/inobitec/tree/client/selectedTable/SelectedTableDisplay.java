package com.inobitec.tree.client.selectedTable;

import com.google.gwt.user.client.ui.IsWidget;
import com.inobitec.tree.shared.model.Node;

public interface SelectedTableDisplay extends IsWidget {

    void setNodeData(Node node);

    void clearData();
}
