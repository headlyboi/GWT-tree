package com.inobitec.tree.client.event;

import com.google.gwt.event.shared.EventHandler;

public interface SelectedNodeHandler extends EventHandler {

    public void onSelectedNode(SelectedNodeEvent selectedNodeEvent);
}
