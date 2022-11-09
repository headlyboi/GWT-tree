package com.inobitec.tree.client.treetable;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.inobitec.tree.client.TreeProject;
import com.inobitec.tree.client.event.EventBus;
import com.inobitec.tree.client.event.SelectedNodeEvent;
import com.inobitec.tree.client.event.UpdateButtonsEvent;
import com.inobitec.tree.client.event.UpdateTreeEvent;
import com.inobitec.tree.client.event.command.Command;
import com.inobitec.tree.client.event.handler.UpdateTreeHandler;
import com.inobitec.tree.shared.Constants;
import com.inobitec.tree.shared.model.Node;

public class TreeTablePresenter {

    private TreeTableDisplay view;

    public TreeTablePresenter(TreeTableDisplay view) {
        this.view = view;
        getAllNodes();
        bindUpdateTreeHandler();
        bindUpdateCommand();
    }

    private void bindUpdateCommand() {
        view.setCommand(new Command() {
            @Override
            public void executeCommand() {
                EventBus.getInstance().fireEvent(new UpdateButtonsEvent(Constants.ACTIVE));
                EventBus.getInstance().fireEvent(new SelectedNodeEvent(view.getSelectedNode()));
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

    private void bindUpdateTreeHandler() {
        EventBus.getInstance().addHandler(UpdateTreeEvent.TYPE, new UpdateTreeHandler() {

            @Override
            public void onUpdateTree(UpdateTreeEvent updateEvent) {
                getAllNodes();
            }
        });
    }

    public void go(HasWidgets container) {
        container.add(view.asWidget());
    }
}
