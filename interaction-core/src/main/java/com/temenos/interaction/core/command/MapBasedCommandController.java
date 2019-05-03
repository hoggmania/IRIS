package com.temenos.interaction.core.command;

/*******************************************************************************
 * Copyright © Temenos Headquarters SA 1993-2019.  All rights reserved.
 *******************************************************************************/

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The default implementation of CommandController interface, bootstrapped by proving a map of InteractionCommands 
 * (keyed with names of the commands to be assigned). Replaces NewCommandController.
 * The class instances are thread safe, and individual commands can be added to the map returned by getCommandMap().
 * 
 * @author trojanbug
 */
public class MapBasedCommandController implements CommandController {

    private static final Logger logger = LoggerFactory.getLogger(MapBasedCommandController.class);

    private Map<String, InteractionCommand> commandMap = new ConcurrentHashMap<String, InteractionCommand>();

    public MapBasedCommandController() {
        logger.trace("Empty MapBasedCommandController created: " + this);
    }

    public MapBasedCommandController(Map<String, InteractionCommand> commandMap) {
        logger.trace("MapBasedCommandController created: " + this);
        setCommandMap(commandMap);
    }

    @Override
    public InteractionCommand fetchCommand(String name) {
        logger.trace("Retrieving command for name {} from MapBasedCommandController {}", name, this);
        return commandMap.get(name);
    }

    @Override
    public boolean isValidCommand(String name) {
        return commandMap.containsKey(name);
    }

    /**
     * @return the commandMap
     */
    public Map<String, InteractionCommand> getCommandMap() {
        return commandMap;
    }

    /**
     * @param commandMap the commandMap to set
     */
    public final void setCommandMap(Map<String, InteractionCommand> commandMap) {
        logger.trace("New command map set on MapBasedCommandController {}",this);
        this.commandMap = new ConcurrentHashMap(commandMap);
    }

}
