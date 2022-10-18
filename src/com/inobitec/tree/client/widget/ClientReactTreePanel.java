package com.inobitec.tree.client.widget;

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
    public final TreeServiceAsync treeService = GWT.create(TreeService.class);

    private void build() {
        node = new Node();
        verticalPanel = new VerticalPanel();
        headingElement = new Label(MAIN_HEADER);
        treeAndSelectedHorizontalPanel = new HorizontalPanel();
        treeTable = new TreeTable(TREE);
        selectedTable = new SelectedTable(SELECTED);
        crudPanel = new CrudPanel(CRUD);
        crudDialogBox = new CrudDialogBox();
        allNodesPanel = new AllNodesPanel(AN_HEADER, AN_TABLE);
    }

    private void refreshSelectionTable() {
        treeTable.setCommand(new Command() {

            @Override
            public void bindCommand() {
                selectedId = treeTable.getIdFromUserObj();
                selectedTable.setContent(treeTable.getUserObj());
            }
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
                                // TODO Auto-generated method stub
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
            }
        });

        // DELETE BUTTON
        crudPanel.addDeleteButtonClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                crudDialogBox.showDeleteWindow();
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
        refreshSelectionTable();
        initWidget(verticalPanel);
    }

}
