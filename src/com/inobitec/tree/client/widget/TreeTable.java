package com.inobitec.tree.client.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TreeTable extends Composite {

    private static final String TREE = "Tree: ";
    private Tree tree = new Tree();

    public TreeTable(String style) {
        Label label = new Label(TREE);
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.add(label);
        tree.setStyleName(style);
        verticalPanel.add(tree);
        initWidget(verticalPanel);
    }

    public void addRootItem(TreeItem treeItem) {
        tree.addItem(treeItem);
    }
}
