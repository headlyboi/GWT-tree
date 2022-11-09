package com.inobitec.tree.client.selectedTable;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inobitec.tree.shared.Constants;
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
        flexTable.setText(Constants.ID_NODE_ROW, Constants.ID_NODE_COL, Constants.ID);
        flexTable.setText(Constants.PARENT_ID_NODE_ROW, Constants.ID_NODE_COL, Constants.PARENT_ID);
        flexTable.setText(Constants.NAME_NODE_ROW, Constants.ID_NODE_COL, Constants.NAME);
        flexTable.setText(Constants.IP_NODE_ROW, Constants.ID_NODE_COL, Constants.IP);
        flexTable.setText(Constants.PORT_NODE_ROW, Constants.ID_NODE_COL, Constants.PORT);

        wrapperVerticalPanel.add(flexTable);
        selectedVerticalPanel.add(wrapperVerticalPanel);
        flexTable.setStyleName(style);
        wrapperVerticalPanel.setStyleName(wrapperStyle);
    }

    @Override
    public void setNodeData(Node node) {
        if (node == null){
            cleanData();
            return;
        }
        
        flexTable.setText(Constants.ID_NODE_ROW, Constants.PARENT_ID_NODE_COL, String.valueOf(node.getId()));
        Integer parentId = node.getParentId();
        if (parentId == Constants.EMPTY_ID) {
            flexTable.setText(Constants.PARENT_ID_NODE_ROW, Constants.PARENT_ID_NODE_COL, Constants.EMPTY_SYMBOL);
        } else {
            flexTable.setText(Constants.PARENT_ID_NODE_ROW, Constants.PARENT_ID_NODE_COL, String.valueOf(parentId));
        }

        flexTable.setText(Constants.NAME_NODE_ROW, Constants.PARENT_ID_NODE_COL, node.getName());
        flexTable.setText(Constants.IP_NODE_ROW, Constants.PARENT_ID_NODE_COL, node.getIp());
        flexTable.setText(Constants.PORT_NODE_ROW, Constants.PARENT_ID_NODE_COL, node.getPort());

    }

    private void cleanData() {
        if (flexTable.getCellCount(1) != 2) {
            return;
        }
        flexTable.removeCell(Constants.ID_NODE_ROW, Constants.PARENT_ID_NODE_COL);
        flexTable.removeCell(Constants.PARENT_ID_NODE_ROW, Constants.PARENT_ID_NODE_COL);
        flexTable.removeCell(Constants.NAME_NODE_ROW, Constants.PARENT_ID_NODE_COL);
        flexTable.removeCell(Constants.IP_NODE_ROW, Constants.PARENT_ID_NODE_COL);
        flexTable.removeCell(Constants.PORT_NODE_ROW, Constants.PARENT_ID_NODE_COL);
    }
}
