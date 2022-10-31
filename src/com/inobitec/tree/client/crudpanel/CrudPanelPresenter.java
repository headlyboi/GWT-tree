package com.inobitec.tree.client.crudpanel;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.inobitec.tree.client.TreeProject;
import com.inobitec.tree.client.event.command.ChildCommand;
import com.inobitec.tree.client.event.command.DeleteCommand;
import com.inobitec.tree.client.event.command.EditCommand;
import com.inobitec.tree.client.event.command.RootCommand;
import com.inobitec.tree.client.event.handler.ChildHandler;
import com.inobitec.tree.client.event.handler.DeleteHandler;
import com.inobitec.tree.client.event.handler.EditHandler;
import com.inobitec.tree.client.event.handler.RootHandler;
import com.inobitec.tree.shared.model.Node;

public class CrudPanelPresenter {

    private CrudPanelDisplay view;

    private RootCommand rootCommand;
    private ChildCommand childCommand;
    private EditCommand editCommand;
    private DeleteCommand deleteCommand;

    public CrudPanelPresenter(CrudPanelDisplay view) {
        this.view = view;
        buildRootHandler();
        buildChildHandler();
        buildEditClickHandler();
        buildDeleteClickHandler();
    }

    private void buildRootHandler() {
        view.setRootHandler(new RootHandler() {

            @Override
            public void executeRootHandler(Node node) {
                addRootNode(node);
            }
        });
    }

    private void addRootNode(Node node) {
        TreeProject.treeService.addRootNode(node, new AsyncCallback<Node>() {

            @Override
            public void onSuccess(Node node) {
                rootCommand.executeRootCommand();
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }

    private void buildChildHandler() {
        view.setChildHandler(new ChildHandler() {

            @Override
            public void executeChildHandler(Node node, int selectedId) {
                addChildNode(node, selectedId);
            }
        });
    }

    private void addChildNode(Node node, int selectedId) {
        TreeProject.treeService.addChildNode(node, selectedId, new AsyncCallback<Node>() {

            @Override
            public void onSuccess(Node node) {
                childCommand.executeChildCommand();
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }

    private void buildEditClickHandler() {
        view.setEditHandler(new EditHandler() {

            @Override
            public void executeEditHandler(Node node, int selectedId) {
                editNode(node, selectedId);
            }
        });
    }

    private void editNode(Node node, int selectedId) {
        TreeProject.treeService.editNode(node, selectedId, new AsyncCallback<Node>() {

            @Override
            public void onSuccess(Node node) {
                editCommand.executeEditCommand();
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }

    private void buildDeleteClickHandler() {
        view.setDeleteHandler(new DeleteHandler() {

            @Override
            public void executeDeleteHandler(int id) {
                deleteNode(id);
            }
        });
    }

    private void deleteNode(int selectedId) {
        TreeProject.treeService.deleteNode(selectedId, new AsyncCallback<Void>() {

            @Override
            public void onSuccess(Void result) {
                deleteCommand.executeDeleteCommand();
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }

    public void setActiveButtons(boolean bool) {
        view.setActiveButtons(bool);
    }
    
    public void setSelectedNode(Node selectedNode) {
        view.setSelectedNode(selectedNode);
    }

    public void setSelectedId(int id) {
        view.setSelectedId(id);
    }
    
    public void setRootCommand(RootCommand rootCommand) {
        this.rootCommand = rootCommand;
    }

    public void setChildCommand(ChildCommand childCommand) {
        this.childCommand = childCommand;
    }

    public void setEditCommand(EditCommand editCommand) {
        this.editCommand = editCommand;
    }

    public void setDeleteCommand(DeleteCommand deleteCommand) {
        this.deleteCommand = deleteCommand;
    }

    public void go(HasWidgets container) {
        container.add(view.asWidget());
    }

}
