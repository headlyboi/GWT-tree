package com.inobitec.tree.client.crudpanel;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.inobitec.tree.client.TreeProject;
import com.inobitec.tree.client.event.ChildEvent;
import com.inobitec.tree.client.event.EditEvent;
import com.inobitec.tree.client.event.EditHandler;
import com.inobitec.tree.client.event.EventBus;
import com.inobitec.tree.client.event.RootEvent;
import com.inobitec.tree.client.event.RootHandler;
import com.inobitec.tree.client.event.UpdateEvent;
import com.inobitec.tree.client.event.handler.DeleteHandler;
import com.inobitec.tree.shared.model.Node;

//FIXME Edit give a fuck
public class CrudPanelPresenter {

    private CrudPanelDisplay view;

    private SimpleEventBus eventBus = EventBus.getInstance();

    public CrudPanelPresenter(CrudPanelDisplay view) {
        this.view = view;
        build();
    }

    private void build() {
        bindRootHandler();
        bindChildHandler();
        bindEditClickHandler();
        bindDeleteClickHandler();
    }

    private void bindRootHandler() {

        eventBus.addHandler(RootEvent.TYPE, new RootHandler() {
            
            @Override
            public void executeRootHandler(RootEvent handler, Node node) {
                addRootNode(node);
            }
        });
        
    }

    private void addRootNode(Node node) {
        TreeProject.treeService.addRootNode(node, new AsyncCallback<Node>() {

            @Override
            public void onSuccess(Node node) {
                eventBus.fireEvent(new UpdateEvent());
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }

    private void bindChildHandler() {
        
        eventBus.addHandler(ChildEvent.TYPE, new com.inobitec.tree.client.event.ChildHandler() {
            
            @Override
            public void executeChildHandler(ChildEvent childEvent, Node node, int selectedId) {
               addChildNode(node, selectedId);
            }
        });
    }

    private void addChildNode(Node node, int selectedId) {
        TreeProject.treeService.addChildNode(node, selectedId, new AsyncCallback<Node>() {

            @Override
            public void onSuccess(Node node) {
                eventBus.fireEvent(new UpdateEvent());
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }

    private void bindEditClickHandler() {
        eventBus.addHandler(EditEvent.TYPE, new EditHandler() {
            
            @Override
            public void executeEditHandler(EditEvent editEvent, Node node, int selectedId) {
                editNodeById(node, selectedId);
            }
        });
    }

    private void editNodeById(Node node, int selectedId) {
        TreeProject.treeService.editNodeById(node, selectedId, new AsyncCallback<Node>() {

            @Override
            public void onSuccess(Node node) {
                eventBus.fireEvent(new UpdateEvent());
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }

    private void bindDeleteClickHandler() {
        view.setDeleteHandler(new DeleteHandler() {

            @Override
            public void executeDeleteHandler(int id) {
                deleteNode(id);
            }
        });
    }

    private void deleteNode(int selectedId) {
        TreeProject.treeService.deleteNodeById(selectedId, new AsyncCallback<Void>() {

            @Override
            public void onSuccess(Void result) {
                eventBus.fireEvent(new UpdateEvent());
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

    public void setSelectedId(Integer id) {
        view.setSelectedId(id);
    }

    public void go(HasWidgets container) {
        container.add(view.asWidget());
    }

}
