package com.inobitec.tree.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.inobitec.tree.client.widget.CrudPanel;
import com.inobitec.tree.client.widget.SelectedTable;
import com.inobitec.tree.client.widget.TreeTable;
import com.inobitec.tree.shared.model.Child;

public class TreeProject implements EntryPoint {

    private static final String CRUD = "crud";
    private static final String TREE = "tree";
    private static final String SELECTED = "selected";
    private static final String TREE_AND_SELECTED = "treeAndSelected";
    private static final String HEADER = "Client React Tree";

    public final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    private HorizontalPanel treeAndSelectedHorizonatalPanel = new HorizontalPanel();
    private TreeTable treeTable = new TreeTable(TREE);
    private SelectedTable selectedTable = new SelectedTable(SELECTED);
    private CrudPanel crudPanel = new CrudPanel(CRUD);
    private Button button = new Button("+");

    public void onModuleLoad() {

        // TODO main panel for all panel :)
        
        // HEADER
        Label headingElement = new Label(HEADER);
        RootPanel.get(TREE_AND_SELECTED).add(headingElement);

        // TREE
        treeAndSelectedHorizonatalPanel.add(treeTable);

        // SELECTED
        treeAndSelectedHorizonatalPanel.add(selectedTable);

        RootPanel.get(TREE_AND_SELECTED).add(treeAndSelectedHorizonatalPanel);

        crudPanel.addRootButtonClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                
            }
        });
        crudPanel.addEditButtonClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                edit();
            }
        });
        RootPanel.get(CRUD).add(crudPanel);
    }



    private void edit() {
        Child child = new Child();
        child.setId(0);
        child.setName("ветка");
        child.setParentId(0);
        child.setIp("192.168.0.1");
        child.setPort("5215");
        selectedTable.updateContent(child);
    }
}
