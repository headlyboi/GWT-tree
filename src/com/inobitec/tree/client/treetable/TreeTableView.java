package com.inobitec.tree.client.treetable;

import java.util.Iterator;
import java.util.List;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inobitec.tree.client.event.EventBus;
import com.inobitec.tree.client.event.SelectedNodeEvent;
import com.inobitec.tree.shared.Constants;
import com.inobitec.tree.shared.model.Node;

public class TreeTableView extends Composite implements TreeTableDisplay {
    private static final String ROOT_NODE = "root-Node";
    private static final String TREE = "Tree: ";
    private static final String TOUCH = "(*)";
    private static final String UNTOUCH = "(.)";
    private Tree tree;
    private Label label;
    private VerticalPanel verticalPanel;
    private TreeItem item;

    public TreeTableView(String style) {
        build(style);
        bindSelectionHandlerOnTreeTable();
        initWidget(verticalPanel);
    }

    private void build(String style) {
        tree = new Tree();
        label = new Label(TREE);
        verticalPanel = new VerticalPanel();
        verticalPanel.add(label);
        tree.setStyleName(style);
        verticalPanel.add(tree);
    }

    private void bindSelectionHandlerOnTreeTable() {

        tree.addSelectionHandler(new SelectionHandler<TreeItem>() {

            @Override
            public void onSelection(SelectionEvent<TreeItem> event) {
                Node item = (Node) tree.getSelectedItem().getUserObject();
                String name = item.getName();
                tree.getSelectedItem().setText(name + TOUCH);
                Iterator<TreeItem> treeItemIterator = tree.treeItemIterator();
                while (treeItemIterator.hasNext()) {
                    TreeItem itemFromTable = treeItemIterator.next();
                    if (!itemFromTable.isSelected()) {
                        Node nodeFromTable = (Node) itemFromTable.getUserObject();
                        itemFromTable.setText(nodeFromTable.getName() + UNTOUCH);
                    }
                }
                EventBus.getInstance().fireEvent(new SelectedNodeEvent(getSelectedNode()));
            }
        });
    }

    private void addRootItem(Node node) {
        item = new TreeItem();
        item.setText(node.getName() + UNTOUCH);
        item.setUserObject(node);
        tree.addItem(item);
    }

    private void addChildItem(Node node) {
        item = new TreeItem();
        item.setText(node.getName() + UNTOUCH);
        item.setUserObject(node);
        int parentId = node.getParentId();
        Iterator<TreeItem> treeItemIterator = tree.treeItemIterator();
        TreeItem itemFromTable = null;
        while (treeItemIterator.hasNext()) {
            itemFromTable = treeItemIterator.next();
            Node nodeFromTable = (Node) itemFromTable.getUserObject();
            if (parentId == nodeFromTable.getId()) {
                itemFromTable.addItem(item);
                itemFromTable.addStyleName(ROOT_NODE);
                break;
            }
        }
    }

    private void clearAllRootAndChildNodes() {
        tree.clear();
    }

    private void updateTreeNodes(List<Node> nodeList) {
        for (Node node : nodeList) {
            if (node.getParentId() == Constants.EMPTY_ID) {
                addRootItem(node);
            } else if (node.getParentId() != Constants.EMPTY_ID) {
                addChildItem(node);
            }
        }
    }

    @Override
    public void setNodes(List<Node> nodeList) {
        clearAllRootAndChildNodes();
        updateTreeNodes(nodeList);
    }

    @Override
    public Node getSelectedNode() {
        return (Node) tree.getSelectedItem().getUserObject();
    }

}
