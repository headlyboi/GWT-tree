package com.inobitec.tree.client.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inobitec.tree.shared.command.Command;
import com.inobitec.tree.shared.handler.Handler;
import com.inobitec.tree.shared.model.Node;

public class CrudDialogBox extends Composite {

    private static final String BUTTON_ROOT_NODE = "Add root Node";
    private static final String BUTTON_CHILD_NODE = "Add child";
    private static final String BUTTON_EDIT = "Edit";
    private static final String BUTTON_DELETE = "Delete";

    private static final String BUTTON_CLOSE = "Close";
    private static final String BUTTON_OK = "OK";

    private DialogBox dialogBox;
    private Button closeButton = new Button(BUTTON_CLOSE);
    private Button okButton = new Button(BUTTON_OK);

    private VerticalPanel fieldPanel;
    private TextBox idTextBox;
    private TextBox parentIdTextBox;
    private TextBox nameTextBox;
    private TextBox ipTextBox;
    private TextBox portTextBox;

    private HorizontalPanel idHorizontalPanel;
    private HorizontalPanel parentIdHorizontalPanel;
    private HorizontalPanel nameHorizontalPanel;
    private HorizontalPanel ipHorizontalPanel;
    private HorizontalPanel portHorizontalPanel;
    private HorizontalPanel buttonHorizontalPanel;
    private final Label idLabel = new Label(Fields.ID);
    private final Label parentIdLabel = new Label(Fields.PARENT_ID);
    private final Label nameLabel = new Label(Fields.NAME);
    private final Label ipLabel = new Label(Fields.IP);
    private final Label portLabel = new Label(Fields.PORT);

    private Command command;
    
    public CrudDialogBox() {
        build();
        bindCloseButton(closeButton);
        bindOkButton(okButton);
        buildFields();
    }

    private void build() {
        dialogBox = new DialogBox();
        dialogBox.setGlassEnabled(true);
        dialogBox.setAnimationEnabled(true);
        fieldPanel = new VerticalPanel();
        idTextBox = new TextBox();
        parentIdTextBox = new TextBox();
        nameTextBox = new TextBox();
        ipTextBox = new TextBox();
        portTextBox = new TextBox();
        idHorizontalPanel = new HorizontalPanel();
        parentIdHorizontalPanel = new HorizontalPanel();
        nameHorizontalPanel = new HorizontalPanel();
        ipHorizontalPanel = new HorizontalPanel();
        portHorizontalPanel = new HorizontalPanel();
        buttonHorizontalPanel = new HorizontalPanel();
    }

    private void setVisibleFields(boolean bool) {
        idHorizontalPanel.setVisible(bool);
        parentIdHorizontalPanel.setVisible(bool);
        nameHorizontalPanel.setVisible(bool);
        ipHorizontalPanel.setVisible(bool);
        portHorizontalPanel.setVisible(bool);
    }

    private void buildFields() {
        idTextBox.setEnabled(false);
        idTextBox.setReadOnly(true);
        idHorizontalPanel.add(idTextBox);
        idHorizontalPanel.add(idLabel);
        fieldPanel.add(idHorizontalPanel);

        parentIdTextBox.setEnabled(false);
        parentIdTextBox.setReadOnly(true);
        parentIdHorizontalPanel.add(parentIdTextBox);
        parentIdHorizontalPanel.add(parentIdLabel);
        fieldPanel.add(parentIdHorizontalPanel);

        nameHorizontalPanel.add(nameTextBox);
        nameHorizontalPanel.add(nameLabel);
        fieldPanel.add(nameHorizontalPanel);

        ipHorizontalPanel.add(ipTextBox);
        ipHorizontalPanel.add(ipLabel);
        fieldPanel.add(ipHorizontalPanel);

        portHorizontalPanel.add(portTextBox);
        portHorizontalPanel.add(portLabel);
        fieldPanel.add(portHorizontalPanel);

        buttonHorizontalPanel.add(closeButton);
        buttonHorizontalPanel.add(okButton);
        fieldPanel.add(buttonHorizontalPanel);

        dialogBox.setWidget(fieldPanel);
    }

    private void bindCloseButton(Button button) {
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                dialogBox.hide();
            }
        });
    }

    private void bindOkButton(Button button) {
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                command.executeCommand();
                dialogBox.hide();
            }
        });
    }

    private void clearTextBoxData() {
        idTextBox.setValue(Fields.EMPTY_SYMBOL);
        parentIdTextBox.setValue(Fields.EMPTY_SYMBOL);
        nameTextBox.setValue(Fields.EMPTY_SYMBOL);
        ipTextBox.setValue(Fields.EMPTY_SYMBOL);
        portTextBox.setValue(Fields.EMPTY_SYMBOL);
    }

    public void showWindow(String buttonName) {
        clearTextBoxData();
        switch (buttonName) {
            case Fields.ROOT: {
                setVisibleFields(true);
                dialogBox.setText(BUTTON_ROOT_NODE);
                break;
            }
            case Fields.CHILD: {
                setVisibleFields(true);
                dialogBox.setText(BUTTON_CHILD_NODE);
                break;

            }
            case Fields.EDIT: {
                setVisibleFields(true);
                dialogBox.setText(BUTTON_EDIT);
                break;

            }

            case Fields.DELETE: {
                setVisibleFields(false);
                dialogBox.setText(BUTTON_DELETE);
                break;

            }
        }
        dialogBox.center();
    }

    /**
     * 
     * @return return node without id and parentId, if there are fields are empty
     */
    public Node getNodeData() {
        Node node = new Node();
        if (getIdData() != Fields.EMPTY_SYMBOL) {
            node.setId(Integer.valueOf(getIdData()));
        }
        if(getParentIdData() != Fields.EMPTY_SYMBOL) {
            node.setParentId(Integer.valueOf(getParentIdData()));
        }
        
        node.setName(getNameData());
        node.setIp(getIpData());
        node.setPort(getPortData());
        return node;
    }

    public String getNameData() {
        return nameTextBox.getValue();
    }

    public String getIpData() {
        return ipTextBox.getValue();
    }

    public String getPortData() {
        return portTextBox.getValue();
    }

    public String getIdData() {
        return idTextBox.getValue();
    }

    public String getParentIdData() {
        return parentIdTextBox.getValue();
    }

    public void setParentIdData(int parentId) {
        this.parentIdTextBox.setValue(String.valueOf(parentId));
    }

    public void setTextBoxData(Node node) {
        this.idTextBox.setValue(String.valueOf(node.getId()));
        this.nameTextBox.setValue(node.getName());
        this.ipTextBox.setValue(node.getIp());
        this.portTextBox.setValue(node.getPort());

        int parentIdFromNode = node.getParentId();
        if (parentIdFromNode == -1) {
            this.parentIdTextBox.setText(Fields.EMPTY_SYMBOL);
            return;
        }
        setParentIdData(parentIdFromNode);
    }

    public void setCommand(Command command) {
        this.command = command;
    }

}
