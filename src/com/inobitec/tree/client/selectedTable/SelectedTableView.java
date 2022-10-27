package com.inobitec.tree.client.selectedTable;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inobitec.tree.client.widget.Fields;
import com.inobitec.tree.shared.model.Node;

public class SelectedTableView extends Composite implements SelectedTableDisplay {

    private static final String SELECTED = "Selected: ";

    private FlexTable flexTable;
    private Label selectedLabel;
    private VerticalPanel selectedVerticalPanel;
    private VerticalPanel wrapperVerticalPanel;

    public SelectedTableView(String style, String wrapperStyle) {
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
        flexTable.setText(Fields.FIRST_ROW, Fields.FIRST_COL, Fields.ID);
        flexTable.setText(Fields.SECOND_ROW, Fields.FIRST_COL, Fields.PARENT_ID);
        flexTable.setText(Fields.THIRD_ROW, Fields.FIRST_COL, Fields.NAME);
        flexTable.setText(Fields.FOURTH_ROW, Fields.FIRST_COL, Fields.IP);
        flexTable.setText(Fields.FIFTH_ROW, Fields.FIRST_COL, Fields.PORT);

        wrapperVerticalPanel.add(flexTable);
        selectedVerticalPanel.add(wrapperVerticalPanel);
    }

    @Override
    public void setNodeData(Node node) {
        flexTable.setText(Fields.FIRST_ROW, Fields.SECOND_COL, String.valueOf(node.getId()));
        int parentId = node.getParentId();
        if (parentId == Fields.EMPTY_ID) {
            flexTable.setText(Fields.SECOND_ROW, Fields.SECOND_COL, Fields.EMPTY_SYMBOL);
        } else {
            flexTable.setText(Fields.SECOND_ROW, Fields.SECOND_COL, String.valueOf(parentId));
        }

        flexTable.setText(Fields.THIRD_ROW, Fields.SECOND_COL, node.getName());
        flexTable.setText(Fields.FOURTH_ROW, Fields.SECOND_COL, node.getIp());
        flexTable.setText(Fields.FIFTH_ROW, Fields.SECOND_COL, node.getPort());
    }

    @Override
    public void clearData() {

        flexTable.removeCell(Fields.FIRST_ROW, Fields.SECOND_COL);
        flexTable.removeCell(Fields.SECOND_ROW, Fields.SECOND_COL);
        flexTable.removeCell(Fields.THIRD_ROW, Fields.SECOND_COL);
        flexTable.removeCell(Fields.FOURTH_ROW, Fields.SECOND_COL);
        flexTable.removeCell(Fields.FIFTH_ROW, Fields.SECOND_COL);

    }
}
