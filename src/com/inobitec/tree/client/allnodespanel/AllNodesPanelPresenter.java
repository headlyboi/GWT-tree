package com.inobitec.tree.client.allnodespanel;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.inobitec.tree.client.TreeProject;
import com.inobitec.tree.client.event.EventBus;
import com.inobitec.tree.client.event.UpdateAllNodesEvent;
import com.inobitec.tree.client.event.handler.UpdateAllNodesHandler;
import com.inobitec.tree.shared.model.Node;

public class AllNodesPanelPresenter {

    private AllNodesPanelDisplay view;

    public AllNodesPanelPresenter(AllNodesPanelDisplay view) {
        this.view = view;
        bindUpdateAllNodesHandler();
        getAllNodes();
    }

    private void getAllNodes() {
        TreeProject.treeService.getAllNodes(new AsyncCallback<List<Node>>() {

            @Override
            public void onSuccess(List<Node> listNode) {
                view.setAllNodesTable(listNode);
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }

    private void bindUpdateAllNodesHandler() {
        EventBus.getInstance().addHandler(UpdateAllNodesEvent.TYPE, new UpdateAllNodesHandler() {

            @Override
            public void onUpdateAllNodes(UpdateAllNodesEvent event) {
                getAllNodes();
            }
        });
    }

    public void go(HasWidgets container) {
        container.add(view.asWidget());
    }
}
