package com.inobitec.tree.client.allnodespanel;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inobitec.tree.client.event.command.Command;
import com.inobitec.tree.shared.Constants;
import com.inobitec.tree.shared.model.Node;

public class AllNodesPanelView extends Composite implements AllNodesPanelDisplay {
    private static final String ALL_NODES = "All nodes:";
    private static final String BUTTON_REFRESH = "Refresh";

    private static final String STYLE_ALL_NODES = "all-nodes";
    private static final String DASH = "-";
    private static final String STYLE_ALL_NODES_ID = STYLE_ALL_NODES + DASH + "id";
    private static final String STYLE_ALL_NODES_PARENT_ID = STYLE_ALL_NODES + DASH + "parentId";
    private static final String STYLE_ALL_NODES_NAME = STYLE_ALL_NODES + DASH + "name";
    private static final String STYLE_ALL_NODES_IP = STYLE_ALL_NODES + DASH + "ip";
    private static final String STYLE_ALL_NODES_PORT = STYLE_ALL_NODES + DASH + "port";
    private static final String STYLE_LABEL = "-Label";
    private static final String STYLE_COLUMN = "-Column";

    private static final String STYLE_ALL_NODES_TABLE = STYLE_ALL_NODES + DASH + "table";

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

    private Command command;

    public AllNodesPanelView(String headerStyle, String wrapperStyle) {
        build(headerStyle, wrapperStyle);
        buildFields();
        bindRefreshButton();
        initWidget(verticalPanel);
    }

    private void build(String headerStyle, String wrapperStyle) {
        headerLabel = new Label(ALL_NODES);
        headerLabel.setStyleName(headerStyle);
        refreshButton = new Button(BUTTON_REFRESH);
        verticalPanel = new VerticalPanel();
        wrapperVerticalPanel = new VerticalPanel();
        wrapperVerticalPanel.setStyleName(wrapperStyle);
        fieldsHorizontalPanel = new HorizontalPanel();
        flexTable = new FlexTable();
        idLabel = new Label(Constants.ID);
        parentIdLabel = new Label(Constants.PARENT_ID);
        nameLabel = new Label(Constants.NAME);
        ipLabel = new Label(Constants.IP);
        portLabel = new Label(Constants.PORT);

        idLabel.setStyleName(STYLE_ALL_NODES_ID + STYLE_LABEL);
        parentIdLabel.setStyleName(STYLE_ALL_NODES_PARENT_ID + STYLE_LABEL);
        nameLabel.setStyleName(STYLE_ALL_NODES_NAME + STYLE_LABEL);
        ipLabel.setStyleName(STYLE_ALL_NODES_IP + STYLE_LABEL);
        portLabel.setStyleName(STYLE_ALL_NODES_PORT + STYLE_LABEL);
        flexTable.setBorderWidth(1);
        flexTable.setStyleName(STYLE_ALL_NODES_TABLE);
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

    private void bindRefreshButton() {
        refreshButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                command.executeCommand();
            }
        });
    }

    @Override
    public void setAllNodesTable(List<Node> nodeList) {
        flexTable.removeAllRows();
        for (int i = 0; i < nodeList.size(); i++) {
            Node node = nodeList.get(i);
            if (node.equals(null)) {
                continue;
            }

            String parentId = (node.getParentId() == Constants.EMPTY_ID) ? parentId = Constants.EMPTY_SYMBOL
                    : String.valueOf(node.getParentId());

            flexTable.setText(i, Constants.ID_NODE_COL, String.valueOf(node.getId()));
            flexTable.setText(i, Constants.PARENT_ID_NODE_COL, parentId);
            flexTable.setText(i, Constants.NAME_NODE_COL, node.getName());
            flexTable.setText(i, Constants.IP_NODE_COL, node.getIp());
            flexTable.setText(i, Constants.PORT_NODE_COL, node.getPort());
        }
        flexTable.getColumnFormatter().setStyleName(Constants.ID_NODE_COL, STYLE_ALL_NODES_ID + STYLE_COLUMN);
        flexTable.getColumnFormatter().setStyleName(Constants.PARENT_ID_NODE_COL,
                STYLE_ALL_NODES_PARENT_ID + STYLE_COLUMN);
        flexTable.getColumnFormatter().setStyleName(Constants.NAME_NODE_COL, STYLE_ALL_NODES_NAME + STYLE_COLUMN);
        flexTable.getColumnFormatter().setStyleName(Constants.IP_NODE_COL, STYLE_ALL_NODES_IP + STYLE_COLUMN);
        flexTable.getColumnFormatter().setStyleName(Constants.PORT_NODE_COL, STYLE_ALL_NODES_PORT + STYLE_COLUMN);
    }

    @Override
    public void setCommand(Command command) {
        this.command = command;
    }

}
