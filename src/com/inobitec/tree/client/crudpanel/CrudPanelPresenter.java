package com.inobitec.tree.client.crudpanel;

import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.inobitec.tree.client.TreeProject;
import com.inobitec.tree.client.event.ChildEvent;
import com.inobitec.tree.client.event.DeleteEvent;
import com.inobitec.tree.client.event.EditEvent;
import com.inobitec.tree.client.event.EventBus;
import com.inobitec.tree.client.event.RootEvent;
import com.inobitec.tree.client.event.UpdateAllNodesEvent;
import com.inobitec.tree.client.event.UpdateTreeEvent;
import com.inobitec.tree.client.event.handler.ChildHandler;
import com.inobitec.tree.client.event.handler.DeleteHandler;
import com.inobitec.tree.client.event.handler.EditHandler;
import com.inobitec.tree.client.event.handler.RootHandler;
import com.inobitec.tree.shared.Constants;
import com.inobitec.tree.shared.model.Node;

public class CrudPanelPresenter {

    private CrudPanelDisplay view;

    private SimpleEventBus eventBus = EventBus.getInstance();

    public CrudPanelPresenter(CrudPanelDisplay view) {
        this.view = view;
        bindHandlers();
        view.setActiveButtons(false);
    }

    private void bindHandlers() {

        eventBus.addHandler(RootEvent.TYPE, new RootHandler() {

            @Override
            public void executeRootHandler(RootEvent handler, Node node) {
                addRootNode(node);
            }
        });

        eventBus.addHandler(ChildEvent.TYPE, new ChildHandler() {

            @Override
            public void executeChildHandler(ChildEvent childEvent, Node node, Integer selectedId) {
                addChildNode(node, selectedId);
            }
        });

        eventBus.addHandler(EditEvent.TYPE, new EditHandler() {

            @Override
            public void executeEditHandler(EditEvent editEvent, Node node, Integer selectedId) {
                editNodeById(node, selectedId);
            }
        });

        eventBus.addHandler(DeleteEvent.TYPE, new DeleteHandler() {

            @Override
            public void executeDeleteHandler(DeleteEvent deleteEvent, Integer id) {
                deleteNodeById(id);
            }
        });
    }

    private void addRootNode(Node node) {
        TreeProject.treeService.addRootNode(node, new AsyncCallback<Node>() {

            @Override
            public void onSuccess(Node node) {
                setSelectedId(Constants.EMPTY_ID);
                eventBus.fireEvent(new UpdateTreeEvent());
                eventBus.fireEvent(new UpdateAllNodesEvent());
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }

    private void addChildNode(Node node, Integer selectedId) {
        TreeProject.treeService.addChildNode(node, selectedId, new AsyncCallback<Node>() {

            @Override
            public void onSuccess(Node node) {
                setSelectedId(Constants.EMPTY_ID);
                eventBus.fireEvent(new UpdateTreeEvent());
                eventBus.fireEvent(new UpdateAllNodesEvent());
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }


    private void editNodeById(Node node, Integer selectedId) {
        TreeProject.treeService.editNodeById(node, selectedId, new AsyncCallback<Node>() {

            @Override
            public void onSuccess(Node node) {
                setSelectedId(Constants.EMPTY_ID);
                eventBus.fireEvent(new UpdateTreeEvent());
                eventBus.fireEvent(new UpdateAllNodesEvent());
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }

    private void deleteNodeById(Integer selectedId) {
        TreeProject.treeService.deleteNodeById(selectedId, new AsyncCallback<Void>() {

            @Override
            public void onSuccess(Void result) {
                setSelectedId(Constants.EMPTY_ID);
                eventBus.fireEvent(new UpdateTreeEvent());
                eventBus.fireEvent(new UpdateAllNodesEvent());
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }

    public void setSelectedId(Integer id) {
        view.setSelectedId(id);
    }

    public void go(HasWidgets container) {
        container.add(view.asWidget());
    }

}
