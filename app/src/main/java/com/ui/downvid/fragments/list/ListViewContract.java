package com.ui.downvid.fragments.list;

import com.ui.downvid.fragments.ViewContract;

public interface ListViewContract<I, N> extends ViewContract<I> {
    void showListFooter(boolean show);

    void handleNextItems(N result);
}
