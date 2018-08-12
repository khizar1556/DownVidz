package com.ui.downvid.database.history.dao;

import com.ui.downvid.database.BasicDAO;

public interface HistoryDAO<T> extends BasicDAO<T> {
    T getLatestEntry();
}
