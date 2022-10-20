package com.inobitec.tree.client.widget;

import java.util.List;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inobitec.tree.shared.model.Node;

public class AllNodesPanel extends Composite {

    private static final String ALL_NODES = "All nodes:";
    private static final String REFRESH = "Refresh";

    private Label header;
    private Button refreshButton;
    private VerticalPanel verticalPanel;
    private HorizontalPanel fieldsHorizontalPanel;
    private FlexTable flexTable;

    public AllNodesPanel(String headerStyle) {
        build(headerStyle);
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

    public void setTableContent(String style, List<Node> nodeList) {
        flexTable.removeAllRows();
        flexTable.setBorderWidth(1);
        flexTable.setStyleName(style);
        for (int i = 0; i < nodeList.size(); i++) {
            Node node = nodeList.get(i);
            if (node.equals(null)) {
                continue;
            }
            flexTable.setText(i, 0, String.valueOf(node.getId()));
            flexTable.setText(i, 1, String.valueOf(node.getParentId()));
            flexTable.setText(i, 2, node.getName());
            flexTable.setText(i, 3, node.getIp());
            flexTable.setText(i, 4, node.getPort());
        }
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
