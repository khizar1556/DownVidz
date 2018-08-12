package us.ui.downvidz.ui.fragment;

import us.ui.downvidz.get.DownloadManager;
import us.ui.downvidz.service.DownloadManagerService;

public class AllMissionsFragment extends MissionsFragment {

    @Override
    protected DownloadManager setupDownloadManager(DownloadManagerService.DMBinder binder) {
        return binder.getDownloadManager();
    }
}
