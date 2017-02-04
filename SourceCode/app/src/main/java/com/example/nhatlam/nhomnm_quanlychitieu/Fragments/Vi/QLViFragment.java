package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Vi;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.fragmentAdapter;
import com.example.nhatlam.nhomnm_quanlychitieu.R;
/**
 * Created by MinhLuan on 2/4/2017.
 */

public class QLViFragment extends Fragment {
    ViewPager vPaper;
    TabLayout tabLayout;

    public QLViFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quanlyvi, container, false);
        tabLayout = (TabLayout) v.findViewById(R.id.tabQLVi);
        vPaper = (ViewPager)v.findViewById(R.id.viewPaperQLVi);

        fragmentAdapter adapter = new fragmentAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new ViFragment(),"Ví");
        adapter.addFragment(new LoaiViFragment(),"Loại Ví");

        vPaper.setAdapter(adapter);
        tabLayout.setupWithViewPager(vPaper);

        return v;
    }


}