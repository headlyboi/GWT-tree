package com.inobitec.tree.client.event;

import com.google.web.bindery.event.shared.Event;

public class SelectedNodeEvent extends Event<SelectedNodeHandler> {

    public static final Type<SelectedNodeHandler> TYPE = new Type<SelectedNodeHandler>();

    public SelectedNodeEvent() {
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
