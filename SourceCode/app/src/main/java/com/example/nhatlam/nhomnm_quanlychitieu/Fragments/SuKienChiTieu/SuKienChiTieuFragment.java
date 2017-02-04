package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.SuKienChiTieu;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Catagory.ChiTieuFragment;
import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Catagory.NoFragment;
import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Catagory.ThuFragment;
import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.fragmentAdapter;
import com.example.nhatlam.nhomnm_quanlychitieu.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SuKienChiTieuFragment extends Fragment {
    ViewPager vPaper;
    TabLayout tabLayout;

    public SuKienChiTieuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sukienchitieu, container, false);
        tabLayout = (TabLayout) v.findViewById(R.id.tabSuKienChiTieu);
        vPaper = (ViewPager)v.findViewById(R.id.viewPaperSuKienChiTieu);

        fragmentAdapter adapter = new fragmentAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new ChiTieuFragment(),"Chi tiêu ngày");
        adapter.addFragment(new ThuFragment(),"Chi tiêu tháng");
        adapter.addFragment(new ThuFragment(),"Chi tiêu tháng");
        adapter.addFragment(new NoFragment(),"Chi tiêu dự kiến");
        vPaper.setAdapter(adapter);
        tabLayout.setupWithViewPager(vPaper);

        return v;
    }


}
