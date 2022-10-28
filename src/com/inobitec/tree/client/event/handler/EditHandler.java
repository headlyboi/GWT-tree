package com.inobitec.tree.client.event.handler;

import com.inobitec.tree.shared.model.Node;

public interface EditHandler {

    void executeEditHandler(Node node, int selectedId);

}
