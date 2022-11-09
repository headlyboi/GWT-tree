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
import com.inobitec.tree.client.event.SelectedNodeEvent;
import com.inobitec.tree.client.event.UpdateAllNodesEvent;
import com.inobitec.tree.client.event.UpdateButtonsEvent;
import com.inobitec.tree.client.event.UpdateTreeEvent;
import com.inobitec.tree.client.event.command.Command;
import com.inobitec.tree.client.event.handler.ChildHandler;
import com.inobitec.tree.client.event.handler.CrudHandler;
import com.inobitec.tree.client.event.handler.DeleteHandler;
import com.inobitec.tree.client.event.handler.EditHandler;
import com.inobitec.tree.client.event.handler.RootHandler;
import com.inobitec.tree.client.event.handler.SelectedNodeHandler;
import com.inobitec.tree.client.event.handler.UpdateButtonsHandler;
import com.inobitec.tree.shared.Constants;
import com.inobitec.tree.shared.model.Node;

public class CrudPanelPresenter {

    private CrudPanelDisplay view;

    private SimpleEventBus eventBus = EventBus.getInstance();

    public CrudPanelPresenter(CrudPanelDisplay view) {
        this.view = view;
        bindHandlers();
        bindCrudHandler();
        view.setActiveButtons(Constants.UNACTIVE);
    }

    private void bindCrudHandler() {
        view.setCrudHandler(new CrudHandler() {

            @Override
            public Command executeHandler(String buttonName) {
                switch (buttonName) {
                    case Constants.ROOT: {
                        return new Command() {

                            @Override
                            public void executeCommand() {
                                eventBus.fireEvent(new RootEvent(view.getNodeData()));
                            }
                        };
                    }
                    case Constants.CHILD: {
                        return new Command() {
                            @Override
                            public void executeCommand() {
                                eventBus.fireEvent(new ChildEvent(view.getNodeData(), view.getSelectedId()));
                            }
                        };
                    }
                    case Constants.EDIT: {
                        return new Command() {
                            @Override
                            public void executeCommand() {
                                eventBus.fireEvent(new EditEvent(view.getNodeData(), view.getSelectedId()));
                            }
                        };
                    }
                    case Constants.DELETE: {
                        return new Command() {

                            @Override
                            public void executeCommand() {
                                eventBus.fireEvent(new DeleteEvent(view.getSelectedId()));
                            }
                        };
                    }
                }
                return null;
            }
        });
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
        eventBus.addHandler(UpdateButtonsEvent.TYPE, new UpdateButtonsHandler() {
            @Override
            public void onUpdateButtons(UpdateButtonsEvent buttonsEvent) {
                view.setActiveButtons(buttonsEvent.isActive());
            }
        });
        eventBus.addHandler(SelectedNodeEvent.TYPE, new SelectedNodeHandler() {

            @Override
            public void onSelectedNode(SelectedNodeEvent selectedNodeEvent) {
                view.setSelectedNode(selectedNodeEvent.getSelectedNode());
            }
        });
    }

    private void addRootNode(Node node) {
        TreeProject.treeService.addRootNode(node, new AsyncCallback<Node>() {

            @Override
            public void onSuccess(Node node) {
                updateAllTables();
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
                updateAllTables();
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
                updateAllTables();
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
                updateAllTables();
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }

    private void updateAllTables() {
        eventBus.fireEvent(new UpdateTreeEvent());
        eventBus.fireEvent(new UpdateAllNodesEvent());
        eventBus.fireEvent(new SelectedNodeEvent(Constants.EMPTY_NODE));
    }

    public void go(HasWidgets container) {
        container.add(view.asWidget());
    }

}
