package com.epicgui.lib.components;

import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class TextComponent extends Component {

    public TextComponent(int x, int y, Material material, net.kyori.adventure.text.Component name, net.kyori.adventure.text.Component... lore) {
        super(x, y, ItemStack.builder(material).displayName(name).lore(lore).build());
    }

    public TextComponent(int x, int y, Material material, int customModelData, net.kyori.adventure.text.Component name, net.kyori.adventure.text.Component... lore) {
        super(x, y, ItemStack.builder(material).displayName(name).lore(lore).meta(builder -> builder.customModelData(customModelData)).build());
    }

    public TextComponent(int x, int y, int width, int height, Material material, net.kyori.adventure.text.Component name, net.kyori.adventure.text.Component... lore) {
        super(x, y, width, height, ItemStack.builder(material).displayName(name).lore(lore).build());
    }

    public TextComponent(int x, int y, int width, int height, Material material, int customModelData, net.kyori.adventure.text.Component name, net.kyori.adventure.text.Component... lore) {
        super(x, y, width, height, ItemStack.builder(material).displayName(name).lore(lore).meta(builder -> builder.customModelData(customModelData)).build());
    }
}
