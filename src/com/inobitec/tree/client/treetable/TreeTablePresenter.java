package com.inobitec.tree.client.treetable;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.inobitec.tree.client.TreeProject;
import com.inobitec.tree.client.event.EventBus;
import com.inobitec.tree.client.event.UpdateSelectedNodeEvent;
import com.inobitec.tree.client.event.UpdateTablesEvent;
import com.inobitec.tree.client.event.command.Command;
import com.inobitec.tree.client.event.handler.UpdateTablesHandler;
import com.inobitec.tree.shared.model.Node;

public class TreeTablePresenter {

    private TreeTableDisplay view;

    public TreeTablePresenter(TreeTableDisplay view) {
        this.view = view;
        getAllNodes();
        bindUpdateCommand();
        bindUpdateTablesEvent();
    }

    private void bindUpdateCommand() {
        view.setCommand(new Command() {
            @Override
            public void executeCommand() {
                EventBus.getInstance().fireEvent(new UpdateSelectedNodeEvent(view.getSelectedNode()));
            }
        });
    }

    private void getAllNodes() {
        TreeProject.treeService.getAllNodes(new AsyncCallback<List<Node>>() {

            @Override
            public void onSuccess(List<Node> listNode) {
                view.setNodes(listNode);
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }

    private void bindUpdateTablesEvent() {
        EventBus.getInstance().addHandler(UpdateTablesEvent.TYPE, new UpdateTablesHandler() {
            @Override
            public void onUpdateTables(UpdateTablesEvent updateTablesEvent) {
                getAllNodes();
            }
        });
    }

    public void go(HasWidgets container) {
        container.add(view.asWidget());
    }
}
