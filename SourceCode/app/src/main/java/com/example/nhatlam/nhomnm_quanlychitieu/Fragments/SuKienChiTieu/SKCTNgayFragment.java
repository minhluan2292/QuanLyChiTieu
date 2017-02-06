package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.SuKienChiTieu;

/**
 * Created by MinhLuan on 2/5/2017.
 */

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.DatePicker;

import com.example.nhatlam.nhomnm_quanlychitieu.AsyncTask.AddViTask;
import com.example.nhatlam.nhomnm_quanlychitieu.Database.databasehelper;
import com.example.nhatlam.nhomnm_quanlychitieu.Database.dbstring;
import com.example.nhatlam.nhomnm_quanlychitieu.Models.*;
import com.example.nhatlam.nhomnm_quanlychitieu.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static com.example.nhatlam.nhomnm_quanlychitieu.Activities.MainActivity.user;


/**
 * A simple {@link Fragment} subclass.
 */
public class SKCTNgayFragment extends Fragment implements MyDialogFragment.OnDateSetListener{
    RecyclerView rView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    static databasehelper db;
    List<_giaodich> lst;
    static List<SuKienChiTieuProvider> lstThuChi;
    static View v;
    static View vContain;
    static View vButtonShow;
    static View vbox;

    static Spinner spinnerCatagory;
    static Spinner spinnerEditCatagory;

    static EditText txtSoTien;
    static EditText txtGhiChu;
    static EditText txtEditSoTien;
    static EditText txtEditGhiChu;

    static TextView viewNgay;
    static TextView viewEditNgay;


    _vi vi;

    static int editPosition;

    public SKCTNgayFragment() {
        // Required empty public constructor

    }

    public SKCTNgayFragment(_vi vi) {
        // Required empty public constructor
        this.vi = vi;

    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_skct_ngay, container, false);

        spinnerCatagory = (Spinner)v.findViewById(R.id.spinnerCatagory);
        spinnerEditCatagory = (Spinner)v.findViewById(R.id.spinnerEditCatagory);

        txtSoTien = (EditText)v.findViewById(R.id.txtSoTien);
        txtGhiChu = (EditText)v.findViewById(R.id.txtGhiChu);

        txtEditSoTien = (EditText)v.findViewById(R.id.editSoTien);
        txtEditGhiChu = (EditText)v.findViewById(R.id.editGhiChu);

        viewNgay = (TextView)v.findViewById(R.id.viewNgay);
        viewEditNgay = (TextView)v.findViewById(R.id.viewEditNgay);


        vContain = v.findViewById(R.id.layoutAddThuChi);
        vButtonShow = v.findViewById(R.id.layoutbtnAddThuChi);
        vbox = v.findViewById(R.id.ThuChiEditBox);


        Button btnAcceptEdit=(Button) v.findViewById(R.id.btnEditThuChi);
        Button btnCancelEdit = (Button)v.findViewById(R.id.btnCancelEditThuChi);
        Button btnDelete = (Button)v.findViewById(R.id.btnDeleteThuChi);

        Button btnCancel = (Button) v.findViewById(R.id.btnCancelAddThuChi);

        rView = (RecyclerView)v.findViewById(R.id.lstThuChi);
        Button btnAdd = (Button)v.findViewById(R.id.btnAddNewThuChi);
        Button btnShowAddChi = (Button)v.findViewById(R.id.btnShowContainChi);
        Button btnShowAddThu = (Button)v.findViewById(R.id.btnShowContainThu);




        ///////////////////////////////////set adapter first/////////////////////////////////////////////
        db = new databasehelper(getActivity().getApplicationContext());

        lst= db.laydanhsachGiaodich(vi);
        lstThuChi = new ArrayList<SuKienChiTieuProvider>();
        for(int i=0;i<lst.size();i++){
            SuKienChiTieuProvider cateThuChi = new SuKienChiTieuProvider(lst.get(i).getGiaodich_id(),R.drawable.chi,db.getCategory(lst.get(i).getCategory_id()).getCategory_name(),lst.get(i).getSotien());
            lstThuChi.add(cateThuChi);
        }
        adapter = new SuKienChiTieuRecyclerAdapter(getActivity().getApplicationContext(),lstThuChi);
        rView.setHasFixedSize(true);

        layoutManager= new LinearLayoutManager(getActivity());
        rView.setLayoutManager(layoutManager);
        rView.setAdapter(adapter);
        //////////////////////////////////set adapter first/////////////////////////////////////////////

        btnAcceptEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _vi vi = new _vi();
                vi.setVi_id(editPosition);
                //vi.setVi_name(txtEditViName.getText().toString());
               // vi.setSotien(txtEditViSoTien.getText().toString());
                if(db.chinhsuaVi(vi)==true){
                    Toast.makeText(getActivity().getApplicationContext(),"Chỉnh sửa thành công!",Toast.LENGTH_SHORT);
                  //  txtEditViName.setText("");
                  //  txtEditViSoTien.setText("");
                    vContain.setVisibility(View.GONE);
                    vButtonShow.setVisibility(View.VISIBLE);
                    vbox.setVisibility(View.GONE);
                    refreshData();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Chỉnh sửa thất bại!",Toast.LENGTH_SHORT);
                }
            }
        });

     /*   btnCancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEditViName.setText("");
                txtEditViSoTien.setText("");
                vContain.setVisibility(View.GONE);
                vButtonShow.setVisibility(View.VISIBLE);
                vbox.setVisibility(View.GONE);
            }
        });*/

      /*  btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _vi vi = new _vi();
                vi.setVi_id(editPosition);
                if(db.xoaVi(vi)==true){
                    Toast.makeText(getActivity().getApplicationContext(),"Xóa thành công!",Toast.LENGTH_SHORT);
                    txtEditViName.setText("");
                    txtEditViSoTien.setText("");
                    vContain.setVisibility(View.GONE);
                    vButtonShow.setVisibility(View.VISIBLE);
                    vbox.setVisibility(View.GONE);
                    refreshData();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Xóa thất bại!",Toast.LENGTH_SHORT);
                }
            }
        });*/


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  txtAddViName.setText("");
              //  txtAddViSoTien.setText("");
                vContain.setVisibility(View.GONE);
                vButtonShow.setVisibility(View.VISIBLE);
            }
        });


        btnShowAddChi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                vContain.setVisibility(View.VISIBLE);
                vButtonShow.setVisibility(View.GONE);
                vbox.setVisibility(View.GONE);
                ArrayList<ThuChiData> list=new ArrayList<>();
                for (_category cate:db.laydanhsachCategory(0)) {
                    System.out.println(cate.getCategory_id());
                    System.out.println(cate.getCategory_name());
                    list.add(new ThuChiData(cate.getCategory_id(),cate.getCategory_name(),R.drawable.chi));
                }
                SpinnerThuChiAdapter adapter=new SpinnerThuChiAdapter(getActivity().getApplicationContext(),list);
                spinnerCatagory.setAdapter(adapter);
                }
        });
        btnShowAddThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vContain.setVisibility(View.VISIBLE);
                vButtonShow.setVisibility(View.GONE);
                vbox.setVisibility(View.GONE);
            }
        });

        /*btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String viname = txtAddViName.getText().toString();
                String sotien = txtAddViSoTien.getText().toString();
                String subval = viname.replaceAll("\\s","");
                AddViTask task;
                if(subval.equals("")==false) {
                    task = new AddViTask(getActivity().getApplicationContext(), dbstring.TABLE_VI, user.getUser_id(), viname, 0,sotien,0);
                    task.execute();
                    vContain.setVisibility(View.GONE);
                    vbox.setVisibility(View.GONE);
                    vButtonShow.setVisibility(View.VISIBLE);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    refreshData();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Tên ví không bỏ trống!",Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        //Hien thi datepicker
        viewNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(v);
            }
        });
        viewEditNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(v);
            }
        });


/*
        rView.addOnItemTouchListener(new com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Catagory.RecyclerItemClickListener(getActivity().getApplicationContext(),
                rView, new com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Catagory.RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                vbox.setVisibility(View.VISIBLE);
                vContain.setVisibility(View.GONE);
                vButtonShow.setVisibility(View.GONE);

                editPosition=lstThuChi.get(position).getId();

                EditText edit = (EditText)v.findViewById(R.id.editnameVi);
                EditText edit2 = (EditText)v.findViewById(R.id.editsotienVi);
                SuKienChiTieuProvider provider = lstThuChi.get(position);
                edit.setText(provider.getName());
                edit2.setText(provider.getSotien());
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));*/


        return v;
    }


    public void refreshData(){
        lst= db.laydanhsachGiaodich(vi);
        lstThuChi = new ArrayList<SuKienChiTieuProvider>();
        for(int i=0;i<lst.size();i++){
           // SuKienChiTieuProvider cateThuChi = new SuKienChiTieuProvider(lst.get(i).getVi_id(),R.drawable.chi,lst.get(i).getVi_name(),lst.get(i).getSotien());
           // lstThuChi.add(cateThuChi);
        }

        adapter = new SuKienChiTieuRecyclerAdapter(getActivity().getApplicationContext(),lstThuChi);
        rView.setAdapter(adapter);

    }

    //Hien thi ngay

    public void datePicker(View view)  {
        MyDialogFragment myDialogFragment = new MyDialogFragment();
        myDialogFragment.show(getFragmentManager(), "DatePicker");
        myDialogFragment.setOnDateSetListener(this);
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar cal = new GregorianCalendar(year, month,dayOfMonth);
        setDate(cal);
    }

    private void setDate(final Calendar calendar){
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);
        ((TextView) v.findViewById(R.id.viewNgay)).setText(dateFormat.format(calendar.getTime()));
    }

    /*public static class DatePickerFragment extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(), this , year, month, day);
        }
    }*/
}