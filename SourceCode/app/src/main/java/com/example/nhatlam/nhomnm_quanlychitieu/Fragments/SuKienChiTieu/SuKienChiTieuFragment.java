package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.SuKienChiTieu;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.fragmentAdapter;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._vi;
import com.example.nhatlam.nhomnm_quanlychitieu.R;
/**
 * Created by MinhLuan on 2/4/2017.
 */

public class SuKienChiTieuFragment extends Fragment {
    ViewPager vPaper;
    TabLayout tabLayout;
    fragmentAdapter adapter;
    FragmentManager fragmentManager;
    _vi vi;

    public SuKienChiTieuFragment() {
        // Required empty public constructor
    }

    public SuKienChiTieuFragment(_vi vi) {
        // Required empty public constructor
        this.vi = vi;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sukienchitieu, container, false);
        tabLayout = (TabLayout) v.findViewById(R.id.tabSuKienChiTieu);
        vPaper = (ViewPager)v.findViewById(R.id.viewPaperSuKienChiTieu);
        fragmentManager = getChildFragmentManager();
        adapter = new fragmentAdapter(fragmentManager);
        adapter.addFragment(new SKCTNgayFragment(vi),"Chi Tiêu Ngày");
       // adapter.addFragment(new LoaiViFragment(),"Loại Ví");

        vPaper.setAdapter(adapter);
        tabLayout.setupWithViewPager(vPaper);

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