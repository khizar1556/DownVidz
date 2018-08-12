package com.ui.downvid.fragments;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ui.downvid.BaseFragment;
import com.ui.downvid.R;

import org.schabi.newpipe.extractor.ServiceList;

import com.ui.downvid.fragments.list.kiosk.KioskFragment;
import com.ui.downvid.local.feed.FeedFragment;
import com.ui.downvid.report.ErrorActivity;
import com.ui.downvid.report.UserAction;
import com.ui.downvid.util.NavigationHelper;
import com.ui.downvid.util.ServiceHelper;
import com.ui.downvid.util.ThemeHelper;

public class MainFragment extends BaseFragment implements TabLayout.OnTabSelectedListener {

    public int currentServiceId = -1;
    private ViewPager viewPager;

    /*//////////////////////////////////////////////////////////////////////////
    // Constants
    //////////////////////////////////////////////////////////////////////////*/

    private static final int FALLBACK_SERVICE_ID = ServiceList.YouTube.getServiceId();
    private static final String FALLBACK_CHANNEL_URL = "https://www.youtube.com/channel/UC-9-kyTW8ZkZNDHQJ6FgpwQ";
    private static final String FALLBACK_CHANNEL_NAME = "Music";
    private static final String FALLBACK_KIOSK_ID = "Trending";


    /*//////////////////////////////////////////////////////////////////////////
    // Fragment's LifeCycle
    //////////////////////////////////////////////////////////////////////////*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        currentServiceId = ServiceHelper.getSelectedServiceId(activity);
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    protected void initViews(View rootView, Bundle savedInstanceState) {
        super.initViews(rootView, savedInstanceState);

        TabLayout tabLayout = rootView.findViewById(R.id.main_tab_layout);
        viewPager = rootView.findViewById(R.id.pager);

        /*  Nested fragment, use child fragment here to maintain backstack in view pager. */
        PagerAdapter adapter = new PagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount());

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setVisibility(View.GONE);

        int whatsHotIcon = ThemeHelper.resolveResourceIdFromAttr(activity, R.attr.ic_hot);

        if (isSubscriptionsPageOnlySelected()) {
          /*  tabLayout.getTabAt(0).setIcon(channelIcon);
            tabLayout.getTabAt(1).setIcon(bookmarkIcon);*/
        } else {
            tabLayout.getTabAt(0).setIcon(whatsHotIcon);
           /* tabLayout.getTabAt(1).setIcon(channelIcon);
            tabLayout.getTabAt(2).setIcon(bookmarkIcon);*/
        }
        try {
            NavigationHelper.openSearchFragment(
                    getFragmentManager(),
                    ServiceHelper.getSelectedServiceId(activity),
                    "");
        } catch (Exception e) {
            ErrorActivity.reportUiError((AppCompatActivity) getActivity(), e);
        }
    }

    /*//////////////////////////////////////////////////////////////////////////
    // Menu
    //////////////////////////////////////////////////////////////////////////*/

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (DEBUG) Log.d(TAG, "onCreateOptionsMenu() called with: menu = [" + menu + "], inflater = [" + inflater + "]");
        inflater.inflate(R.menu.main_fragment_menu, menu);


        ActionBar supportActionBar = activity.getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                try {
                    NavigationHelper.openSearchFragment(
                            getFragmentManager(),
                            ServiceHelper.getSelectedServiceId(activity),
                            "");
                } catch (Exception e) {
                    ErrorActivity.reportUiError((AppCompatActivity) getActivity(), e);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*//////////////////////////////////////////////////////////////////////////
    // Tabs
    //////////////////////////////////////////////////////////////////////////*/

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    private class PagerAdapter extends FragmentPagerAdapter {
        PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                   /* return isSubscriptionsPageOnlySelected() ? new SubscriptionFragment() : getMainPageFragment();*/
                  /* return getMainPageFragment();*/
                case 1:
                    /*if(PreferenceManager.getDefaultSharedPreferences(getActivity())
                            .getString(getString(R.string.main_page_content_key), getString(R.string.blank_page_key))
                            .equals(getString(R.string.subscription_page_key))) {
                        return new BookmarkFragment();
                    } else {
                        return new SubscriptionFragment();
                    }*/
                case 2:
                    /*return new BookmarkFragment();*/
                default:
                    return new BlankFragment();
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //return getString(this.tabTitles[position]);
            return "";
        }

        @Override
        public int getCount() {
            return 1;
        }
    }

    /*//////////////////////////////////////////////////////////////////////////
    // Main page content
    //////////////////////////////////////////////////////////////////////////*/

    private boolean isSubscriptionsPageOnlySelected() {
        return PreferenceManager.getDefaultSharedPreferences(activity)
                .getString(getString(R.string.main_page_content_key), getString(R.string.blank_page_key))
                .equals(getString(R.string.subscription_page_key));
    }

    private Fragment getMainPageFragment() {
        if (getActivity() == null) return new BlankFragment();

        try {
            SharedPreferences preferences =
                    PreferenceManager.getDefaultSharedPreferences(getActivity());
            final String setMainPage = preferences.getString(getString(R.string.main_page_content_key),
                    getString(R.string.main_page_selectd_kiosk_id));
            if (setMainPage.equals(getString(R.string.blank_page_key))) {
                return new BlankFragment();
            } /*else if (setMainPage.equals(getString(R.string.kiosk_page_key))) {
               *//* int serviceId = preferences.getInt(getString(R.string.main_page_selected_service),
                        FALLBACK_SERVICE_ID);
                String kioskId = preferences.getString(getString(R.string.main_page_selectd_kiosk_id),
                        FALLBACK_KIOSK_ID);
                KioskFragment fragment = KioskFragment.getInstance(serviceId, kioskId);
                fragment.useAsFrontPage(true);
                return fragment;*//*
            } */else if (setMainPage.equals(getString(R.string.feed_page_key))) {
                FeedFragment fragment = new FeedFragment();
                fragment.useAsFrontPage(true);
                return fragment;
            } else {
                return new BlankFragment();
            }

        } catch (Exception e) {
            ErrorActivity.reportError(activity, e,
                    activity.getClass(),
                    null,
                    ErrorActivity.ErrorInfo.make(UserAction.UI_ERROR,
                            "none", "", R.string.app_ui_crash));
            return new BlankFragment();
        }
    }

    /*//////////////////////////////////////////////////////////////////////////
    // Select Kiosk
    //////////////////////////////////////////////////////////////////////////*/


}
