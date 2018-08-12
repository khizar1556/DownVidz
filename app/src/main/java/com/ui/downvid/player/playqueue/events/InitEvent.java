package com.ui.downvid.player.playqueue.events;

public class InitEvent implements PlayQueueEvent {
    @Override
    public PlayQueueEventType type() {
        return PlayQueueEventType.INIT;
    }
}
