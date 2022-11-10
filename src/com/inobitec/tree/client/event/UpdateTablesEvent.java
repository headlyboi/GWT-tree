package com.inobitec.tree.client.event;

import com.google.web.bindery.event.shared.Event;
import com.inobitec.tree.client.event.handler.UpdateTablesHandler;

public class UpdateTablesEvent extends Event<UpdateTablesHandler> {

    public static final Type<UpdateTablesHandler> TYPE = new Type<>();

    @Override
    public Type<UpdateTablesHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(UpdateTablesHandler handler) {
        handler.onUpdateTables(this);
    }
}
