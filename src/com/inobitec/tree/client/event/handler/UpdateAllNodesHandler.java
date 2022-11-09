package com.inobitec.tree.client.event.handler;

import com.google.gwt.event.shared.EventHandler;
import com.inobitec.tree.client.event.UpdateAllNodesEvent;

public interface UpdateAllNodesHandler extends EventHandler {

    public void onUpdateAllNodes(UpdateAllNodesEvent event);
}
