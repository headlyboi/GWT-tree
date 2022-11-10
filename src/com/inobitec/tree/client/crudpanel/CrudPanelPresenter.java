package com.inobitec.tree.client.crudpanel;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.inobitec.tree.client.TreeProject;
import com.inobitec.tree.client.event.EventBus;
import com.inobitec.tree.client.event.UpdateSelectedNodeEvent;
import com.inobitec.tree.client.event.UpdateTablesEvent;
import com.inobitec.tree.client.event.command.Command;
import com.inobitec.tree.client.event.handler.CrudHandler;
import com.inobitec.tree.client.event.handler.UpdateSelectedNodeHandler;
import com.inobitec.tree.client.event.handler.UpdateTablesHandler;
import com.inobitec.tree.shared.Constants;
import com.inobitec.tree.shared.model.Node;

public class CrudPanelPresenter {

    private CrudPanelDisplay view;

    public CrudPanelPresenter(CrudPanelDisplay view) {
        this.view = view;
        bindCrudHandler();
        bindUpdateSelectedNodeEvent();
        view.setActiveButtons(Constants.UNACTIVE);
    }

    private void bindUpdateTablesEvent() {
        EventBus.getInstance().fireEvent(new UpdateTablesEvent());
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
                                addRootNode(view.getNodeData());
                            }
                        };
                    }
                    case Constants.CHILD: {
                        return new Command() {
                            @Override
                            public void executeCommand() {
                                addChildNode(view.getNodeData(), view.getSelectedId());
                            }
                        };
                    }
                    case Constants.EDIT: {
                        return new Command() {
                            @Override
                            public void executeCommand() {
                                editNodeById(view.getNodeData(), view.getSelectedId());
                            }
                        };
                    }
                    case Constants.DELETE: {
                        return new Command() {
                            @Override
                            public void executeCommand() {
                                deleteNodeById(view.getSelectedId());
                            }
                        };
                    }
                }
                return null;
            }
        });
    }

    private void addRootNode(Node node) {
        TreeProject.treeService.addRootNode(node, new AsyncCallback<Node>() {
            @Override
            public void onSuccess(Node node) {
                bindUpdateTablesEvent();
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
                bindUpdateTablesEvent();
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
                bindUpdateTablesEvent();
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
                bindUpdateTablesEvent();
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }

    private void bindUpdateSelectedNodeEvent() {
        EventBus.getInstance().addHandler(UpdateSelectedNodeEvent.TYPE, new UpdateSelectedNodeHandler() {
            @Override
            public void onUpdateSelectedNode(UpdateSelectedNodeEvent updateSelectedNodeEvent) {
                view.setSelectedNode(updateSelectedNodeEvent.getSelectedNode());
                view.setActiveButtons(Constants.ACTIVE);
            }
        });

        EventBus.getInstance().addHandler(UpdateTablesEvent.TYPE, new UpdateTablesHandler() {
            @Override
            public void onUpdateTables(UpdateTablesEvent updateTablesEvent) {
                view.setSelectedNode(Constants.EMPTY_NODE);
                view.setActiveButtons(Constants.UNACTIVE);
            }
        });
    }

    public void go(HasWidgets container) {
        container.add(view.asWidget());
    }
}
