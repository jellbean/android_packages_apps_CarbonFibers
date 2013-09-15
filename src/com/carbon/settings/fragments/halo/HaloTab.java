/*
 * Copyright (C) 2012-2013 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.carbon.settings.fragments.halo;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Resources;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceDrawerActivityAlt;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carbon.settings.R;
import com.carbon.settings.SettingsPreferenceFragment;
import com.carbon.settings.Utils;

// import htc one stuffs
import com.carbon.settings.fragments.halo.*;
// import the other stuffs

import java.util.ArrayList;

public class HaloTab extends SettingsPreferenceFragment {

    private static final String TAG = "Halo_Category";

    PagerTabStrip mPagerTabStrip;
    ViewPager mViewPager;

    ViewGroup mContainer;

    String titleString[];

    static Bundle mSavedState;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mContainer = container;

        View view = inflater.inflate(R.layout.pager_tab, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.viewPager);
        mPagerTabStrip = (PagerTabStrip) view.findViewById(R.id.pagerTabStrip);

        HaloAdapter HaloAdapter = new HaloAdapter(getFragmentManager());
        mViewPager.setAdapter(HaloAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!Utils.isTablet(getActivity())) {
            mContainer.setPadding(0, 0, 0, 0);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    class HaloAdapter extends FragmentPagerAdapter {
        String titles[] = getTitles();
        private Fragment frags[] = new Fragment[titles.length];

        public HaloAdapter(FragmentManager fm) {
            super(fm);
            frags[0] = new HaloStatus();
            frags[1] = new HaloAdvanced();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return frags[position];
        }

        @Override
        public int getCount() {
            return frags.length;
        }
    }

    private String[] getTitles() {
        titleString = new String[]{
                getString(R.string.halo_general_category),
                getString(R.string.halo_advanced_category)};
        return titleString;
    }
}
