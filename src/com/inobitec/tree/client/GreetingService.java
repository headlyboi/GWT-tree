package com.inobitec.tree.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.inobitec.tree.shared.model.Child;

@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
    Child greetServer(Child child) throws IllegalArgumentException;
}
