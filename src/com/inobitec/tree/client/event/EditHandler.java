package com.inobitec.tree.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.inobitec.tree.shared.model.Node;

public interface EditHandler extends EventHandler {
    void executeEditHandler(EditEvent editEvent, Node node, int selectedId);

}
