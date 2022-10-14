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

public class CrudDialogBox extends Composite {

    private static final String CLOSE = "Close";
    private static final String OK = "OK";
    private DialogBox dialogBox;
    private Button closeButton = new Button(CLOSE);
    private Button okButton = new Button(OK);

    private VerticalPanel fieldPanel;
    private TextBox name;
    private TextBox ip;
    private TextBox port;
    private HorizontalPanel nameHorizontalPanel;
    private HorizontalPanel ipHorizontalPanel;
    private HorizontalPanel portHorizontalPanel;
    private HorizontalPanel buttonHorizontalPanel;
    private final Label nameLabel = new Label(Fields.NAME);
    private final Label ipLabel = new Label(Fields.IP);
    private final Label portLabel = new Label(Fields.PORT);

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public CrudDialogBox() {
        build();
        bindCloseButton(closeButton);
        bindOkButton(okButton);
        setFields();
    }

    private void build() {
        dialogBox = new DialogBox();
        dialogBox.setGlassEnabled(true);
        dialogBox.setAnimationEnabled(true);
        fieldPanel = new VerticalPanel();
        name = new TextBox();
        ip = new TextBox();
        port = new TextBox();
        nameHorizontalPanel = new HorizontalPanel();
        ipHorizontalPanel = new HorizontalPanel();
        portHorizontalPanel = new HorizontalPanel();
        buttonHorizontalPanel = new HorizontalPanel();
    }

    private void setFields() {
        nameHorizontalPanel.add(name);
        nameHorizontalPanel.add(nameLabel);
        fieldPanel.add(nameHorizontalPanel);

        portHorizontalPanel.add(port);
        portHorizontalPanel.add(portLabel);
        fieldPanel.add(portHorizontalPanel);

        ipHorizontalPanel.add(ip);
        ipHorizontalPanel.add(ipLabel);
        fieldPanel.add(ipHorizontalPanel);

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

    public void bindOkButton(Button button) {
        button.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                command.execute();
                dialogBox.hide();
            }
        });
    }

    public void show() {
        dialogBox.center();
    }

    public String getNameContent() {
        return name.getValue();
    }

    public String getIpContent() {
        return ip.getValue();
    }

    public String getPortContent() {
        return port.getValue();
    }
}
