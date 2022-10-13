package com.inobitec.tree.client.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SelectedTable extends Composite {

    private static final String SELECTED = "Selected: ";

    private FlexTable flexTable;
    private Label selectedHTML;
    private VerticalPanel selectedVerticalPanel;

    public SelectedTable(String style) {
        build();
        flexTable.setStyleName(style);
        initWidget(selectedVerticalPanel);
    }

    private void build() {
        selectedVerticalPanel = new VerticalPanel();
        flexTable = new FlexTable();
        selectedHTML = new Label(SELECTED);
        selectedVerticalPanel.add(selectedHTML);
        
        flexTable.setBorderWidth(1);
        flexTable.setText(0, 0, Fields.ID);
        flexTable.setText(1, 0, Fields.PARENT_ID);
        flexTable.setText(2, 0, Fields.NAME);
        flexTable.setText(3, 0, Fields.IP);
        flexTable.setText(4, 0, Fields.PORT);
        
        selectedVerticalPanel.add(flexTable);

    }

}
