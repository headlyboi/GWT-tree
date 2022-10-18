package com.inobitec.tree.client.widget;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.inobitec.tree.shared.command.Command;

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
    private Label selectedNodeHTML;
    private HorizontalPanel horizontalPanel;
    
    private Command command;
    
    public void setCommand(Command command) {
        this.command = command;
    }
    
    public CrudPanel(String style) {
        build();
        horizontalPanel.setStyleName(style);
        initWidget(horizontalPanel);
    }

    private void build() {
        selectedNodeHTML = new Label(SELECTED_NODE_ID);
        horizontalPanel = new HorizontalPanel();
        rootNodeButton = new Button(ADD_ROOT_NODE);
        childButton = new Button(ADD_CHILD);
        editButton = new Button(EDIT);
        deleteButton = new Button(DELETE);
        horizontalPanel.add(rootNodeButton);
        horizontalPanel.add(childButton);
        horizontalPanel.add(editButton);
        horizontalPanel.add(deleteButton);
        horizontalPanel.add(selectedNodeHTML);
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
