package com.inobitec.tree.client.event.handler;

import com.google.gwt.event.shared.EventHandler;
import com.inobitec.tree.client.event.UpdateButtonsEvent;

public interface UpdateButtonsHandler extends EventHandler{

    public void onUpdateButtons(UpdateButtonsEvent buttonsEvent);
}
