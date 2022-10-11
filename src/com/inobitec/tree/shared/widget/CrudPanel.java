package com.inobitec.tree.shared.widget;

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

    private Button rootNodeButton = new Button(ADD_ROOT_NODE);
    private Button childButton = new Button(ADD_CHILD);
    private Button editButton = new Button(EDIT);
    private Button deleteButton = new Button(DELETE);

    private HorizontalPanel horizontalPanel = new HorizontalPanel();

    public CrudPanel(String style) {
        Label selectedNodeHTML = new Label(SELECTED_NODE_ID);
        horizontalPanel.setStyleName(style);
        horizontalPanel.add(rootNodeButton);
        horizontalPanel.add(childButton);
        horizontalPanel.add(editButton);
        horizontalPanel.add(deleteButton);
        horizontalPanel.add(selectedNodeHTML);
        initWidget(horizontalPanel);
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
}
