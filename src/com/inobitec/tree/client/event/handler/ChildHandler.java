package com.inobitec.tree.client.event.handler;

import com.inobitec.tree.shared.model.Node;

public interface ChildHandler {

    void executeChildHandler(Node node, int selectedId);

}
