package com.inobitec.tree.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inobitec.tree.model.Child;

public class TreeProject implements EntryPoint {
    public static final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    private HorizontalPanel horizonatalPanel = new HorizontalPanel();
    private VerticalPanel treeVerticalPanel = new VerticalPanel();
    private VerticalPanel selectedVerticalPanel = new VerticalPanel();
    private HorizontalPanel crudHorizontalPanel = new HorizontalPanel();

    private Button rootNodeButton = new Button("Add root node");
    private Button childButton = new Button("Add child");
    private Button editButton = new Button("Edit");
    private Button deleteButton = new Button("Delete");

    private Tree tree = new Tree();
    private TreeItem treeItem = new TreeItem();

    private String id;
    
    public void onModuleLoad() {

        Label headingElement = new Label("Client React Tree");
        RootPanel.get("treeAndSelected").add(headingElement);
        // TODO СДЕЛАТЬ СВОИ КЛАССЫ ДЛЯ РАМОК
        Label treeHTML = new Label("Tree: ");
        treeVerticalPanel.add(treeHTML);
        tree.setStyleName("tree");
        treeItem.setText("rootNode");
        treeItem.addTextItem("dead inside");
        tree.addItem(treeItem);
        treeVerticalPanel.add(tree);
        horizonatalPanel.add(treeVerticalPanel);
        Label selectedHTML = new Label("Selected: ");
        selectedVerticalPanel.add(selectedHTML);
        FlexTable selectedTable = new FlexTable();
        selectedTable.setStyleName("selected");
        selectedTable.setBorderWidth(1);
        selectedTable.setText(0, 0, "id");
        selectedTable.setText(1, 0, "parentId");
        selectedTable.setText(2, 0, "name");
        selectedTable.setText(3, 0, "ip");
        selectedTable.setText(4, 0, "port");

        greetingService.getChildByKey("rootNode", new AsyncCallback<Child>() {
            public void onFailure(Throwable caught) {
                // TODO: Do something with errors.
            }

            public void onSuccess(Child result) {

            }
        });
        
        selectedTable.setText(0, 1, id);
        selectedTable.setText(1, 1, "b");
        selectedTable.setText(2, 1, "c");
        selectedTable.setText(3, 1, "d");
        selectedTable.setText(4, 1, "e");
        selectedVerticalPanel.add(selectedTable);
        horizonatalPanel.add(selectedVerticalPanel);

        RootPanel.get("treeAndSelected").add(horizonatalPanel);

        Label selectedNodeHTML = new Label("selected node id = ");
        crudHorizontalPanel.setStyleName("crud");
        crudHorizontalPanel.add(rootNodeButton);
        rootNodeButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                addRootNode();
                // TODO СОЗДАТЬ ВЕТКИ ЧЕРЕЗ ОКНО
            }
        });
        crudHorizontalPanel.add(childButton);
        childButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                addChild();
                // TODO СОЗДАТЬ ПОДВЕТКИ ЧЕРЕЗ ОКНО
            }
        });
        crudHorizontalPanel.add(editButton);
        editButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                // TODO АПДЕЙТ ВЕТОК
            }
        });
        crudHorizontalPanel.add(deleteButton);
        deleteButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                // TODO УДАЛЕНИЕ ВЕТОК :)
            }
        });
        crudHorizontalPanel.add(selectedNodeHTML);
        RootPanel.get("crud").add(crudHorizontalPanel);
    }

    private void addRootNode() {
        TreeItem newTreeItem = new TreeItem();
        newTreeItem.setText("newRoot");
        ;
        newTreeItem.addTextItem("dead outside");
        tree.addItem(newTreeItem);
    }

    private void addChild() {
        tree.addTextItem("Vsem dobra i pozitiva");
    }
}
