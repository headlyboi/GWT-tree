package com.inobitec.tree.client.event;

import com.google.web.bindery.event.shared.Event;
import com.inobitec.tree.client.event.handler.UpdateTreeHandler;

public class UpdateTreeEvent extends Event<UpdateTreeHandler> {

    public static final Type<UpdateTreeHandler> TYPE = new Type<>();

    public UpdateTreeEvent() {
    }

    @Override
    public Type<UpdateTreeHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(UpdateTreeHandler handler) {
        handler.onUpdateTree(this);
    }

}
