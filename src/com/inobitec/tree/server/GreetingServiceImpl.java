package com.inobitec.tree.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.inobitec.tree.client.GreetingService;

public class GreetingServiceImpl extends RemoteServiceServlet implements 
        GreetingService {

    private static final long serialVersionUID = -4136337623971180253L;

    
    public String greetServer(String input) throws IllegalArgumentException {
            return null;
    }

}
 