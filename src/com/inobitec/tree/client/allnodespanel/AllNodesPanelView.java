package com.inobitec.tree.client.allnodespanel;

import java.util.List;

import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inobitec.tree.client.widget.Fields;
import com.inobitec.tree.shared.model.Node;

public class AllNodesPanelView extends Composite implements AllNodesPanelDisplay {
    private static final String ALL_NODES = "All nodes:";
    private static final String BUTTON_REFRESH = "Refresh";

    private static final String STYLE_ALL_NODES_ID = "all-nodes-id";
    private static final String STYLE_ALL_NODES_PARENT_ID = "all-nodes-parentId";
    private static final String STYLE_ALL_NODES_NAME = "all-nodes-name";
    private static final String STYLE_ALL_NODES_IP = "all-nodes-ip";
    private static final String STYLE_ALL_NODES_PORT = "all-nodes-port";
    private static final String STYLE_LABEL = "-Label";
    private static final String STYLE_COLUMN = "-Column";

    private static final String STYLE_ALL_NODES_TABLE = "all-nodes-table";

    private static Label idLabel;
    private static Label parentIdLabel;
    private static Label nameLabel;
    private static Label ipLabel;
    private static Label portLabel;

    private Label headerLabel;
    private Button refreshButton;
    private VerticalPanel verticalPanel;
    private HorizontalPanel fieldsHorizontalPanel;
    private VerticalPanel wrapperVerticalPanel;
    private FlexTable flexTable;

    public AllNodesPanelView(String headerStyle, String wrapperStyle) {
        build(headerStyle);
        buildFields();
        wrapperVerticalPanel.setStyleName(wrapperStyle);
        initWidget(verticalPanel);
    }

    private void build(String headerStyle) {
        headerLabel = new Label(ALL_NODES);
        headerLabel.setStyleName(headerStyle);
        refreshButton = new Button(BUTTON_REFRESH);
        verticalPanel = new VerticalPanel();
        wrapperVerticalPanel = new VerticalPanel();
        fieldsHorizontalPanel = new HorizontalPanel();
        flexTable = new FlexTable();

        idLabel = new Label(Fields.ID);
        parentIdLabel = new Label(Fields.PARENT_ID);
        nameLabel = new Label(Fields.NAME);
        ipLabel = new Label(Fields.IP);
        portLabel = new Label(Fields.PORT);

        idLabel.setStyleName(STYLE_ALL_NODES_ID + STYLE_LABEL);
        parentIdLabel.setStyleName(STYLE_ALL_NODES_PARENT_ID + STYLE_LABEL);
        nameLabel.setStyleName(STYLE_ALL_NODES_NAME + STYLE_LABEL);
        ipLabel.setStyleName(STYLE_ALL_NODES_IP + STYLE_LABEL);
        portLabel.setStyleName(STYLE_ALL_NODES_PORT + STYLE_LABEL);
    }

    private void buildFields() {
        verticalPanel.add(headerLabel);
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

    public void setAllNodesTable(List<Node> nodeList) {
        flexTable.removeAllRows();
        flexTable.setBorderWidth(1);
        flexTable.setStyleName(STYLE_ALL_NODES_TABLE);
        for (int i = 0; i < nodeList.size(); i++) {
            Node node = nodeList.get(i);
            if (node.equals(null)) {
                continue;
            }
            String parentId = String.valueOf(node.getParentId());
            if (node.getParentId() == Fields.EMPTY_ID) {
                parentId = Fields.EMPTY_SYMBOL;
            }
            flexTable.setText(i, Fields.FIRST_COL, String.valueOf(node.getId()));
            flexTable.setText(i, Fields.SECOND_COL, parentId);
            flexTable.setText(i, Fields.THIRD_COL, node.getName());
            flexTable.setText(i, Fields.FOURTH_COL, node.getIp());
            flexTable.setText(i, Fields.FIFTH_COL, node.getPort());
        }
        flexTable.getColumnFormatter().setStyleName(Fields.FIRST_COL, STYLE_ALL_NODES_ID + STYLE_COLUMN);
        flexTable.getColumnFormatter().setStyleName(Fields.SECOND_COL, STYLE_ALL_NODES_PARENT_ID + STYLE_COLUMN);
        flexTable.getColumnFormatter().setStyleName(Fields.THIRD_COL, STYLE_ALL_NODES_NAME + STYLE_COLUMN);
        flexTable.getColumnFormatter().setStyleName(Fields.FOURTH_COL, STYLE_ALL_NODES_IP + STYLE_COLUMN);
        flexTable.getColumnFormatter().setStyleName(Fields.FIFTH_COL, STYLE_ALL_NODES_PORT + STYLE_COLUMN);
    }

    public void addRefreshButtonClickHandler(ClickHandler clickHandler) {
        refreshButton.addClickHandler(clickHandler);
    }
}
