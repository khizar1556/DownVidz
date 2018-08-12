package com.ui.downvid.player.playqueue.events;

import java.io.Serializable;

public interface PlayQueueEvent extends Serializable {
    PlayQueueEventType type();
}
