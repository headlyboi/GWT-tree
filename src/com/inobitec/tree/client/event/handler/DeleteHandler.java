package com.inobitec.tree.client.event.handler;

import com.google.gwt.event.shared.EventHandler;
import com.inobitec.tree.client.event.DeleteEvent;

public interface DeleteHandler extends EventHandler{

    void executeDeleteHandler(DeleteEvent deleteEvent, Integer id);
}
