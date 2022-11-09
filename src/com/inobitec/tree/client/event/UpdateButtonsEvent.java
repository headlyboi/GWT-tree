package com.inobitec.tree.client.event;

import com.google.web.bindery.event.shared.Event;
import com.inobitec.tree.client.event.handler.UpdateButtonsHandler;

public class UpdateButtonsEvent extends Event<UpdateButtonsHandler>{

    public static final Type<UpdateButtonsHandler> TYPE = new Type<>();
    
    private boolean isActive;
    
    public UpdateButtonsEvent(boolean isActive) {
        this.isActive = isActive;
    }
    
    @Override
    public Type<UpdateButtonsHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(UpdateButtonsHandler handler) {
        handler.onUpdateButtons(this);
    }

    public boolean isActive() {
        return isActive;
    }

}
