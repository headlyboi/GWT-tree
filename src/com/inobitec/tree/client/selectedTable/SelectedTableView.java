package com.inobitec.tree.client.selectedTable;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inobitec.tree.shared.Fields;
import com.inobitec.tree.shared.model.Node;

public class SelectedTableView extends Composite implements SelectedTableDisplay {

    private static final String SELECTED = "Selected: ";

    private FlexTable flexTable;
    private Label selectedLabel;
    private VerticalPanel selectedVerticalPanel;
    private VerticalPanel wrapperVerticalPanel;

    public SelectedTableView(String style, String wrapperStyle) {
        build(style, wrapperStyle);
        initWidget(selectedVerticalPanel);
    }

    private void build(String style, String wrapperStyle) {
        wrapperVerticalPanel = new VerticalPanel();
        selectedVerticalPanel = new VerticalPanel();
        flexTable = new FlexTable();
        selectedLabel = new Label(SELECTED);
        selectedVerticalPanel.add(selectedLabel);

        flexTable.setBorderWidth(1);
        flexTable.setText(Fields.ID_NODE_ROW, Fields.ID_NODE_COL, Fields.ID);
        flexTable.setText(Fields.PARENT_ID_NODE_ROW, Fields.ID_NODE_COL, Fields.PARENT_ID);
        flexTable.setText(Fields.NAME_NODE_ROW, Fields.ID_NODE_COL, Fields.NAME);
        flexTable.setText(Fields.IP_NODE_ROW, Fields.ID_NODE_COL, Fields.IP);
        flexTable.setText(Fields.PORT_NODE_ROW, Fields.ID_NODE_COL, Fields.PORT);

        wrapperVerticalPanel.add(flexTable);
        selectedVerticalPanel.add(wrapperVerticalPanel);
        flexTable.setStyleName(style);
        wrapperVerticalPanel.setStyleName(wrapperStyle);
    }

    @Override
    public void setNodeData(Node node) {
        flexTable.setText(Fields.ID_NODE_ROW, Fields.PARENT_ID_NODE_COL, String.valueOf(node.getId()));
        int parentId = node.getParentId();
        if (parentId == Fields.EMPTY_ID) {
            flexTable.setText(Fields.PARENT_ID_NODE_ROW, Fields.PARENT_ID_NODE_COL, Fields.EMPTY_SYMBOL);
        } else {
            flexTable.setText(Fields.PARENT_ID_NODE_ROW, Fields.PARENT_ID_NODE_COL, String.valueOf(parentId));
        }

        flexTable.setText(Fields.NAME_NODE_ROW, Fields.PARENT_ID_NODE_COL, node.getName());
        flexTable.setText(Fields.IP_NODE_ROW, Fields.PARENT_ID_NODE_COL, node.getIp());
        flexTable.setText(Fields.PORT_NODE_ROW, Fields.PARENT_ID_NODE_COL, node.getPort());
    }

    @Override
    public void cleanData() {

        flexTable.removeCell(Fields.ID_NODE_ROW, Fields.PARENT_ID_NODE_COL);
        flexTable.removeCell(Fields.PARENT_ID_NODE_ROW, Fields.PARENT_ID_NODE_COL);
        flexTable.removeCell(Fields.NAME_NODE_ROW, Fields.PARENT_ID_NODE_COL);
        flexTable.removeCell(Fields.IP_NODE_ROW, Fields.PARENT_ID_NODE_COL);
        flexTable.removeCell(Fields.PORT_NODE_ROW, Fields.PARENT_ID_NODE_COL);

    }
}
