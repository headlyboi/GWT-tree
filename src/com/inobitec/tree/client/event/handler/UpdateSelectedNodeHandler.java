package com.inobitec.tree.client.event.handler;

import com.google.gwt.event.shared.EventHandler;
import com.inobitec.tree.client.event.UpdateSelectedNodeEvent;

public interface UpdateSelectedNodeHandler extends EventHandler{

    void onUpdateSelectedNode(UpdateSelectedNodeEvent updateSelectedNodeEvent);
}
