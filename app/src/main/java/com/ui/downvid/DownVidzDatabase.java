package com.ui.downvid;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.NonNull;

import com.ui.downvid.database.AppDatabase;

import static com.ui.downvid.database.AppDatabase.DATABASE_NAME;
import static com.ui.downvid.database.Migrations.MIGRATION_11_12;

public final class DownVidzDatabase {

    private static volatile AppDatabase databaseInstance;

    private DownVidzDatabase() {
        //no instance
    }

    private static AppDatabase getDatabase(Context context) {
        return Room
                .databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME)
                .addMigrations(MIGRATION_11_12)
                .fallbackToDestructiveMigration()
                .build();
    }

    @NonNull
    public static AppDatabase getInstance(@NonNull Context context) {
        AppDatabase result = databaseInstance;
        if (result == null) {
            synchronized (DownVidzDatabase.class) {
                result = databaseInstance;
                if (result == null) {
                    databaseInstance = (result = getDatabase(context));
                }
            }
        }

        return result;
    }
}
