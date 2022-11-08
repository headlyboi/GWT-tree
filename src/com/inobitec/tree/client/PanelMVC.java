package com.inobitec.tree.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inobitec.tree.client.allnodespanel.AllNodesPanelPresenter;
import com.inobitec.tree.client.allnodespanel.AllNodesPanelView;
import com.inobitec.tree.client.crudpanel.CrudPanelPresenter;
import com.inobitec.tree.client.crudpanel.CrudPanelView;
import com.inobitec.tree.client.selectedTable.SelectedTablePresenter;
import com.inobitec.tree.client.selectedTable.SelectedTableView;
import com.inobitec.tree.client.treetable.TreeTablePresenter;
import com.inobitec.tree.client.treetable.TreeTableView;

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

    public PanelMVC() {
        build();
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
        verticalPanel.add(headingElement);
        treeTablePresenter.go(treeAndSelectedHorizontalPanel);
        selectedTablePresenter.go(treeAndSelectedHorizontalPanel);
        verticalPanel.add(treeAndSelectedHorizontalPanel);
        crudPanelPresenter.go(verticalPanel);
        allNodesPanelPresenter.go(verticalPanel);
    }

//    private void bindSelectedNodeEvent() {
//
//        eventBus.addHandler(SelectedNodeEvent.TYPE, new SelectedNodeHandler() {
//
//            @Override
//            public void onSelectedNode(SelectedNodeEvent selectedNodeEvent) {
////                crudPanelPresenter.setActiveButtons(true);
//                selectedNode = selectedNodeEvent.getSelectedNode();
//                selectedId = selectedNode.getId();
//                crudPanelPresenter.setSelectedId(selectedId);
////                crudPanelPresenter.setSelectedNode(selectedNode);
//            }
//        });
//    }
}
