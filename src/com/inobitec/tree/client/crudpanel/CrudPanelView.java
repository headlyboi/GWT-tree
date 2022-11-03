package com.inobitec.tree.client.crudpanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.inobitec.tree.client.event.handler.ChildHandler;
import com.inobitec.tree.client.event.handler.DeleteHandler;
import com.inobitec.tree.client.event.handler.EditHandler;
import com.inobitec.tree.client.event.handler.RootHandler;
import com.inobitec.tree.client.widget.CrudDialogBox;
import com.inobitec.tree.shared.Fields;
import com.inobitec.tree.shared.command.Command;
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
    private String selectedId = Fields.EMPTY_SYMBOL;
    private Node selectedNode;

    private RootHandler rootHandler;
    private ChildHandler childHandler;
    private EditHandler editHandler;
    private DeleteHandler deleteHandler;

    public CrudPanelView(String style) {
        build(style);
        bindRootButtonClickHandler();
        bindChildButtonClickHandler();
        bindEditButtonClickHandler();
        bindDeleteButtonClickHandler();
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

    private void bindRootButtonClickHandler() {
        rootNodeButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                doRootClicked();
            }
        });
    }

    private void doRootClicked() {
        crudDialogBox.showWindow(Fields.ROOT);
        crudDialogBox.setCommand(new Command() {

            @Override
            public void executeCommand() {
                rootHandler.executeRootHandler(crudDialogBox.getNodeData());
            }
        });
    }

    private void bindChildButtonClickHandler() {
        childNodeButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                doChildClicked();
            }
        });
    }

    private void doChildClicked() {
        crudDialogBox.showWindow(Fields.CHILD);
        crudDialogBox.setParentIdData(getSelectedId());
        crudDialogBox.setCommand(new Command() {

            @Override
            public void executeCommand() {
                childHandler.executeChildHandler(crudDialogBox.getNodeData(), getSelectedId());
            }

        });
    }

    private void bindEditButtonClickHandler() {
        editNodeButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                doEditClicked();
            }
        });
    }

    private int getSelectedId() {
        return Integer.valueOf(selectedId);
    }

    private Node getSelectedNode() {
        return selectedNode;
    }

    private void doEditClicked() {
        crudDialogBox.showWindow(Fields.EDIT);
        crudDialogBox.setTextBoxData(getSelectedNode());
        crudDialogBox.setCommand(new Command() {

            @Override
            public void executeCommand() {
                editHandler.executeEditHandler(crudDialogBox.getNodeData(), getSelectedId());
            }

        });
    }

    private void bindDeleteButtonClickHandler() {
        deleteNodeButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                crudDialogBox.showWindow(Fields.DELETE);
                crudDialogBox.setCommand(new Command() {

                    @Override
                    public void executeCommand() {
                        deleteHandler.executeDeleteHandler(getSelectedId());
                    }
                });
            }
        });
    }

    @Override
    public void setRootHandler(RootHandler rootHandler) {
        this.rootHandler = rootHandler;
    }

    @Override
    public void setChildHandler(ChildHandler childHandler) {
        this.childHandler = childHandler;

    }

    public void setEditHandler(EditHandler editHandler) {
        this.editHandler = editHandler;
    }

    @Override
    public void setDeleteHandler(DeleteHandler deleteHandler) {
        this.deleteHandler = deleteHandler;
    }

    @Override
    public void setActiveButtons(boolean bool) {
        childNodeButton.setEnabled(bool);
        editNodeButton.setEnabled(bool);
        deleteNodeButton.setEnabled(bool);
    }

    @Override
    public void setSelectedId(Integer id) {
        if (id == Fields.EMPTY_ID) {
            selectedNodeLabel.setText(SELECTED_NODE_ID + Fields.EMPTY_SYMBOL);
            return;
        }
        selectedId = String.valueOf(id);
        selectedNodeLabel.setText(SELECTED_NODE_ID + selectedId);
    }

    @Override
    public void setSelectedNode(Node node) {
        selectedNode = node;
    }
}
