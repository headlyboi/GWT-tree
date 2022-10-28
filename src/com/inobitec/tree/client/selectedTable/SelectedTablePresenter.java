package com.inobitec.tree.client.selectedTable;

import com.google.gwt.user.client.ui.HasWidgets;
import com.inobitec.tree.shared.model.Node;

public class SelectedTablePresenter{

    private SelectedTableDisplay view;
    
    public SelectedTablePresenter(SelectedTableDisplay view) {
        this.view = view;
    }

    public void go(HasWidgets container) {
        container.add(view.asWidget());
    }
    
    public void updateNodeData(Node node) {
        view.setNodeData(node);
    }
    
    public void clearData() {
        view.clearData();
    }
}
