package com.inobitec.tree.client.crudpanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.inobitec.tree.client.event.handler.CrudHandler;
import com.inobitec.tree.client.widget.CrudDialogBox;
import com.inobitec.tree.shared.Constants;
import com.inobitec.tree.shared.model.Node;

public class CrudPanelView extends Composite implements CrudPanelDisplay {
    private static final String ADD_ROOT_NODE = "Add root node";
    private static final String ADD_CHILD = "Add child";
    private static final String EDIT = "Edit";
    private static final String DELETE = "Delete";
    private static final String SELECTED_NODE_ID = "selected node id = ";

    private Button rootNodeButton;
    private Button childNodeButton;
    private Button editNodeButton;
    private Button deleteNodeButton;
    private Label selectedNodeLabel;
    private CrudDialogBox crudDialogBox;
    private HorizontalPanel horizontalPanel;
    private String selectedId = Constants.EMPTY_SYMBOL;
    private Node selectedNode;

    private CrudHandler crudHandler;

    public CrudPanelView(String style) {
        build(style);
        bindAllButtonsClickHandler();
        initWidget(horizontalPanel);
    }

    private void build(String style) {
        selectedNodeLabel = new Label(SELECTED_NODE_ID);
        horizontalPanel = new HorizontalPanel();
        rootNodeButton = new Button(ADD_ROOT_NODE);
        childNodeButton = new Button(ADD_CHILD);
        editNodeButton = new Button(EDIT);
        deleteNodeButton = new Button(DELETE);
        crudDialogBox = new CrudDialogBox();
        horizontalPanel.add(rootNodeButton);
        horizontalPanel.add(childNodeButton);
        horizontalPanel.add(editNodeButton);
        horizontalPanel.add(deleteNodeButton);
        horizontalPanel.add(selectedNodeLabel);
        horizontalPanel.setStyleName(style);
    }

    private void bindAllButtonsClickHandler() {
        rootNodeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                doClicked(Constants.ROOT);
            }
        });
        childNodeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                doClicked(Constants.CHILD);
            }
        });
        editNodeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                doClicked(Constants.EDIT);
            }
        });
        deleteNodeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                doClicked(Constants.DELETE);
            }
        });
    }

    private void doClicked(String buttonName) {
        switch (buttonName) {
            case Constants.ROOT: {
                crudDialogBox.showWindow(Constants.ROOT);
                crudDialogBox.setCommand(crudHandler.executeHandler(Constants.ROOT));
                break;
            }
            case Constants.CHILD: {
                crudDialogBox.showWindow(Constants.CHILD);
                crudDialogBox.setParentIdData(getSelectedId());
                crudDialogBox.setCommand(crudHandler.executeHandler(Constants.CHILD));
                break;
            }
            case Constants.EDIT: {
                crudDialogBox.showWindow(Constants.EDIT);
                crudDialogBox.setTextBoxData(getSelectedNode());
                crudDialogBox.setCommand(crudHandler.executeHandler(Constants.EDIT));
                break;
            }
            case Constants.DELETE: {
                crudDialogBox.showWindow(Constants.DELETE);
                crudDialogBox.setCommand(crudHandler.executeHandler(Constants.DELETE));
                break;
            }
        }
    }

    @Override
    public void setActiveButtons(boolean bool) {
        childNodeButton.setEnabled(bool);
        editNodeButton.setEnabled(bool);
        deleteNodeButton.setEnabled(bool);
    }

    @Override
    public void setSelectedNode(Node node) {
        selectedNode = node;
        if (node == Constants.EMPTY_NODE) {
            selectedId = Constants.EMPTY_SYMBOL;
            selectedNodeLabel.setText(SELECTED_NODE_ID + selectedId);
            return;
        }
        Integer id = node.getId();
        if (id == Constants.EMPTY_ID) {
            selectedNodeLabel.setText(SELECTED_NODE_ID + Constants.EMPTY_SYMBOL);
            return;
        }
        selectedId = String.valueOf(id);
        selectedNodeLabel.setText(SELECTED_NODE_ID + selectedId);
    }

    @Override
    public Node getNodeData() {
        return crudDialogBox.getNodeData();
    }

    @Override
    public Integer getSelectedId() {
        return Integer.valueOf(selectedId);
    }

    @Override
    public Node getSelectedNode() {
        return selectedNode;
    }

    @Override
    public void setCrudHandler(CrudHandler crudHandler) {
        this.crudHandler = crudHandler;
    }
}
