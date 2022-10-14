package com.inobitec.tree.client.widget;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class AllNodesPanel extends Composite {

    private static final String ALL_NODES = "All nodes:";
    private static final String REFRESH = "Refresh";

    private Label header;
    private Button refreshButton;
    private VerticalPanel verticalPanel;
    private HorizontalPanel fieldsHorizontalPanel;
    private FlexTable flexTable;

    public AllNodesPanel(String headerStyle, String tableStyle) {
        build(headerStyle);
        setTable(tableStyle);
        setFields();

        initWidget(verticalPanel);
    }

    private void build(String headerStyle) {
        header = new Label(ALL_NODES);
        header.setStyleName(headerStyle);
        refreshButton = new Button(REFRESH);
        verticalPanel = new VerticalPanel();
        fieldsHorizontalPanel = new HorizontalPanel();
        flexTable = new FlexTable();
    }

    private void setTable(String style) {
        flexTable.setBorderWidth(1);
        flexTable.setStyleName(style);
        flexTable.setText(0, 0, "Листья");
        flexTable.setText(0, 1, "Падают");
        flexTable.setText(0, 2, "С");
        flexTable.setText(0, 3, "Дуба");
        flexTable.setText(0, 4, "Ясеня");
    }

    private void setFields() {
        verticalPanel.add(header);
        verticalPanel.add(refreshButton);
        fieldsHorizontalPanel.add(Fields.idLabel);
        fieldsHorizontalPanel.add(Fields.parentIdLabel);
        fieldsHorizontalPanel.add(Fields.nameLabel);
        fieldsHorizontalPanel.add(Fields.ipLabel);
        fieldsHorizontalPanel.add(Fields.portLabel);
        verticalPanel.add(fieldsHorizontalPanel);
        verticalPanel.add(flexTable);
    }

    public void addRefreshButtonClickHandler(ClickHandler clickHandler) {
        refreshButton.addClickHandler(clickHandler);
    }
}
