package com.inobitec.tree.client.crudpanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.inobitec.tree.client.event.ChildEvent;
import com.inobitec.tree.client.event.DeleteEvent;
import com.inobitec.tree.client.event.EditEvent;
import com.inobitec.tree.client.event.EventBus;
import com.inobitec.tree.client.event.RootEvent;
import com.inobitec.tree.client.event.command.Command;
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

    private SimpleEventBus eventBus = EventBus.getInstance();

    public CrudPanelView(String style) {
        build(style);
        bindAllButtonsClickHandler();
        initWidget(horizontalPanel);
    }

    private int getSelectedId() {
        return Integer.valueOf(selectedId);
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
                doRootClicked();
            }
        });
        childNodeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                doChildClicked();
            }
        });
        editNodeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                doEditClicked();
            }
        });
        deleteNodeButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                doDeleteClicked();
            }
        });
    }
    
    private void doRootClicked() {
        crudDialogBox.showWindow(Constants.ROOT);
        crudDialogBox.setCommand(new Command() {

            @Override
            public void executeCommand() {
                eventBus.fireEvent(new RootEvent(crudDialogBox.getNodeData()));
            }
        });
    }

    private void doChildClicked() {
        crudDialogBox.showWindow(Constants.CHILD);
        crudDialogBox.setParentIdData(getSelectedId());
        crudDialogBox.setCommand(new Command() {

            @Override
            public void executeCommand() {
                eventBus.fireEvent(new ChildEvent(crudDialogBox.getNodeData(), getSelectedId()));
            }

        });
    }

    private void doEditClicked() {
        crudDialogBox.showWindow(Constants.EDIT);
        crudDialogBox.setTextBoxData(getSelectedNode());
        crudDialogBox.setCommand(new Command() {

            @Override
            public void executeCommand() {
                eventBus.fireEvent(new EditEvent(crudDialogBox.getNodeData(), getSelectedId()));
            }

        });
    }

    private void doDeleteClicked() {
        crudDialogBox.showWindow(Constants.DELETE);
        crudDialogBox.setCommand(new Command() {

            @Override
            public void executeCommand() {
                eventBus.fireEvent(new DeleteEvent(getSelectedId()));
            }

        });
    }

    @Override
    public void setActiveButtons(boolean bool) {
        childNodeButton.setEnabled(bool);
        editNodeButton.setEnabled(bool);
        deleteNodeButton.setEnabled(bool);
    }

    @Override
    public void setSelectedId(Integer id) {

        if (id == Constants.EMPTY_ID) {
            selectedNodeLabel.setText(SELECTED_NODE_ID + Constants.EMPTY_SYMBOL);
            return;
        }
        selectedId = String.valueOf(id);
        selectedNodeLabel.setText(SELECTED_NODE_ID + selectedId);
    }

    @Override
    public void setSelectedNode(Node node) {
        selectedNode = node;
    }

    private Node getSelectedNode() {
        return selectedNode;
    }
}
