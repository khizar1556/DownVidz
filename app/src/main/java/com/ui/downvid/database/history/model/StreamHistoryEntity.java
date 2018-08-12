package com.ui.downvid.database.history.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

import com.ui.downvid.database.stream.model.StreamEntity;

import java.util.Date;

import static android.arch.persistence.room.ForeignKey.CASCADE;
import static com.ui.downvid.database.history.model.StreamHistoryEntity.STREAM_HISTORY_TABLE;
import static com.ui.downvid.database.history.model.StreamHistoryEntity.JOIN_STREAM_ID;
import static com.ui.downvid.database.history.model.StreamHistoryEntity.STREAM_ACCESS_DATE;

@Entity(tableName = StreamHistoryEntity.STREAM_HISTORY_TABLE,
        primaryKeys = {StreamHistoryEntity.JOIN_STREAM_ID, StreamHistoryEntity.STREAM_ACCESS_DATE},
        // No need to index for timestamp as they will almost always be unique
        indices = {@Index(value = {StreamHistoryEntity.JOIN_STREAM_ID})},
        foreignKeys = {
                @ForeignKey(entity = StreamEntity.class,
                        parentColumns = StreamEntity.STREAM_ID,
                        childColumns = StreamHistoryEntity.JOIN_STREAM_ID,
                        onDelete = CASCADE, onUpdate = CASCADE)
        })
public class StreamHistoryEntity {
    final public static String STREAM_HISTORY_TABLE = "stream_history";
    final public static String JOIN_STREAM_ID       = "stream_id";
    final public static String STREAM_ACCESS_DATE   = "access_date";
    final public static String STREAM_REPEAT_COUNT  = "repeat_count";

    @ColumnInfo(name = JOIN_STREAM_ID)
    private long streamUid;

    @NonNull
    @ColumnInfo(name = STREAM_ACCESS_DATE)
    private Date accessDate;

    @ColumnInfo(name = STREAM_REPEAT_COUNT)
    private long repeatCount;

    public StreamHistoryEntity(long streamUid, @NonNull Date accessDate, long repeatCount) {
        this.streamUid = streamUid;
        this.accessDate = accessDate;
        this.repeatCount = repeatCount;
    }

    @Ignore
    public StreamHistoryEntity(long streamUid, @NonNull Date accessDate) {
        this(streamUid, accessDate, 1);
    }

    public long getStreamUid() {
        return streamUid;
    }

    public void setStreamUid(long streamUid) {
        this.streamUid = streamUid;
    }

    public Date getAccessDate() {
        return accessDate;
    }

    public void setAccessDate(@NonNull Date accessDate) {
        this.accessDate = accessDate;
    }

    public long getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(long repeatCount) {
        this.repeatCount = repeatCount;
    }
}
