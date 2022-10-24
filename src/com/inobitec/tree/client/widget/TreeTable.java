package com.inobitec.tree.client.widget;

import java.util.Iterator;
import java.util.List;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inobitec.tree.shared.command.Command;
import com.inobitec.tree.shared.model.Node;

public class TreeTable extends Composite {

    private static final String TREE = "Tree: ";
    private static final String TOUCH = "(*)";
    private static final String UNTOUCH = "(.)";
    private Tree tree = new Tree();
    private Label label;
    private VerticalPanel verticalPanel;
    private TreeItem item;
    private Command command;

    public TreeTable(String style) {
        build();
        verticalPanel.add(label);
        tree.setStyleName(style);
        verticalPanel.add(tree);
        bindTable();
        initWidget(verticalPanel);
    }

    private void build() {
        tree = new Tree();
        label = new Label(TREE);
        verticalPanel = new VerticalPanel();
    }

    private void bindTable() {

        tree.addSelectionHandler(new SelectionHandler<TreeItem>() {

            @Override
            public void onSelection(SelectionEvent<TreeItem> event) {
                command.executeCommand();

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

            }
        });
    }

    public void addRootItem(Node node) {
        item = new TreeItem();
        item.setText(node.getName() + UNTOUCH);
        item.setUserObject(node);
        tree.addItem(item);
    }

    public void getRootItems(List<Node> nodeList) {
        for (Node node : nodeList) {
            if (node.getParentId() == -1) {
                addRootItem(node);
            }
        }
    }

    public void getChildItems(List<Node> nodeList) {
        for (Node node : nodeList) {
            if (node.getParentId() != -1) {
                addChildItem(node);
            }
        }
    }

    public void addChildItem(Node node) {
        item = new TreeItem();
        item.setText(node.getName() + UNTOUCH);
        item.setUserObject(node);

        int id = node.getParentId();
        Iterator<TreeItem> treeItemIterator = tree.treeItemIterator();
        while (treeItemIterator.hasNext()) {
            TreeItem itemFromTable = treeItemIterator.next();
            Node nodeFromTable = (Node) itemFromTable.getUserObject();
            if (id == nodeFromTable.getId()) {
                itemFromTable.addItem(item);
            }
        }
    }

    public void editNode(Node node) {
        int id = node.getId();
        Iterator<TreeItem> treeItemIterator = tree.treeItemIterator();
        while (treeItemIterator.hasNext()) {
            TreeItem itemFromTable = treeItemIterator.next();
            Node nodeFromTable = (Node) itemFromTable.getUserObject();
            if (id == nodeFromTable.getId()) {
                itemFromTable.setText(node.getName() + UNTOUCH);
                nodeFromTable.setName(node.getName());
                nodeFromTable.setIp(node.getIp());
                nodeFromTable.setPort(node.getPort());
            }
        }
    }

    public void deleteNode(Integer id) {
        Iterator<TreeItem> treeItemIterator = tree.treeItemIterator();
        while (treeItemIterator.hasNext()) {
            TreeItem itemFromTable = treeItemIterator.next();
            Node nodeFromTable = (Node) itemFromTable.getUserObject();
            if (id == nodeFromTable.getId()) {
                itemFromTable.remove();
            }
        }
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public Node getNode() {
        return (Node) tree.getSelectedItem().getUserObject();
    }

    public int getNodeId() {
        Node node = getNode();
        return node.getId();
    }

}
