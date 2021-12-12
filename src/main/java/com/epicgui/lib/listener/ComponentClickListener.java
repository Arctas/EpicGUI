package com.epicgui.lib.listener;

import com.epicgui.lib.event.ComponentClickEvent;

public interface ComponentClickListener extends Listener {

    void onClick(ComponentClickEvent event);
}
