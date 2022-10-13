package com.inobitec.tree.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inobitec.tree.client.GreetingService;
import com.inobitec.tree.client.GreetingServiceAsync;
import com.inobitec.tree.shared.model.Child;

public class CrudDialogBox extends Composite {

    private static final String CLOSE = "Close";
    private static final String OK = "OK";
    private DialogBox dialogBox;
    private Button close = new Button(CLOSE);
    private Button ok = new Button(OK);

    private VerticalPanel fieldPanel;
    private TextBox name;
    private TextBox ip;
    private TextBox port;
    private HorizontalPanel nameHorizontalPanel;
    private HorizontalPanel ipHorizontalPanel;
    private HorizontalPanel portHorizontalPanel;
    private HorizontalPanel buttonHorizontalPanel;
    private final Label nameLabel = new Label("name");
    private final Label ipLabel = new Label("ip");
    private final Label portLabel = new Label("port");
    // TODO УБРАТЬ ЭТУ ХЕРНЮ
    public final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
    private final Child child;
    public CrudDialogBox() {
        this.child = new Child();
        build();
        bindCloseButton(close);
        bindOkButton(ok);
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

        buttonHorizontalPanel.add(close);
        buttonHorizontalPanel.add(ok);
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
                child.setName(name.getValue());
                child.setIp(ip.getValue());
                child.setPort(port.getValue());
                dialogBox.hide();
                
                //TODO ПЕРЕДЕЛАТЬ ЧЕРЕЗ PATTERN COMMAND ЭТУ ДИЧЬ
                greetingService.greetServer(child, new AsyncCallback<Child>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert("shoto ne tak");
                    }

                    @Override
                    public void onSuccess(Child result) {
                        Window.alert("Vse zbs" + child.getName());
                    }
                });
            }
        });
    }

    public void show() {
        dialogBox.center();
    }

    public Child getChild() {
        return this.child;
    }

}
