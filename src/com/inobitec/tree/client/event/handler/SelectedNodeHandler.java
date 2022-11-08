package com.inobitec.tree.client.event.handler;

import com.google.gwt.event.shared.EventHandler;
import com.inobitec.tree.client.event.SelectedNodeEvent;

public interface SelectedNodeHandler extends EventHandler {

    public void onSelectedNode(SelectedNodeEvent selectedNodeEvent);
}
