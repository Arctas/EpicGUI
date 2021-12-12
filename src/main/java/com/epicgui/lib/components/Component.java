package com.epicgui.lib.components;

import com.epicgui.lib.event.ComponentClickEvent;
import com.epicgui.lib.listener.ComponentClickListener;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventListener;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.item.ItemStack;

import java.util.ArrayList;

public abstract class Component {

    private final int x, y, width, height;
    private ItemStack itemStack;
    private ComponentClickListener componentClickListener;
    private boolean canPlayerTakeOutOfInventory = true;

    public Component(int x, int y, ItemStack itemStack) {
        this(x, y, 1, 1, itemStack);
    }

    public Component(int x, int y, int width, int height, ItemStack itemStack) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.itemStack = itemStack;
    }

    public void setComponentClickListener(ComponentClickListener event) {
        this.componentClickListener = event;
    }

    public ComponentClickListener getComponentClickListener() {
        return componentClickListener;
    }

    public Inventory render(Inventory inventory) {
        int basePosition = x;
        for (int i = 0; i < y; i++) basePosition += 9;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                inventory.setItemStack(basePosition + j + (i * 9), itemStack);
            }
        }

        return inventory;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<Integer> getOccupiedSlots() {
        ArrayList<Integer> occupiedSlots = new ArrayList<>();
        int basePosition = x;
        for (int i = 0; i < y; i++) basePosition += 9;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                occupiedSlots.add(basePosition + j + (i * 9));
            }
        }
        return occupiedSlots;
    }

    public Component canPlayerTakeOutOfInventory(boolean canPlayerTakeOutOfInventory) {
        this.canPlayerTakeOutOfInventory = canPlayerTakeOutOfInventory;
        return this;
    }

    public boolean canPlayerTakeOutOfInventory() {
        return canPlayerTakeOutOfInventory;
    }
}
