package com.inobitec.tree.client.selectedTable;

import com.google.gwt.user.client.ui.HasWidgets;
import com.inobitec.tree.client.event.CleanNodeEvent;
import com.inobitec.tree.client.event.EventBus;
import com.inobitec.tree.client.event.SelectedNodeEvent;
import com.inobitec.tree.client.event.handler.CleanNodeHandler;
import com.inobitec.tree.client.event.handler.SelectedNodeHandler;

public class SelectedTablePresenter{

    private SelectedTableDisplay view;
    
    public SelectedTablePresenter(SelectedTableDisplay view) {
        this.view = view;
        bindSelectedNodeHandler();
        bindCleanNodeHandler();
    }

    private void bindSelectedNodeHandler() {
        EventBus.getInstance().addHandler(SelectedNodeEvent.TYPE, new SelectedNodeHandler() {

            @Override
            public void onSelectedNode(SelectedNodeEvent selectedNodeEvent) {
                view.setNodeData(selectedNodeEvent.getSelectedNode());
            }
        });
    }
    
    private void bindCleanNodeHandler() {
        EventBus.getInstance().addHandler(CleanNodeEvent.TYPE, new CleanNodeHandler() {

            @Override
            public void onCleanNodeData(CleanNodeEvent event) {
                view.cleanData();
            }
            
        });
    }
    public void go(HasWidgets container) {
        container.add(view.asWidget());
    }
    
    
}
