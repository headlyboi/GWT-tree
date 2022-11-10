package com.inobitec.tree.client.selectedTable;

import com.google.gwt.user.client.ui.HasWidgets;
import com.inobitec.tree.client.event.EventBus;
import com.inobitec.tree.client.event.UpdateSelectedNodeEvent;
import com.inobitec.tree.client.event.UpdateTablesEvent;
import com.inobitec.tree.client.event.handler.UpdateSelectedNodeHandler;
import com.inobitec.tree.client.event.handler.UpdateTablesHandler;
import com.inobitec.tree.shared.Constants;

public class SelectedTablePresenter {

    private SelectedTableDisplay view;

    public SelectedTablePresenter(SelectedTableDisplay view) {
        this.view = view;
        bindSelectedNodeEvent();
    }

    private void bindSelectedNodeEvent() {
        EventBus.getInstance().addHandler(UpdateSelectedNodeEvent.TYPE, new UpdateSelectedNodeHandler() {
            @Override
            public void onUpdateSelectedNode(UpdateSelectedNodeEvent updateSelectedNodeEvent) {
                view.setNodeData(updateSelectedNodeEvent.getSelectedNode());
            }
        });

        EventBus.getInstance().addHandler(UpdateTablesEvent.TYPE, new UpdateTablesHandler() {
            @Override
            public void onUpdateTables(UpdateTablesEvent updateTablesEvent) {
                view.setNodeData(Constants.EMPTY_NODE);
            }
        });
    }

    public void go(HasWidgets container) {
        container.add(view.asWidget());
    }
}
