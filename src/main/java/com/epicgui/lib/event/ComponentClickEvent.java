package com.epicgui.lib.event;

import com.epicgui.lib.gui.EpicGUI;
import com.epicgui.lib.listener.PromptPlayerResultListener;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.event.player.PlayerChatEvent;
import net.minestom.server.inventory.click.ClickType;

import java.util.concurrent.atomic.AtomicInteger;

public class ComponentClickEvent extends Event {

    private final Player player;
    private final InventoryPreClickEvent event;
    private final EpicGUI gui;

    public ComponentClickEvent(InventoryPreClickEvent event, EpicGUI gui) {
        this.player = event.getPlayer();
        this.event = event;
        this.gui = gui;
    }

    public Player getPlayer() {
        return player;
    }

    public void cancel() {
        event.setCancelled(true);
    }

    public void setCancelled(boolean cancelled) {
        event.setCancelled(cancelled);
    }

    public int getSlot() {
        return event.getSlot();
    }

    public ClickType getClickType() {
        return event.getClickType();
    }

    public void promptPlayerInChat(PromptPlayerResultListener listener) {
        AtomicInteger i = new AtomicInteger();
        player.closeInventory();
        MinecraftServer.getGlobalEventHandler().addListener(EventListener.builder(PlayerChatEvent.class)
                .expireWhen(e -> e.getPlayer().getUsername().equals(player.getUsername()) && i.get() == 1)
                .handler(e -> {
                    System.out.println("lol");
                    e.setCancelled(true);
                    listener.onResult(new PromptPlayerResultEvent(e));
                    gui.open(player);
                    i.set(1);
                }).build());

    }
}
