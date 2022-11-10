package com.inobitec.tree.client.event.handler;

import com.google.gwt.event.shared.EventHandler;
import com.inobitec.tree.client.event.UpdateTablesEvent;

public interface UpdateTablesHandler extends EventHandler {

    void onUpdateTables(UpdateTablesEvent updateTablesEvent);
}
