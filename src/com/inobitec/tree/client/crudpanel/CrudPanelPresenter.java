package com.inobitec.tree.client.crudpanel;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.inobitec.tree.client.TreeProject;
import com.inobitec.tree.shared.command.Command;
import com.inobitec.tree.shared.handler.Handler;
import com.inobitec.tree.shared.model.Node;

public class CrudPanelPresenter implements Presenter {

    private CrudPanelDisplay view;

    private Command command;

    public CrudPanelPresenter(CrudPanelDisplay view) {
        this.view = view;
        buildRootHandler();
    }

    private void buildRootHandler() {
        view.setHandler(new Handler() {

            @Override
            public void executeHandler(Node node) {
                addRootNode(node);
            }
        });
    }


    private void addRootNode(Node node) {
        TreeProject.treeService.addRootNode(node, new AsyncCallback<Node>() {

            @Override
            public void onSuccess(Node node) {
                command.executeCommand();
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }

    private void addChildNode(Node node, int selectedId) {
        TreeProject.treeService.addChildNode(node, selectedId, new AsyncCallback<Node>() {

            @Override
            public void onSuccess(Node node) {
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }

    private void editNode(Node node, int selectedId) {
        TreeProject.treeService.editNode(node, selectedId, new AsyncCallback<Node>() {

            @Override
            public void onSuccess(Node node) {
//              treeTableView.editNode(node);
//              updateAllNodesTable();
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }

    private void deleteNode(int selectedId) {
        TreeProject.treeService.deleteNode(selectedId, new AsyncCallback<Void>() {

            @Override
            public void onSuccess(Void result) {
//              treeTableView.deleteNode(selectedId);
//              selectedId = Fields.EMPTY_ID;
//              crudPanel.setSelectedId(selectedId);
//              selectedTable.clearData();
//              updateAllNodesTable();
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }

    public void go(HasWidgets container) {
        container.add(view.asWidget());
    }
    
    public void setCommand(Command command) {
        this.command = command;
    }

}
