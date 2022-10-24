package com.inobitec.tree.client.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inobitec.tree.shared.model.Node;

public class SelectedTable extends Composite {

    private static final String SELECTED = "Selected: ";

    private FlexTable flexTable;
    private Label selectedLabel;
    private VerticalPanel selectedVerticalPanel;
    private VerticalPanel wrapperVerticalPanel;

    public SelectedTable(String style, String wrapperStyle) {
        build();
        flexTable.setStyleName(style);
        wrapperVerticalPanel.setStyleName(wrapperStyle);
        initWidget(selectedVerticalPanel);
    }

    private void build() {
        wrapperVerticalPanel = new VerticalPanel();
        selectedVerticalPanel = new VerticalPanel();
        flexTable = new FlexTable();
        selectedLabel = new Label(SELECTED);
        selectedVerticalPanel.add(selectedLabel);

        flexTable.setBorderWidth(1);
        flexTable.setText(0, 0, Fields.ID);
        flexTable.setText(1, 0, Fields.PARENT_ID);
        flexTable.setText(2, 0, Fields.NAME);
        flexTable.setText(3, 0, Fields.IP);
        flexTable.setText(4, 0, Fields.PORT);

        wrapperVerticalPanel.add(flexTable);
        selectedVerticalPanel.add(wrapperVerticalPanel);
    }

    public void setNodeData(Node node) {
        flexTable.setText(0, 1, String.valueOf(node.getId()));
        int parentId = node.getParentId();
        if (parentId == -1) {
            flexTable.setText(1, 1, Fields.EMPTY_SYMBOL);
        } else {
            flexTable.setText(1, 1, String.valueOf(parentId));
        }

        flexTable.setText(2, 1, node.getName());
        flexTable.setText(3, 1, node.getIp());
        flexTable.setText(4, 1, node.getPort());
    }

    public void clearData() {

        flexTable.removeCell(0, 1);
        flexTable.removeCell(1, 1);
        flexTable.removeCell(2, 1);
        flexTable.removeCell(3, 1);
        flexTable.removeCell(4, 1);

    }
}
