package com.example.nhatlam.nhomnm_quanlychitieu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {

	
	private List<Fragment> fragments;

	public PagerAdapter(FragmentManager fm, List<Fragment> fragments) {
		super(fm);
		// TODO Auto-generated constructor stub
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return this.fragments.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return this.fragments.size();
	}

}
