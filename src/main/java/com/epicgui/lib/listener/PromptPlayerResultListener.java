package com.epicgui.lib.listener;

import com.epicgui.lib.event.PromptPlayerResultEvent;

public interface PromptPlayerResultListener extends Listener {

    void onResult(PromptPlayerResultEvent event);
}
