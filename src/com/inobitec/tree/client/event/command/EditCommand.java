package com.inobitec.tree.client.event.command;

import com.inobitec.tree.shared.model.Node;

public interface EditCommand {

    void executeEditCommand();
    
    Node getSelectedNode();
}
