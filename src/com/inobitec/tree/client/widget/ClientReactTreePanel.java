package com.inobitec.tree.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.inobitec.tree.client.GreetingService;
import com.inobitec.tree.client.GreetingServiceAsync;
import com.inobitec.tree.shared.command.Command;
import com.inobitec.tree.shared.model.Child;

public class ClientReactTreePanel extends Composite {

    private static final String CRUD = "crud";
    private static final String TREE = "tree";
    private static final String SELECTED = "selected";
    private static final String MAIN_HEADER = "Client React Tree";
    private static final String AN_HEADER = "allNodes";
    private static final String AN_TABLE = "allNodesTable";

    private Label headingElement;
    private HorizontalPanel treeAndSelectedHorizontalPanel;
    private TreeTable treeTable;
    private SelectedTable selectedTable;
    private CrudPanel crudPanel;
    private VerticalPanel verticalPanel;
    private CrudDialogBox crudDialogBox;
    private AllNodesPanel allNodesPanel;
    private Child child;
    public final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

    private void build() {
        child = new Child();
        verticalPanel = new VerticalPanel();
        headingElement = new Label(MAIN_HEADER);
        treeAndSelectedHorizontalPanel = new HorizontalPanel();
        treeTable = new TreeTable(TREE);
        selectedTable = new SelectedTable(SELECTED);
        crudPanel = new CrudPanel(CRUD);
        crudDialogBox = new CrudDialogBox();
        allNodesPanel = new AllNodesPanel(AN_HEADER, AN_TABLE);

    }

    public ClientReactTreePanel() {
        build();
        verticalPanel.add(headingElement);
        treeAndSelectedHorizontalPanel.add(treeTable);
        treeAndSelectedHorizontalPanel.add(selectedTable);
        verticalPanel.add(treeAndSelectedHorizontalPanel);
        verticalPanel.add(crudPanel);
        verticalPanel.add(allNodesPanel);
        buildCrudButtons();
        initWidget(verticalPanel);
    }

    private void buildCrudButtons() {
        // ROOT BUTTON
        crudPanel.addRootButtonClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                crudDialogBox.show();
                crudDialogBox.setCommand(new Command() {

                    @Override
                    public void execute() {
                        child.setName(crudDialogBox.getNameContent());
                        child.setIp(crudDialogBox.getIpContent());
                        child.setPort(crudDialogBox.getPortContent());

                        greetingService.greetServer(child, new AsyncCallback<Child>() {

                            @Override
                            public void onSuccess(Child result) {
                             treeTable.addRootItem(child);
                            }

                            @Override
                            public void onFailure(Throwable caught) {
                                // TODO Auto-generated method stub

                            }
                        });
                    }
                });
            }
        });
        // CHILD BUTTON
        crudPanel.addChildButtonClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                Window.alert(child.getName());
            }
        });
        // EDIT BUTTON
        crudPanel.addEditButtonClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                // TODO Auto-generated method stub
            }
        });
        // DELETE BUTTON
        crudPanel.addDeleteButtonClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                // TODO Auto-generated method stub

            }
        });
    }

}
