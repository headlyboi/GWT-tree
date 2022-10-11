package com.inobitec.tree.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TreeItem;
import com.inobitec.tree.shared.widget.SelectedTable;
import com.inobitec.tree.shared.widget.TreeTable;

public class TreeProject implements EntryPoint {
    public final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    private HorizontalPanel horizonatalPanel = new HorizontalPanel();
    private HorizontalPanel crudHorizontalPanel = new HorizontalPanel();

    private Button rootNodeButton = new Button("Add root node");
    private Button childButton = new Button("Add child");
    private Button editButton = new Button("Edit");
    private Button deleteButton = new Button("Delete");


    private TreeTable treeTable = new TreeTable("tree");
    private SelectedTable selectedTable = new SelectedTable("selected");

    public void onModuleLoad() {

        Label headingElement = new Label("Client React Tree");

        RootPanel.get("treeAndSelected").add(headingElement);

        // TREE
        horizonatalPanel.add(treeTable);

        // SELECTED
        horizonatalPanel.add(selectedTable);

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
        newTreeItem.addTextItem("on new root");
        treeTable.addRootItem(newTreeItem);
    }

    private void addChild() {

    }

}
