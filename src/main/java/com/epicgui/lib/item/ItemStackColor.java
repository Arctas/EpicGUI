package com.epicgui.lib.item;

import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public enum ItemStackColor {
    WHITE(Material.WHITE_STAINED_GLASS_PANE),
    ORANGE(Material.ORANGE_STAINED_GLASS_PANE),
    MAGENTA(Material.MAGENTA_STAINED_GLASS_PANE),
    LIGHT_BLUE(Material.LIGHT_BLUE_STAINED_GLASS_PANE),
    YELLOW(Material.YELLOW_STAINED_GLASS_PANE),
    LIME(Material.LIME_STAINED_GLASS_PANE),
    PINK(Material.PINK_STAINED_GLASS_PANE),
    GRAY(Material.GRAY_STAINED_GLASS_PANE),
    LIGHT_GRAY(Material.LIGHT_GRAY_STAINED_GLASS_PANE),
    CYAN(Material.CYAN_STAINED_GLASS_PANE),
    PURPLE(Material.PURPLE_STAINED_GLASS_PANE),
    BLUE(Material.BLUE_STAINED_GLASS_PANE),
    BROWN(Material.BROWN_STAINED_GLASS_PANE),
    GREEN(Material.BROWN_STAINED_GLASS_PANE),
    RED(Material.RED_STAINED_GLASS_PANE),
    BLACK(Material.BLACK_STAINED_GLASS_PANE),
    NULL(null);

    private final Material glassPaneMaterial;

    ItemStackColor(Material glassPaneMaterial) {
        this.glassPaneMaterial = glassPaneMaterial;
    }

    public Material getGlassPaneMaterial() {
        return glassPaneMaterial;
    }
}
