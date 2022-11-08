package com.inobitec.tree.client.event;

import com.google.web.bindery.event.shared.Event;
import com.inobitec.tree.client.event.handler.UpdateAllNodesHandler;

public class UpdateAllNodesEvent extends Event<UpdateAllNodesHandler> {

    public static final Type<UpdateAllNodesHandler> TYPE = new Type<>();

    public UpdateAllNodesEvent() {
    }

    @Override
    public Type<UpdateAllNodesHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(UpdateAllNodesHandler handler) {
        handler.onUpdateAllNodes(this);
    }

}
