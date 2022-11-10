package com.inobitec.tree.client.allnodespanel;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.inobitec.tree.client.TreeProject;
import com.inobitec.tree.client.event.EventBus;
import com.inobitec.tree.client.event.UpdateTablesEvent;
import com.inobitec.tree.client.event.command.Command;
import com.inobitec.tree.client.event.handler.UpdateTablesHandler;
import com.inobitec.tree.shared.model.Node;

public class AllNodesPanelPresenter {

    private AllNodesPanelDisplay view;

    public AllNodesPanelPresenter(AllNodesPanelDisplay view) {
        this.view = view;
        getAllNodes();
        bindUpdateTablesHandler();
        bindUpdateTablesCommand();
    }

    private void bindUpdateTablesCommand() {
        view.setCommand(new Command() {
            @Override
            public void executeCommand() {
                getAllNodes();
                EventBus.getInstance().fireEvent(new UpdateTablesEvent());
            }
        });
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

    private void bindUpdateTablesHandler() {
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
