package com.example.nhatlam.nhomnm_quanlychitieu.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by NHATLAM on 2/2/2017.
 */

public class fragmentAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> lstFragment= new ArrayList<>();
    ArrayList<String> lstTitle= new ArrayList<>();

    public void addFragment(Fragment fragment, String title){
        this.lstFragment.add(fragment);
        this.lstTitle.add(title);
    }

    public fragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return lstFragment.get(position);
    }

    @Override
    public int getCount() {
        return lstFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return lstTitle.get(position);
    }
}
