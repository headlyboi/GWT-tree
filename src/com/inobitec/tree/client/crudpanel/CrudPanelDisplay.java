package com.inobitec.tree.client.crudpanel;

import com.google.gwt.user.client.ui.IsWidget;
import com.inobitec.tree.shared.command.Command;
import com.inobitec.tree.shared.handler.Handler;

public interface CrudPanelDisplay extends IsWidget {

    void setHandler(Handler handler);
}
