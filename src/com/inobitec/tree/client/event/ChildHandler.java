package com.inobitec.tree.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.inobitec.tree.shared.model.Node;

public interface ChildHandler extends EventHandler {
    void executeChildHandler(ChildEvent childEvent, Node node, int selectedId);
}
