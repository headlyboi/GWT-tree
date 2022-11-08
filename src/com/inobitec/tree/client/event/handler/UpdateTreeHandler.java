package com.inobitec.tree.client.event.handler;

import com.google.gwt.event.shared.EventHandler;
import com.inobitec.tree.client.event.UpdateTreeEvent;

public interface UpdateTreeHandler extends EventHandler {

    public void onUpdateTree(UpdateTreeEvent updateEvent);
}
