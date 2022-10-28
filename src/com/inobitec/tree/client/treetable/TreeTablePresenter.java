package com.inobitec.tree.client.treetable;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.inobitec.tree.client.TreeProject;
import com.inobitec.tree.client.event.command.SelectedNodeCommand;
import com.inobitec.tree.shared.model.Node;

public class TreeTablePresenter{

    private TreeTableDisplay view;

    private SelectedNodeCommand selectedNodeCommand;
    
    public TreeTablePresenter(TreeTableDisplay view) {
        this.view = view;
        buildSelectedNodeHandler();
    }

    private void buildSelectedNodeHandler() {
        view.setSelectedNodeCommand(new SelectedNodeCommand() {
            
            @Override
            public void executeSelectedNodeCommand() {
                selectedNodeCommand.executeSelectedNodeCommand();
            }
        });
    }
    
    private void getAllNodes() {
        TreeProject.treeService.getAllNodes(new AsyncCallback<List<Node>>() {

            @Override
            public void onSuccess(List<Node> listNode) {
                view.clearAllRoodAndChildItems();
                view.setAllRootItems(listNode);
                view.setAllChildItems(listNode);
            }

            @Override
            public void onFailure(Throwable caught) {
                Window.alert(caught.getMessage());
            }
        });
    }

    public void updateTree() {
        getAllNodes();
    }
    
    public Node getSelectedNode() {
        return view.getSelectedNode();
    }

    public void go(HasWidgets container) {
        container.add(view.asWidget());
    }

    public void setSelectedNodeCommand(SelectedNodeCommand selectedNodeCommand) {
        this.selectedNodeCommand = selectedNodeCommand;
    }
}
