package com.ui.downvid.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.ui.downvid.database.history.dao.SearchHistoryDAO;
import com.ui.downvid.database.history.dao.StreamHistoryDAO;
import com.ui.downvid.database.history.model.SearchHistoryEntry;
import com.ui.downvid.database.history.model.StreamHistoryEntity;
import com.ui.downvid.database.playlist.dao.PlaylistDAO;
import com.ui.downvid.database.playlist.dao.PlaylistRemoteDAO;
import com.ui.downvid.database.playlist.dao.PlaylistStreamDAO;
import com.ui.downvid.database.playlist.model.PlaylistEntity;
import com.ui.downvid.database.playlist.model.PlaylistRemoteEntity;
import com.ui.downvid.database.playlist.model.PlaylistStreamEntity;
import com.ui.downvid.database.stream.dao.StreamDAO;
import com.ui.downvid.database.stream.dao.StreamStateDAO;
import com.ui.downvid.database.stream.model.StreamEntity;
import com.ui.downvid.database.stream.model.StreamStateEntity;
import com.ui.downvid.database.subscription.SubscriptionDAO;
import com.ui.downvid.database.subscription.SubscriptionEntity;

import static com.ui.downvid.database.Migrations.DB_VER_12_0;

@TypeConverters({Converters.class})
@Database(
        entities = {
                SubscriptionEntity.class, SearchHistoryEntry.class,
                StreamEntity.class, StreamHistoryEntity.class, StreamStateEntity.class,
                PlaylistEntity.class, PlaylistStreamEntity.class, PlaylistRemoteEntity.class
        },
        version = DB_VER_12_0,
        exportSchema = false
)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "newpipe.db";

    public abstract SubscriptionDAO subscriptionDAO();

    public abstract SearchHistoryDAO searchHistoryDAO();

    public abstract StreamDAO streamDAO();

    public abstract StreamHistoryDAO streamHistoryDAO();

    public abstract StreamStateDAO streamStateDAO();

    public abstract PlaylistDAO playlistDAO();

    public abstract PlaylistStreamDAO playlistStreamDAO();

    public abstract PlaylistRemoteDAO playlistRemoteDAO();
}
