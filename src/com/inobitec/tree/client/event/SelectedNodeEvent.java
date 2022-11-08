package com.inobitec.tree.client.event;

import com.google.web.bindery.event.shared.Event;
import com.inobitec.tree.client.event.handler.SelectedNodeHandler;
import com.inobitec.tree.shared.model.Node;

public class SelectedNodeEvent extends Event<SelectedNodeHandler> {

    public static final Type<SelectedNodeHandler> TYPE = new Type<>();

    private Node selectedNode;

    public SelectedNodeEvent(Node selectedNode) {
        this.selectedNode = selectedNode;
    }

    public Node getSelectedNode() {
        return selectedNode;
    }

    @Override
    public Type<SelectedNodeHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(SelectedNodeHandler handler) {
        handler.onSelectedNode(this);
    }

}
