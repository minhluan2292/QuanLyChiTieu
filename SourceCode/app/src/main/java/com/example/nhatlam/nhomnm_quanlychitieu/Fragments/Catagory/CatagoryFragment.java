package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Catagory;


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
 * A simple {@link Fragment} subclass.
 */
public class CatagoryFragment extends Fragment {
    ViewPager vPaper;
    TabLayout tabLayout;

    public CatagoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_catagory, container, false);
        tabLayout = (TabLayout) v.findViewById(R.id.tabCatagory);
        vPaper = (ViewPager)v.findViewById(R.id.viewPaperCatagory);

        fragmentAdapter adapter = new fragmentAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new ChiTieuFragment(),"Chi tiêu");
        adapter.addFragment(new ThuFragment(),"Khoản thu");
        adapter.addFragment(new NoFragment(),"Nợ");
        vPaper.setAdapter(adapter);
        tabLayout.setupWithViewPager(vPaper);

        return v;
    }


}
