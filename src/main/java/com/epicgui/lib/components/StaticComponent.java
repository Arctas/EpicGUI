package com.epicgui.lib.components;

import net.minestom.server.item.ItemStack;

public class StaticComponent extends Component {

    public StaticComponent(int x, int y, ItemStack itemStack) {
        super(x, y, itemStack);
    }

    public StaticComponent(int x, int y, int width, int height, ItemStack itemStack) {
        super(x, y, width, height, itemStack);
    }
}
