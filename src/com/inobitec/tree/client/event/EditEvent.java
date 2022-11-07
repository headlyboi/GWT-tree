package com.inobitec.tree.client.event;

import com.google.web.bindery.event.shared.Event;
import com.inobitec.tree.shared.model.Node;

public class EditEvent extends Event<EditHandler> {

    public static final Type<EditHandler> TYPE = new Type<EditHandler>();

    private Node node;

    private Integer selectedId;

    public EditEvent(Node node, Integer selectedId) {
        this.node = node;
        this.selectedId = selectedId;
    }

    @Override
    public Type<EditHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(EditHandler handler) {
        handler.executeEditHandler(this, node, selectedId);
    }

}
