package com.inobitec.tree.client.event;

import com.google.web.bindery.event.shared.Event;
import com.inobitec.tree.client.event.handler.ChildHandler;
import com.inobitec.tree.shared.model.Node;

public class ChildEvent extends Event<ChildHandler> {

    public static final Type<ChildHandler> TYPE = new Type<>();

    private Node node;

    private Integer parentId;

    public ChildEvent(Node node, Integer parentId) {
        this.node = node;
        this.parentId = parentId;
    }

    @Override
    public Type<ChildHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ChildHandler handler) {
        handler.executeChildHandler(this, node, parentId);
    }

}
