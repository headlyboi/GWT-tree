package com.inobitec.tree.client.widget;

import java.util.List;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
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

    private static final String ALL_NODES_ID = "allNodesId";
    private static final String ALL_NODES_PARENT_ID = "allNodesParentId";
    private static final String ALL_NODES_NAME = "allNodesName";
    private static final String ALL_NODES_IP = "allNodesIp";
    private static final String ALL_NODES_PORT = "allNodesPort";
    private static final String COLUMN_STYLE = "allNodesColumn";
    private static final String LABEL = "Label";
    private static final String COLUMN = "Column";

    private static Label idLabel;
    private static Label parentIdLabel;
    private static Label nameLabel;
    private static Label ipLabel;
    private static Label portLabel;

    private Label header;
    private Button refreshButton;
    private VerticalPanel verticalPanel;
    private HorizontalPanel fieldsHorizontalPanel;
    private VerticalPanel wrapperVerticalPanel;
    private FlexTable flexTable;

    public AllNodesPanel(String headerStyle, String wrapperStyle) {
        build(headerStyle);
        setFields();
        wrapperVerticalPanel.setStyleName(wrapperStyle);
        initWidget(verticalPanel);
    }

    private void build(String headerStyle) {
        header = new Label(ALL_NODES);
        header.setStyleName(headerStyle);
        refreshButton = new Button(REFRESH);
        verticalPanel = new VerticalPanel();
        wrapperVerticalPanel = new VerticalPanel();
        fieldsHorizontalPanel = new HorizontalPanel();
        flexTable = new FlexTable();

        idLabel = new Label(Fields.ID);
        parentIdLabel = new Label(Fields.PARENT_ID);
        nameLabel = new Label(Fields.NAME);
        ipLabel = new Label(Fields.IP);
        portLabel = new Label(Fields.PORT);

        idLabel.setStyleName(ALL_NODES_ID + LABEL);
        parentIdLabel.setStyleName(ALL_NODES_PARENT_ID + LABEL);
        nameLabel.setStyleName(ALL_NODES_NAME + LABEL);
        ipLabel.setStyleName(ALL_NODES_IP + LABEL);
        portLabel.setStyleName(ALL_NODES_PORT + LABEL);
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
        flexTable.getColumnFormatter().setStyleName(0, ALL_NODES_ID + COLUMN);
        flexTable.getColumnFormatter().setStyleName(1, ALL_NODES_PARENT_ID + COLUMN);
        flexTable.getColumnFormatter().setStyleName(2, ALL_NODES_NAME + COLUMN);
        flexTable.getColumnFormatter().setStyleName(3, ALL_NODES_IP + COLUMN);
        flexTable.getColumnFormatter().setStyleName(4, ALL_NODES_PORT + COLUMN);
    }

    private void setFields() {
        verticalPanel.add(header);

        wrapperVerticalPanel.add(refreshButton);
        fieldsHorizontalPanel.add(idLabel);
        fieldsHorizontalPanel.add(parentIdLabel);
        fieldsHorizontalPanel.add(nameLabel);
        fieldsHorizontalPanel.add(ipLabel);
        fieldsHorizontalPanel.add(portLabel);
        wrapperVerticalPanel.add(fieldsHorizontalPanel);
        wrapperVerticalPanel.add(flexTable);
        verticalPanel.add(wrapperVerticalPanel);
    }

    public void addRefreshButtonClickHandler(ClickHandler clickHandler) {
        refreshButton.addClickHandler(clickHandler);
    }
}
