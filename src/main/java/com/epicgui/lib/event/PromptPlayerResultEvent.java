package com.epicgui.lib.event;

import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerChatEvent;

import javax.swing.text.PlainDocument;

public class PromptPlayerResultEvent extends Event {

    private final PlayerChatEvent event;

    public PromptPlayerResultEvent(PlayerChatEvent event) {
        this.event = event;
    }

    public Player getPlayer() {
        return event.getPlayer();
    }

    public String getResult() {
        return event.getMessage();
    }
}
