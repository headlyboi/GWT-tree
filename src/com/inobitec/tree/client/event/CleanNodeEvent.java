package com.inobitec.tree.client.event;

import com.google.web.bindery.event.shared.Event;
import com.inobitec.tree.client.event.handler.CleanNodeHandler;

public class CleanNodeEvent extends Event<CleanNodeHandler> {

    public static final Type<CleanNodeHandler> TYPE = new Type<>();

    @Override
    public Type<CleanNodeHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(CleanNodeHandler handler) {
        handler.onCleanNodeData(this);
    }

}
