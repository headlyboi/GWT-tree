package com.inobitec.tree.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.inobitec.tree.client.GreetingService;
import com.inobitec.tree.shared.model.Child;

public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

    private static final long serialVersionUID = -4136337623971180253L;

    public Child greetServer(Child child) throws IllegalArgumentException {
        return child;
    }
}
