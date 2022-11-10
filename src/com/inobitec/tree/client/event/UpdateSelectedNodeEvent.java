package com.inobitec.tree.client.event;

import com.google.web.bindery.event.shared.Event;
import com.inobitec.tree.shared.model.Node;
import com.inobitec.tree.client.event.handler.UpdateSelectedNodeHandler;

public class UpdateSelectedNodeEvent extends Event<UpdateSelectedNodeHandler> {

    public static final Type<UpdateSelectedNodeHandler> TYPE = new Type<>();

    private Node selectedNode;

    public UpdateSelectedNodeEvent(Node selectedNode) {
        this.selectedNode = selectedNode;
    }

    public Node getSelectedNode() {
        return selectedNode;
    }

    @Override
    public Type<UpdateSelectedNodeHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(UpdateSelectedNodeHandler handler) {
        handler.onUpdateSelectedNode(this);
    }

}
