package com.ui.downvid.database;

public interface LocalItem {
    enum LocalItemType {
        PLAYLIST_LOCAL_ITEM,
        PLAYLIST_REMOTE_ITEM,

        PLAYLIST_STREAM_ITEM,
        STATISTIC_STREAM_ITEM,
    }

    LocalItemType getLocalItemType();
}
