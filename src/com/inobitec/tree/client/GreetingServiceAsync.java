package com.inobitec.tree.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.inobitec.tree.shared.model.Child;

public interface GreetingServiceAsync {
    void greetServer(Child child, AsyncCallback<Child> callback)
            throws IllegalArgumentException;
}
