package com.epicgui.lib.gui;

import com.epicgui.lib.components.Component;
import com.epicgui.lib.components.StaticComponent;
import com.epicgui.lib.event.ComponentClickEvent;
import com.epicgui.lib.item.ItemStackColor;
import com.epicgui.lib.listener.ComponentClickListener;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;

import java.util.ArrayList;

public class EpicGUI {

    private net.kyori.adventure.text.Component title;
    private final ArrayList<Component> components;
    private final InventoryType inventoryType;

    private ComponentClickListener componentClickListener;

    public EpicGUI(net.kyori.adventure.text.Component title, InventoryType inventoryType) {
        this.title = title;
        this.components = new ArrayList<>();
        this.inventoryType = inventoryType;
    }

    public net.kyori.adventure.text.Component getTitle() {
        return title;
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public ArrayList<Component> getComponents() {
        return components;
    }

    public InventoryType getInventoryType() {
        return inventoryType;
    }

    public void open(Player player) {
        Inventory inventory = render();
        EventNode<Event> eventNode = EventNode.all("epicgui");

        eventNode.addListener(EventListener.builder(InventoryPreClickEvent.class)
                .expireWhen(inventoryPreClickEvent -> !player.getOpenInventory().getTitle().equals(title))
                .handler(e -> {
                    int slot = e.getSlot();
                    if (componentClickListener != null) componentClickListener.onClick(new ComponentClickEvent(e, this));

                    for (Component component : components) {
                        if (component.getOccupiedSlots().contains(slot)) {
                            if(component.canPlayerTakeOutOfInventory()) e.setCancelled(true);
                            if (component.getComponentClickListener() != null)
                                component.getComponentClickListener().onClick(new ComponentClickEvent(e, this));
                        }
                    }
                }).build());

        MinecraftServer.getGlobalEventHandler().addChild(eventNode);


        player.openInventory(render());
    }

    private Inventory render() {
        return render(new Inventory(inventoryType, title));
    }

    private Inventory render(Inventory inventory) {
        for (Component component : components) {
            inventory = component.render(inventory);
        }
        return inventory;
    }

    public ComponentClickListener getComponentClickListener() {
        return componentClickListener;
    }

    public void setComponentClickListener(ComponentClickListener componentClickListener) {
        this.componentClickListener = componentClickListener;
    }
}
