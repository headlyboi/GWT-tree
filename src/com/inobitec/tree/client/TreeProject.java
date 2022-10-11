package com.inobitec.tree.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TreeItem;
import com.inobitec.tree.shared.widget.CrudPanel;
import com.inobitec.tree.shared.widget.SelectedTable;
import com.inobitec.tree.shared.widget.TreeTable;

public class TreeProject implements EntryPoint {
    public final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    private HorizontalPanel horizonatalPanel = new HorizontalPanel();

    private TreeTable treeTable = new TreeTable("tree");
    private SelectedTable selectedTable = new SelectedTable("selected");
    private CrudPanel crudPanel = new CrudPanel("crud");
    public void onModuleLoad() {

        // HEADER
        Label headingElement = new Label("Client React Tree");
        RootPanel.get("treeAndSelected").add(headingElement);

        // TREE
        horizonatalPanel.add(treeTable);

        // SELECTED
        horizonatalPanel.add(selectedTable);

        RootPanel.get("treeAndSelected").add(horizonatalPanel);


        RootPanel.get("crud").add(crudPanel);
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
