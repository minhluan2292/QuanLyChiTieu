package com.example.nhatlam.nhomnm_quanlychitieu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import java.util.List;
import java.util.Vector;

public class Acti0 extends FragmentActivity {
	private PagerAdapter mPagerAdapter;  
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.viewpager_layout);
	
	  	
	  //ActionBar actionbar = getActionBar();
	  //actionbar.hide();
	  initialisePaging();
	}

	public void initialisePaging() {
		// TODO Auto-generated method stub
		List<Fragment> fragments = new Vector<Fragment>();
		fragments.add(Fragment.instantiate(this,Fragment1.class.getName()));
		fragments.add(Fragment.instantiate(this,Fragment2.class.getName()));
		fragments.add(Fragment.instantiate(this,Fragment3.class.getName()));
		mPagerAdapter =new PagerAdapter(this.getSupportFragmentManager(), fragments);
		
		ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
		pager.setAdapter(mPagerAdapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}