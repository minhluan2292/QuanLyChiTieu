package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Catagory;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.fragmentAdapter;
import com.example.nhatlam.nhomnm_quanlychitieu.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CatagoryFragment extends Fragment {
    ViewPager vPaper;
    TabLayout tabLayout;
    LayoutInflater inflater;
    ViewGroup container;
    View v;
    FragmentManager fragmentManager;

    public CatagoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(null,"Oncreated");
        this.inflater=inflater;
        this.container=container;
        fragmentManager = getChildFragmentManager();


        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_catagory, container, false);
        tabLayout = (TabLayout) v.findViewById(R.id.tabCatagory);
        vPaper = (ViewPager)v.findViewById(R.id.viewPaperCatagory);

        ArrayList<Fragment> lstFragment= new ArrayList<>();
        ArrayList<String> lstTitle= new ArrayList<>();


        lstFragment.add(new ChiTieuFragment());
        lstTitle.add("Chi Tiêu");

        lstFragment.add(new ThuFragment());
        lstTitle.add("Khoản thu");
        lstFragment.add(new NoFragment());
        lstTitle.add("Nợ");
        final fragmentAdapter adapter = new fragmentAdapter(fragmentManager,lstFragment,lstTitle);

        vPaper.setAdapter(adapter);
        tabLayout.setupWithViewPager(vPaper);
        Log.d(null,"setAdapter");
        vPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        return v;
    }

}
