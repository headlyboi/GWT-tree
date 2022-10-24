package com.inobitec.tree.client.widget;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;

public class CrudPanel extends Composite {

    private static final String ADD_ROOT_NODE = "Add root node";
    private static final String ADD_CHILD = "Add child";
    private static final String EDIT = "Edit";
    private static final String DELETE = "Delete";
    private static final String SELECTED_NODE_ID = "selected node id = ";

    private Button rootNodeButton;
    private Button childButton;
    private Button editButton;
    private Button deleteButton;
    private Label selectedNodeLabel;
    private HorizontalPanel horizontalPanel;

    public CrudPanel(String style) {
        build();
        horizontalPanel.setStyleName(style);
        initWidget(horizontalPanel);
    }

    private void build() {
        selectedNodeLabel = new Label(SELECTED_NODE_ID);
        horizontalPanel = new HorizontalPanel();
        rootNodeButton = new Button(ADD_ROOT_NODE);
        childButton = new Button(ADD_CHILD);
        editButton = new Button(EDIT);
        deleteButton = new Button(DELETE);
        horizontalPanel.add(rootNodeButton);
        horizontalPanel.add(childButton);
        horizontalPanel.add(editButton);
        horizontalPanel.add(deleteButton);
        horizontalPanel.add(selectedNodeLabel);
    }

    public void addRootButtonClickHandler(ClickHandler clickHandler) {
        rootNodeButton.addClickHandler(clickHandler);
    }

    public void addChildButtonClickHandler(ClickHandler clickHandler) {
        childButton.addClickHandler(clickHandler);
    }

    public void addEditButtonClickHandler(ClickHandler clickHandler) {
        editButton.addClickHandler(clickHandler);
    }

    public void addDeleteButtonClickHandler(ClickHandler clickHandler) {
        deleteButton.addClickHandler(clickHandler);
    }

    public void setSelectedId(int id) {
        if (id == Fields.EMPTY_ID) {
            selectedNodeLabel.setText(SELECTED_NODE_ID);
            return;
        }
        selectedNodeLabel.setText(SELECTED_NODE_ID + id);
    }
}
