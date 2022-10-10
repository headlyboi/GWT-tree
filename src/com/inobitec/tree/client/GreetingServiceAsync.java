package com.inobitec.tree.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.inobitec.tree.model.Child;

public interface GreetingServiceAsync {
    void greetServer(String input, AsyncCallback<String> callback)
            throws IllegalArgumentException;
    void getChildByKey(String key, AsyncCallback<Child> callback)
            throws IllegalArgumentException;
}
