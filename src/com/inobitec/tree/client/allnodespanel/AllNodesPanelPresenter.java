package com.inobitec.tree.client.allnodespanel;

import com.google.gwt.user.client.ui.HasWidgets;

public class AllNodesPanelPresenter implements Presenter {

    private AllNodesPanelDisplay view;
    
    public AllNodesPanelPresenter(AllNodesPanelDisplay view) {
        this.view = view;
    }
    
    public void go(HasWidgets container) {
        container.add(view.asWidget());
    }
}
