package com.inobitec.tree.client.event.handler;

import com.google.gwt.event.shared.EventHandler;
import com.inobitec.tree.client.event.CleanNodeEvent;

public interface CleanNodeHandler extends EventHandler{

    public void onCleanNodeData(CleanNodeEvent event);
}
