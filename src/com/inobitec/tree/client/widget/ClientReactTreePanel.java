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
import com.inobitec.tree.client.TreeService;
import com.inobitec.tree.client.TreeServiceAsync;
import com.inobitec.tree.shared.command.Command;
import com.inobitec.tree.shared.model.Node;

public class ClientReactTreePanel extends Composite {

    private static final String CRUD = "crud";
    private static final String TREE = "tree";
    private static final String SELECTED = "selected";
    private static final String MAIN_HEADER = "Client React Tree";
    private static final String AN_HEADER = "allNodes";
    private static final String AN_TABLE = "allNodesTable";
    private static final String WRAPPER_AN_TABLE = "wrapper-allNodes";
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
    private int selectedId;
    public static final TreeServiceAsync treeService = GWT.create(TreeService.class);

    private void build() {
        node = new Node();
        verticalPanel = new VerticalPanel();
        headingElement = new Label(MAIN_HEADER);
        treeAndSelectedHorizontalPanel = new HorizontalPanel();
        treeTable = new TreeTable(TREE);
        selectedTable = new SelectedTable(SELECTED, WRAPPER_SELECTED);
        crudPanel = new CrudPanel(CRUD);
        crudDialogBox = new CrudDialogBox();
        allNodesPanel = new AllNodesPanel(AN_HEADER, WRAPPER_AN_TABLE);
    }

    private void updateSelectionTable() {
        treeTable.setCommand(new Command() {

            @Override
            public void bindCommand() {
                selectedId = treeTable.getIdFromUserObj();
                selectedTable.setContent(treeTable.getUserObj());
                crudPanel.setContent(selectedId);            }
        });
    }

    private void buildCrudButtons() {
        // ROOT BUTTON
        crudPanel.addRootButtonClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                crudDialogBox.showRootWindow();

                crudDialogBox.setCommand(new Command() {

                    @Override
                    public void bindCommand() {
                        node.setName(crudDialogBox.getNameContent());
                        node.setIp(crudDialogBox.getIpContent());
                        node.setPort(crudDialogBox.getPortContent());
                        treeService.addRootNode(node, new AsyncCallback<Node>() {

                            @Override
                            public void onSuccess(Node result) {
                                treeTable.addRootItem(result);
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                // TODO Auto-generated method stub
                                Window.alert(caught.getMessage());
                            }
                        });
                    }
                });
            }
        });

        // CHILD BUTTON
        crudPanel.addChildButtonClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                crudDialogBox.showChildWindow();
                crudDialogBox.setParentIdContent(selectedId);
                crudDialogBox.setCommand(new Command() {

                    @Override
                    public void bindCommand() {
                        node.setParentId(selectedId);
                        node.setName(crudDialogBox.getNameContent());
                        node.setIp(crudDialogBox.getIpContent());
                        node.setPort(crudDialogBox.getPortContent());
                        treeService.addChildNode(node, selectedId, new AsyncCallback<Node>() {

                            @Override
                            public void onSuccess(Node result) {
                                treeTable.addChildItem(result);
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

        // EDIT BUTTON
        crudPanel.addEditButtonClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                crudDialogBox.showEditWindow();
                Node selectedNodeFromTreeTable = treeTable.getUserObj();
                crudDialogBox.setAllContent(selectedNodeFromTreeTable);

                crudDialogBox.setCommand(new Command() {

                    @Override
                    public void bindCommand() {
                        node.setName(crudDialogBox.getNameContent());
                        node.setIp(crudDialogBox.getIpContent());
                        node.setPort(crudDialogBox.getPortContent());

                        treeService.editNode(node, selectedId, new AsyncCallback<Node>() {

                            @Override
                            public void onSuccess(Node result) {
                                treeTable.editNode(result);
                                selectedTable.setContent(treeTable.getUserObj());
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

        // DELETE BUTTON TODO ПОФИКСИТЬ ДЕЛИТ
        crudPanel.addDeleteButtonClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                crudDialogBox.showDeleteWindow();
                crudDialogBox.setCommand(new Command() {

                    @Override
                    public void bindCommand() {
                        treeService.deleteNode(selectedId, new AsyncCallback<Void>() {

                            @Override
                            public void onSuccess(Void result) {
                                treeTable.deleteNode(selectedId);
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                // TODO Auto-generated method stub
                                Window.alert(caught.getMessage());
                            }
                        });
                    }
                });
            }
        });
    }

    private void buildRefreshButton() {
        allNodesPanel.addRefreshButtonClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                treeService.getAllNodes(new AsyncCallback<List<Node>>() {

                    @Override
                    public void onSuccess(List<Node> result) {
                        allNodesPanel.setTableContent(AN_TABLE, result);
                    }

                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert(caught.getMessage());

                    }
                });
            }
        });
    }

    private void updateTables() {
        treeService.getAllNodes(new AsyncCallback<List<Node>>() {

            @Override
            public void onSuccess(List<Node> result) {
               treeTable.getRootItems(result);
               treeTable.getChildItems(result);
               allNodesPanel.setTableContent(AN_TABLE, result);

            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }

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
        updateTables();
        initWidget(verticalPanel);
    }

}
