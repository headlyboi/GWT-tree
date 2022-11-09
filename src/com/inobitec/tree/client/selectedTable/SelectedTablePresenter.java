package com.inobitec.tree.client.selectedTable;

import com.google.gwt.user.client.ui.HasWidgets;
import com.inobitec.tree.client.event.EventBus;
import com.inobitec.tree.client.event.SelectedNodeEvent;
import com.inobitec.tree.client.event.handler.SelectedNodeHandler;

public class SelectedTablePresenter{

    private SelectedTableDisplay view;
    
    public SelectedTablePresenter(SelectedTableDisplay view) {
        this.view = view;
        bindSelectedNodeHandler();
    }

    private void bindSelectedNodeHandler() {
        EventBus.getInstance().addHandler(SelectedNodeEvent.TYPE, new SelectedNodeHandler() {

            @Override
            public void onSelectedNode(SelectedNodeEvent selectedNodeEvent) {
                view.setNodeData(selectedNodeEvent.getSelectedNode());
            }
        });
    }
    
    public void go(HasWidgets container) {
        container.add(view.asWidget());
    }
}
