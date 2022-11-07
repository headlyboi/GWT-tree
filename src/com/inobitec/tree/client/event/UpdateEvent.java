package com.inobitec.tree.client.event;

import com.google.web.bindery.event.shared.Event;

public class UpdateEvent extends Event<UpdateHandler> {

    public static final Type<UpdateHandler> TYPE = new Type<UpdateHandler>();

    public UpdateEvent() {
    }

    @Override
    public Type<UpdateHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(UpdateHandler handler) {
        handler.onUpdate(this);
    }

}
