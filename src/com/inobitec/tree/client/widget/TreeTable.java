package com.inobitec.tree.client.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inobitec.tree.shared.model.Child;

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

    public void addRootItem(Child child) {
        TreeItem item = new TreeItem();
        item.setText(child.getName());
        tree.addItem(item);
    }
}
