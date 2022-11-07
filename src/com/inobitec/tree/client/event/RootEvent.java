package com.inobitec.tree.client.event;

import com.google.web.bindery.event.shared.Event;
import com.inobitec.tree.shared.model.Node;

public class RootEvent extends Event<RootHandler> {

    public static final Type<RootHandler> TYPE = new Type<RootHandler>();

    private Node node;

    public RootEvent(Node node) {
        this.node = node;
    }

    @Override
    public Type<RootHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(RootHandler handler) {
        handler.executeRootHandler(this, node);
    }

    public Node getNode() {
        return node;
    }

}
