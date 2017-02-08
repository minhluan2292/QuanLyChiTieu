package com.example.nhatlam.nhomnm_quanlychitieu.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatlam.nhomnm_quanlychitieu.Database.databasehelper;
import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Catagory.CatagoryFragment;
import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.ChuyenTien.ChuyenTienFragment;
import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.DoiTienTe.DoiTienTeFragment;
import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.SoNo.SoNoFragment;
import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.SoTietKiem.SoTietKiemFragment;
import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.SuKienChiTieu.SuKienChiTieuFragment;
import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.User.ThongtinuserFragment;
import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Vi.QLViFragment;
import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Vi.spinnerViAdapter;
import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Vi.viData;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._user;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._vi;
import com.example.nhatlam.nhomnm_quanlychitieu.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private databasehelper db;
    public static _user user;
    FrameLayout layoutContain;
    FragmentManager fragmentManager;
    private Spinner spinner_nav;
    static List<_vi> lstVi;
    static _vi currentVi;
    static spinnerViAdapter adapterVi;

    public static _vi getCurrentVi() {
        return currentVi;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);


        layoutContain = (FrameLayout) findViewById(R.id.content_main);
        fragmentManager = getSupportFragmentManager();

        //TextView txtdisplay = (TextView)findViewById(R.id.txtDisplay);
        TextView txtuser;


        spinner_nav = (Spinner) findViewById(R.id.spinner_nav);
        db = new databasehelper(getApplicationContext());
        Intent i = getIntent();
        if(i.getExtras().getSerializable("user")!=null){
            user =  (_user)i.getExtras().getSerializable("user");
            user =db.getUser(user.getUser_id());
            lstVi=db.laydanhsachVi(user);
            if(lstVi.size()>0) {
                currentVi = lstVi.get(0);
            }else
                currentVi=null;
            ArrayList<viData> vidatas = new ArrayList<viData>();

            for (_vi v:lstVi) {
                viData vdata = new viData();
                vdata.setId(v.getVi_id());
                vdata.setViname(v.getVi_name());
                vidatas.add(vdata);
            }

            adapterVi = new spinnerViAdapter(getApplicationContext(),vidatas);
            if(adapterVi!=null) {
                spinner_nav.setVisibility(View.VISIBLE);
                spinner_nav.setAdapter(adapterVi);
            }
            else
                spinner_nav.setVisibility(View.GONE);
        }

        spinner_nav.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentVi = db.getVI(adapterVi.getItem(position).getId());
                //Toast.makeText(getApplicationContext(),"Ví " + currentVi.getVi_name(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        txtuser = (TextView) header.findViewById(R.id.txtUser);

        txtuser.setText(user.getUsername());

    }
    public void reloadSpinnerVi(){
        lstVi=db.laydanhsachVi(user);

        ArrayList<viData> vidatas = new ArrayList<viData>();

        for (_vi v:lstVi) {
            viData vdata = new viData();
            vdata.setId(v.getVi_id());
            vdata.setViname(v.getVi_name());
            vidatas.add(vdata);
        }

        adapterVi = new spinnerViAdapter(getApplicationContext(),vidatas);
        if(adapterVi!=null) {
            spinner_nav.setVisibility(View.VISIBLE);
            spinner_nav.setAdapter(adapterVi);
        }
        else
            spinner_nav.setVisibility(View.GONE);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        int id = item.getItemId();


        /*
        Fragment f = getSupportFragmentManager().findFragmentById(layoutContain.getId());
        if(f!=null) {
            fragmentTransaction.remove(f);
            f.onDestroy();
        }
        */

        if (id == R.id.nav_Thongtincanhan) {
            ThongtinuserFragment fragment = new ThongtinuserFragment(user);
            fragmentTransaction.replace(layoutContain.getId(),fragment).addToBackStack(null);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_Catagory) {
            CatagoryFragment fragment = new CatagoryFragment();
            fragmentTransaction.replace(layoutContain.getId(),fragment).addToBackStack(null);
            fragmentTransaction.commit();
        } else if (id == R.id.nav_SuKienChiTieu) {
            if(lstVi.size()>0) {
                SuKienChiTieuFragment fragment = new SuKienChiTieuFragment(currentVi);
                fragmentTransaction.replace(layoutContain.getId(), fragment).addToBackStack(null);
                fragmentTransaction.commit();
            }else{
                Toast.makeText(getApplicationContext(),"Hãy tạo ví trước khi thêm giao dịch!",Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.nav_Vi) {
            QLViFragment fragment = new QLViFragment(user);
            fragmentTransaction.replace(layoutContain.getId(), fragment).addToBackStack(null);
            fragmentTransaction.commit();

        } else  if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
            user = db.dangxuatUser(user);
            Intent i = new Intent(this.getApplicationContext(),DangNhapActivity.class);
            startActivity(i);
        }else if (id == R.id.nav_SoTietKiem) {
            SoTietKiemFragment fragment = new SoTietKiemFragment(user);
            fragmentTransaction.replace(layoutContain.getId(),fragment).addToBackStack(null);
            fragmentTransaction.commit();
        }else if (id == R.id.nav_SoNo) {
            SoNoFragment fragment = new SoNoFragment(user);
            fragmentTransaction.replace(layoutContain.getId(),fragment).addToBackStack(null);
            fragmentTransaction.commit();
        }else if (id == R.id.nav_ChuyenTien) {
            ChuyenTienFragment fragment = new ChuyenTienFragment();
            fragmentTransaction.replace(layoutContain.getId(),fragment).addToBackStack(null);
            fragmentTransaction.commit();
        }else if (id == R.id.nav_DoiTienTe) {
            DoiTienTeFragment fragment = new DoiTienTeFragment();
            fragmentTransaction.replace(layoutContain.getId(),fragment).addToBackStack(null);
            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
