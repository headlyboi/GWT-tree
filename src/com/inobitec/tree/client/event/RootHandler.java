package com.inobitec.tree.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.inobitec.tree.shared.model.Node;

public interface RootHandler extends EventHandler {
    void executeRootHandler(RootEvent handler, Node node);

}
