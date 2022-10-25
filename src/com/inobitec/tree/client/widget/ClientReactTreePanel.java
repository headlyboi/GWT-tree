package com.inobitec.tree.client.widget;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inobitec.tree.client.TreeProject;
import com.inobitec.tree.client.TreeService;
import com.inobitec.tree.client.TreeServiceAsync;
import com.inobitec.tree.shared.command.Command;
import com.inobitec.tree.shared.model.Node;

public class ClientReactTreePanel extends Composite {

    private static final String STYLE_CRUD = "crud";
    private static final String STYLE_TREE = "tree";
    private static final String STYLE_SELECTED = "selected";
    private static final String STYLE_MAIN_HEADER = "Client React Tree Panel";
    private static final String STYLE_ALL_NODES_HEADER = "all-nodes";
    private static final String STYLE_ALL_NODES_TABLE = "all-nodes-table";
    private static final String WRAPPER_AN_TABLE = "wrapper-all-nodes";
    private static final String WRAPPER_SELECTED = "wrapper-selected";

    private Label headingElement;
    private HorizontalPanel treeAndSelectedHorizontalPanel;
    private TreeTable treeTable;
    private SelectedTable selectedTable;
    private CrudPanel crudPanel;
    private VerticalPanel verticalPanel;
    private CrudDialogBox crudDialogBox;
    private AllNodesPanel allNodesPanel;
    private Node node;
    private static int selectedId = Fields.EMPTY_ID;

    private TreeServiceAsync treeService = TreeProject.treeService;
    
    public ClientReactTreePanel() {
        build();
        verticalPanel.add(headingElement);
        treeAndSelectedHorizontalPanel.add(treeTable);
        treeAndSelectedHorizontalPanel.add(selectedTable);
        verticalPanel.add(treeAndSelectedHorizontalPanel);
        verticalPanel.add(crudPanel);
        verticalPanel.add(allNodesPanel);
        buildCrudButtons();
        buildRefreshButton();
        updateSelectionTable();
        updateTree();
        initWidget(verticalPanel);
    }

    private void build() {
        node = new Node();
        verticalPanel = new VerticalPanel();
        headingElement = new Label(STYLE_MAIN_HEADER);
        treeAndSelectedHorizontalPanel = new HorizontalPanel();
        treeTable = new TreeTable(STYLE_TREE);
        selectedTable = new SelectedTable(STYLE_SELECTED, WRAPPER_SELECTED);
        crudPanel = new CrudPanel(STYLE_CRUD);
        crudDialogBox = new CrudDialogBox();
        allNodesPanel = new AllNodesPanel(STYLE_ALL_NODES_HEADER, WRAPPER_AN_TABLE);
    }

    private void updateSelectionTable() {
        treeTable.setCommand(new Command() {
            @Override
            public void executeCommand() {
                selectedId = node.getId();
                selectedTable.setNodeData(treeTable.getNode());
                crudPanel.setSelectedId(selectedId);
            }
        });
    }

    private void buildCrudButtons() {
        // ROOT BUTTON in CRUD panel
        crudPanel.addRootButtonClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                crudDialogBox.showWindow(Fields.ROOT);
                crudDialogBox.setCommand(new Command() {
                    @Override
                    public void executeCommand() {
                        node.setName(crudDialogBox.getNameData());
                        node.setIp(crudDialogBox.getIpData());
                        node.setPort(crudDialogBox.getPortData());
                        treeService.addRootNode(node, new AsyncCallback<Node>() {

                            @Override
                            public void onSuccess(Node node) {
                                treeTable.addRootItem(node);
                                updateAllNodesTable();
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                Window.alert(caught.getMessage());
                            }
                        });
                    }
                });
            }
        });

        // CHILD BUTTON in CRUD panel
        crudPanel.addChildButtonClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                if (selectedId == Fields.EMPTY_ID) {
                    return;
                }
                crudDialogBox.showWindow(Fields.CHILD);
                crudDialogBox.setParentIdData(selectedId);
                crudDialogBox.setCommand(new Command() {

                    @Override
                    public void executeCommand() {
                        node.setParentId(selectedId);
                        node.setName(crudDialogBox.getNameData());
                        node.setIp(crudDialogBox.getIpData());
                        node.setPort(crudDialogBox.getPortData());
                        treeService.addChildNode(node, selectedId, new AsyncCallback<Node>() {

                            @Override
                            public void onSuccess(Node node) {
                                treeTable.addChildItem(node);
                                updateAllNodesTable();
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                Window.alert(caught.getMessage());
                            }
                        });
                    }
                });
            }
        });

        // EDIT BUTTON in CRUD panel
        crudPanel.addEditButtonClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                if (selectedId == Fields.EMPTY_ID) {
                    return;
                }
                crudDialogBox.showWindow(Fields.EDIT);
                Node selectedNode = treeTable.getNode();
                crudDialogBox.setNodeData(selectedNode);    
                crudDialogBox.setCommand(new Command() {

                    @Override
                    public void executeCommand() {
                        node.setName(crudDialogBox.getNameData());
                        node.setIp(crudDialogBox.getIpData());
                        node.setPort(crudDialogBox.getPortData());

                        treeService.editNode(node, selectedId, new AsyncCallback<Node>() {

                            @Override
                            public void onSuccess(Node node) {
                                treeTable.editNode(node);
                                updateAllNodesTable();
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                Window.alert(caught.getMessage());
                            }
                        });
                    }
                });
            }
        });

        // DELETE BUTTON in CRUD panel
        crudPanel.addDeleteButtonClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                if (selectedId == Fields.EMPTY_ID) {
                    return;
                }
                crudDialogBox.showWindow(Fields.DELETE);
                crudDialogBox.setCommand(new Command() {

                    @Override
                    public void executeCommand() {
                        treeService.deleteNode(selectedId, new AsyncCallback<Void>() {

                            @Override
                            public void onSuccess(Void result) {
                                treeTable.deleteNode(selectedId);
                                selectedId = Fields.EMPTY_ID;
                                crudPanel.setSelectedId(selectedId);
                                selectedTable.clearData();
                                updateAllNodesTable();
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                Window.alert(caught.getMessage());
                            }
                        });
                    }
                });
            }
        });
    }

    //REFRESH BUTTON on AllNodesPanel
    private void buildRefreshButton() {
        allNodesPanel.addRefreshButtonClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                updateAllNodesTable();
            }
        });
    }
    
    private void updateAllNodesTable() {
        treeService.getAllNodes(new AsyncCallback<List<Node>>() {

            @Override
            public void onSuccess(List<Node> listNode) {
                allNodesPanel.setAllNodesTable(STYLE_ALL_NODES_TABLE, listNode);
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }
    

    private void updateTree() {
        treeService.getAllNodes(new AsyncCallback<List<Node>>() {

            @Override
            public void onSuccess(List<Node> listNode) {
                treeTable.setAllRootItems(listNode);
                treeTable.setAllChildItems(listNode);
                allNodesPanel.setAllNodesTable(STYLE_ALL_NODES_TABLE, listNode);
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }
}
