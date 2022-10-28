package com.inobitec.tree.client.crudpanel;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.inobitec.tree.client.event.handler.ChildHandler;
import com.inobitec.tree.client.event.handler.EditHandler;
import com.inobitec.tree.client.event.handler.RootHandler;
import com.inobitec.tree.client.widget.CrudDialogBox;
import com.inobitec.tree.client.widget.Fields;
import com.inobitec.tree.shared.command.Command;
import com.inobitec.tree.shared.model.Node;

public class CrudPanelView extends Composite implements CrudPanelDisplay {
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
    private CrudDialogBox crudDialogBox;
    private HorizontalPanel horizontalPanel;
    private String selectedId = Fields.EMPTY_SYMBOL;
    
    private RootHandler rootHandler;
    private ChildHandler childHandler;
    private EditHandler editHandler;
    
    public CrudPanelView(String style) {
        build();
        horizontalPanel.setStyleName(style);
        bindRootButtonClickHandler();
        bindChildButtonClickHandler();
        bindEditButtonClickHandler();
        bindDeleteButtonClickHandler();
        initWidget(horizontalPanel);
    }

    private void build() {
        selectedNodeLabel = new Label(SELECTED_NODE_ID);
        horizontalPanel = new HorizontalPanel();
        rootNodeButton = new Button(ADD_ROOT_NODE);
        childButton = new Button(ADD_CHILD);
        editButton = new Button(EDIT);
        deleteButton = new Button(DELETE);
        crudDialogBox = new CrudDialogBox();
        horizontalPanel.add(rootNodeButton);
        horizontalPanel.add(childButton);
        horizontalPanel.add(editButton);
        horizontalPanel.add(deleteButton);
        horizontalPanel.add(selectedNodeLabel);
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
        childButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                doChildClicked();
            }
        });
    }

    private void doChildClicked() {
        crudDialogBox.showWindow(ADD_CHILD);
        crudDialogBox.setParentIdData(getSelectedId());
        crudDialogBox.setCommand(new Command(){

            @Override
            public void executeCommand() {
                childHandler.executeChildHandler(crudDialogBox.getNodeData(), getSelectedId());
            }
            
        });
    }

    private void bindEditButtonClickHandler() {
        editButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                doEditClicked();
            }
        });
    }
    
    public void doEditClicked() {
        crudDialogBox.showWindow(EDIT);
        crudDialogBox.setTextBoxData(null);
        crudDialogBox.setParentIdData(getSelectedId());
        crudDialogBox.setCommand(new Command(){

            @Override
            public void executeCommand() {
                editHandler.executeEditHandler(crudDialogBox.getNodeData(), getSelectedId());
            }
            
        });
    }
    
    private void bindDeleteButtonClickHandler() {
        deleteButton.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                crudDialogBox.showWindow(Fields.DELETE);
            }
        });
    }

    @Override
    public void setSelectedId(int id) {
        if (id == Fields.EMPTY_ID) {
            selectedNodeLabel.setText(SELECTED_NODE_ID + Fields.EMPTY_SYMBOL);
            return;
        }
        selectedId = String.valueOf(id);
        selectedNodeLabel.setText(SELECTED_NODE_ID + selectedId);
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
    public int getSelectedId() {
        return Integer.valueOf(selectedId);
    }

}
