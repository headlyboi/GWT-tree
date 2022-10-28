package com.inobitec.tree.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inobitec.tree.client.allnodespanel.AllNodesPanelPresenter;
import com.inobitec.tree.client.allnodespanel.AllNodesPanelView;
import com.inobitec.tree.client.crudpanel.CrudPanelPresenter;
import com.inobitec.tree.client.crudpanel.CrudPanelView;
import com.inobitec.tree.client.event.command.ChildCommand;import com.inobitec.tree.client.event.command.EditCommand;
import com.inobitec.tree.client.event.command.RootCommand;
import com.inobitec.tree.client.event.command.SelectedNodeCommand;
import com.inobitec.tree.client.selectedTable.SelectedTablePresenter;
import com.inobitec.tree.client.selectedTable.SelectedTableView;
import com.inobitec.tree.client.treetable.TreeTablePresenter;
import com.inobitec.tree.client.treetable.TreeTableView;
import com.inobitec.tree.shared.model.Node;

public class PanelMVC extends Composite {

    private static final String STYLE_MAIN_HEADER = "Client React Tree Panel";
    private static final String STYLE_TREE = "tree-table";
    private static final String STYLE_SELECTED = "selected-table";
    private static final String WRAPPER_SELECTED = "wrapper-selected";
    private static final String STYLE_CRUD = "crud";
    private static final String STYLE_ALL_NODES_HEADER = "all-nodes";
    private static final String WRAPPER_AN_TABLE = "wrapper-all-nodes";

    private Label headingElement;
    private VerticalPanel verticalPanel;
    private HorizontalPanel treeAndSelectedHorizontalPanel;

    private TreeTablePresenter treeTablePresenter;
    private SelectedTablePresenter selectedTablePresenter;
    private CrudPanelPresenter crudPanelPresenter;
    private AllNodesPanelPresenter allNodesPanelPresenter;

    private Node selectedNode;
    private int selectedId;

    public PanelMVC() {
        build();
        verticalPanel.add(headingElement);
        treeTablePresenter.go(treeAndSelectedHorizontalPanel);
        selectedTablePresenter.go(treeAndSelectedHorizontalPanel);
        verticalPanel.add(treeAndSelectedHorizontalPanel);
        crudPanelPresenter.go(verticalPanel);
        allNodesPanelPresenter.go(verticalPanel);

        addSelectedNodeEvent();
        addRootNodeEvent();
        addChildNodeEvent();
        addEditNodeEvent();
        update();
        initWidget(verticalPanel);
    }

    private void build() {
        headingElement = new Label(STYLE_MAIN_HEADER);
        verticalPanel = new VerticalPanel();
        treeAndSelectedHorizontalPanel = new HorizontalPanel();
        treeTablePresenter = new TreeTablePresenter(new TreeTableView(STYLE_TREE));
        selectedTablePresenter = new SelectedTablePresenter(new SelectedTableView(STYLE_SELECTED, WRAPPER_SELECTED));
        crudPanelPresenter = new CrudPanelPresenter(new CrudPanelView(STYLE_CRUD));
        allNodesPanelPresenter = new AllNodesPanelPresenter(
                new AllNodesPanelView(STYLE_ALL_NODES_HEADER, WRAPPER_AN_TABLE));
    }

    private void update() {
        treeTablePresenter.updateTree();
        allNodesPanelPresenter.updateAllNodes();
    }

    //TODO Think about commands 
    private void addRootNodeEvent() {

        crudPanelPresenter.setRootCommand(new RootCommand() {

            @Override
            public void executeRootCommand() {
                treeTablePresenter.updateTree();
                allNodesPanelPresenter.updateAllNodes();
            }
        });
    }

    private void addChildNodeEvent() {

        crudPanelPresenter.setChildCommand(new ChildCommand() {

            @Override
            public void executeChildCommand() {
                treeTablePresenter.updateTree();
                allNodesPanelPresenter.updateAllNodes();
            }
        });
    }

    private void addEditNodeEvent() {
        crudPanelPresenter.setEditCommand(new EditCommand() {

            @Override
            public void executeEditCommand() {
                treeTablePresenter.updateTree();
                allNodesPanelPresenter.updateAllNodes();
            }
            
        });
    }
    
    private void addSelectedNodeEvent() {

        treeTablePresenter.setSelectedNodeCommand(new SelectedNodeCommand() {

            @Override
            public void executeSelectedNodeCommand() {
                selectedNode = treeTablePresenter.getSelectedNode();
                selectedId = selectedNode.getId();
                selectedTablePresenter.updateNodeData(selectedNode);
                crudPanelPresenter.setSelectedId(selectedId);
            }
        });
    }
    
}
