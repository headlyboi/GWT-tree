package com.inobitec.tree.client.event;

import com.google.web.bindery.event.shared.Event;
import com.inobitec.tree.client.event.handler.DeleteHandler;

public class DeleteEvent extends Event<DeleteHandler> {

    public static final Type<DeleteHandler> TYPE = new Type<>();

    private Integer id;

    public DeleteEvent(Integer id) {
        this.id = id;
    }

    @Override
    public Type<DeleteHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(DeleteHandler handler) {
        handler.executeDeleteHandler(this, id);
    }

}
