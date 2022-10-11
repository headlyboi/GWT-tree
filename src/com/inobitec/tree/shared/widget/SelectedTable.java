package com.inobitec.tree.shared.widget;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inobitec.tree.shared.model.Child;

public class SelectedTable extends Composite {

    private static final String ID = "id";
    private static final String PARENT_ID = "parentId";
    private static final String NAME = "name";
    private static final String IP = "ip";
    private static final String PORT = "port";

    private static final String SELECTED = "Selected: ";

    private FlexTable flexTable = new FlexTable();

    public SelectedTable(String style) {
        VerticalPanel selectedVerticalPanel = new VerticalPanel();
        Label selectedHTML = new Label(SELECTED);
        selectedVerticalPanel.add(selectedHTML);

        flexTable.setStyleName(style);
        flexTable.setBorderWidth(1);
        flexTable.setText(0, 0, ID);
        flexTable.setText(1, 0, PARENT_ID);
        flexTable.setText(2, 0, NAME);
        flexTable.setText(3, 0, IP);
        flexTable.setText(4, 0, PORT);

        selectedVerticalPanel.add(flexTable);
        initWidget(flexTable);
    }

    public void setTextContent(Child child) {
        String id = String.valueOf(child.getId());
        String parentId = String.valueOf(child.getParentId());
        String name = child.getName();
        String ip = child.getIp();
        String port = child.getPort();

        flexTable.setText(0, 1, id);
        flexTable.setText(1, 1, parentId);
        flexTable.setText(2, 1, name);
        flexTable.setText(3, 1, ip);
        flexTable.setText(4, 1, port);
    }
}
