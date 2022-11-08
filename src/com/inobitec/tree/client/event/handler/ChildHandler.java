package com.inobitec.tree.client.event.handler;

import com.google.gwt.event.shared.EventHandler;
import com.inobitec.tree.client.event.ChildEvent;
import com.inobitec.tree.shared.model.Node;

public interface ChildHandler extends EventHandler {
    
    void executeChildHandler(ChildEvent childEvent, Node node, Integer selectedId);
}
