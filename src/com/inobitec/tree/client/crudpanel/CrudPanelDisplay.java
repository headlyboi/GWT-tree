package com.inobitec.tree.client.crudpanel;

import com.google.gwt.user.client.ui.IsWidget;
import com.inobitec.tree.client.event.handler.ChildHandler;
import com.inobitec.tree.client.event.handler.EditHandler;
import com.inobitec.tree.client.event.handler.RootHandler;

public interface CrudPanelDisplay extends IsWidget {

    void setRootHandler(RootHandler handler);
    
    void setChildHandler(ChildHandler handler);
    
    void setEditHandler(EditHandler handler);
    
    void setSelectedId(int selectedId);
    
    int getSelectedId();
}
