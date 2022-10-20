package com.inobitec.tree.client.widget;

import java.util.Iterator;
import java.util.List;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inobitec.tree.shared.command.Command;
import com.inobitec.tree.shared.model.Node;

public class TreeTable extends Composite {

    private static final String TREE = "Tree: ";
    private Tree tree = new Tree();
    private Label label;
    private VerticalPanel verticalPanel;
    private TreeItem item;

    private Command command;
    // TODO СДЕЛАТЬ ОБНОВЛЕНИЕ ТАБЛИЦЫ

    private void build() {
        tree = new Tree();
        label = new Label(TREE);
        verticalPanel = new VerticalPanel();
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    private void bindTable() {
        tree.addSelectionHandler(new SelectionHandler<TreeItem>() {

            @Override
            public void onSelection(SelectionEvent<TreeItem> event) {
                command.bindCommand();
            }
        });
    }

    public TreeTable(String style) {
        build();
        verticalPanel.add(label);
        tree.setStyleName(style);
        verticalPanel.add(tree);
        bindTable();
        initWidget(verticalPanel);
    }

    public void addRootItem(Node node) {
        item = new TreeItem();
        item.setText(node.getName());
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
//        for (int i = 0; i < nodeList.size(); i++) {
//            Node node = nodeList.get(i);
//            item = new TreeItem();
//            item.setText(node.getName());
//            item.setUserObject(node);
//            Iterator<TreeItem> treeItemIterator = tree.treeItemIterator();
//            while (treeItemIterator.hasNext()) {
//                TreeItem itemFromTable = treeItemIterator.next();
//                Node nodeFromTable = (Node) itemFromTable.getUserObject();
//                if (node.getParentId() == nodeFromTable.getId()) {
//                    itemFromTable.addItem(item);
//                }
//            }
//            
//        }
        for (Node node : nodeList) {
            if (node.getParentId()!= -1) {
                addChildItem(node);
            }
        }
    }

    public void addChildItem(Node node) {
        item = new TreeItem();
        item.setText(node.getName());
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
                itemFromTable.setText(node.getName());
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

    public Node getUserObj() {
        return (Node) tree.getSelectedItem().getUserObject();
    }

    public int getIdFromUserObj() {
        Node node = getUserObj();
        return node.getId();
    }
}
