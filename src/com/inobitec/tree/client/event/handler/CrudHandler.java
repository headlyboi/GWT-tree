package com.inobitec.tree.client.event.handler;

import com.inobitec.tree.client.event.command.Command;

public interface CrudHandler {
    
    Command executeHandler(String buttonName);
}
