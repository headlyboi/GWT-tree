package com.inobitec.tree.client.treetable;

import java.util.List;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasWidgets;
import com.inobitec.tree.client.TreeProject;
import com.inobitec.tree.shared.command.Command;
import com.inobitec.tree.shared.model.Node;

public class TreeTablePresenter implements Presenter {

    private TreeTableDisplay view;

    
    public TreeTablePresenter(TreeTableDisplay view) {
        this.view = view;
        
    }


    public void updateTree() {
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

    
    public void go(HasWidgets container) {
        container.add(view.asWidget());
    }
}
