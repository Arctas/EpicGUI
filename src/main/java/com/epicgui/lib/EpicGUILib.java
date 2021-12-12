package com.epicgui.lib;

import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.inventory.InventoryPreClickEvent;

public class EpicGUILib {

    private static boolean initialized = false;

    public static void init() throws EpicGUIException {
        if (initialized) throw new EpicGUIException("Library already initialized");

        EventNode<Event> eventNode = EventNode.all("epicgui");
        eventNode.addListener(InventoryPreClickEvent.class, e -> {
            Player p = e.getPlayer();

        });


        initialized = true;
    }
}
