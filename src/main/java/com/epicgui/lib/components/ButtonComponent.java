package com.epicgui.lib.components;

import net.minestom.server.item.ItemStack;

public class ButtonComponent extends Component {

    public ButtonComponent(int x, int y, ItemStack itemStack) {
        super(x, y, itemStack);
    }

    public ButtonComponent(int x, int y, int width, int height, ItemStack itemStack) {
        super(x, y, width, height, itemStack);
    }
}
