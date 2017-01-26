package com.example.nhatlam.nhomnm_quanlychitieu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by NHATLAM on 1/26/2017.
 */

public class ViewDNDKAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> lstFragment= new ArrayList<>();
    ArrayList<String> lstTitle= new ArrayList<>();

    public void addFragment(Fragment fragment, String title){
        this.lstFragment.add(fragment);
        this.lstTitle.add(title);
    }

    public ViewDNDKAdapter(FragmentManager fm) {
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
