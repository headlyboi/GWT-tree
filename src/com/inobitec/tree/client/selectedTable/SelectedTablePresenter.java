package com.inobitec.tree.client.selectedTable;

import com.google.gwt.user.client.ui.HasWidgets;

public class SelectedTablePresenter implements Presenter{

    private SelectedTableDisplay view;
    
    public SelectedTablePresenter(SelectedTableDisplay view) {
        this.view = view;
    }

    public void go(HasWidgets container) {
        container.add(view.asWidget());
    }
}
