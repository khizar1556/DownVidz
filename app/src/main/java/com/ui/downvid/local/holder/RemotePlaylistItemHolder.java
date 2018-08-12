package com.ui.downvid.local.holder;

import android.view.ViewGroup;

import com.ui.downvid.database.LocalItem;
import com.ui.downvid.database.playlist.model.PlaylistRemoteEntity;
import org.schabi.newpipe.extractor.NewPipe;
import com.ui.downvid.local.LocalItemBuilder;
import com.ui.downvid.util.ImageDisplayConstants;
import com.ui.downvid.util.Localization;

import java.text.DateFormat;

public class RemotePlaylistItemHolder extends PlaylistItemHolder {
    public RemotePlaylistItemHolder(LocalItemBuilder infoItemBuilder, ViewGroup parent) {
        super(infoItemBuilder, parent);
    }

    @Override
    public void updateFromItem(final LocalItem localItem, final DateFormat dateFormat) {
        if (!(localItem instanceof PlaylistRemoteEntity)) return;
        final PlaylistRemoteEntity item = (PlaylistRemoteEntity) localItem;

        itemTitleView.setText(item.getName());
        itemStreamCountView.setText(String.valueOf(item.getStreamCount()));
        itemUploaderView.setText(Localization.concatenateStrings(item.getUploader(),
                NewPipe.getNameOfService(item.getServiceId())));

        itemBuilder.displayImage(item.getThumbnailUrl(), itemThumbnailView,
                ImageDisplayConstants.DISPLAY_PLAYLIST_OPTIONS);

        super.updateFromItem(localItem, dateFormat);
    }
}
