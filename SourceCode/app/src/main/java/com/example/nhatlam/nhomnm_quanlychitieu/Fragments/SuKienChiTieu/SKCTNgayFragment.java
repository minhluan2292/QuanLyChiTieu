package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.SuKienChiTieu;

/**
 * Created by MinhLuan on 2/5/2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatlam.nhomnm_quanlychitieu.Activities.MainActivity;
import com.example.nhatlam.nhomnm_quanlychitieu.AsyncTask.AddSuKienChiTieuTask;
import com.example.nhatlam.nhomnm_quanlychitieu.Database.databasehelper;
import com.example.nhatlam.nhomnm_quanlychitieu.Database.dbstring;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._category;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._giaodich;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._vi;
import com.example.nhatlam.nhomnm_quanlychitieu.R;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


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
    _category catagoryadd;

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
            SuKienChiTieuProvider cateThuChi = new SuKienChiTieuProvider(lst.get(i).getGiaodich_id(),R.drawable.thu,db.getCategory(lst.get(i).getCategory_id()).getCategory_name(),
                    lst.get(i).getSotien(),lst.get(i).getNgaygiaodich(),db.getCategory(lst.get(i).getCategory_id()).getParent(),lst.get(i).getGhichu());
            lstThuChi.add(cateThuChi);
        }
        adapter = new SuKienChiTieuRecyclerAdapter(getActivity().getApplicationContext(),lstThuChi);
        rView.setHasFixedSize(true);

        layoutManager= new LinearLayoutManager(getActivity());
        rView.setLayoutManager(layoutManager);
        rView.setAdapter(adapter);
        //////////////////////////////////set adapter first/////////////////////////////////////////////

       /* btnAcceptEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _giao vi = new _vi();
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
        });*/

        btnCancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtEditSoTien.setText("");
                txtEditGhiChu.setText("");
                vContain.setVisibility(View.GONE);
                vButtonShow.setVisibility(View.VISIBLE);
                vbox.setVisibility(View.GONE);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _giaodich giaodich = new _giaodich();
                giaodich.setGiaodich_id(editPosition);
                if(db.xoaGiaodich(giaodich)==true){
                    Toast.makeText(getActivity().getApplicationContext(),"Xóa thành công!",Toast.LENGTH_SHORT);
                    txtEditSoTien.setText("");
                    txtEditGhiChu.setText("");
                    vContain.setVisibility(View.GONE);
                    vButtonShow.setVisibility(View.VISIBLE);
                    vbox.setVisibility(View.GONE);
                    refreshData();
                }else{
                    Toast.makeText(getActivity().getApplicationContext(),"Xóa thất bại!",Toast.LENGTH_SHORT);
                }
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSoTien.setText("");
                txtGhiChu.setText("");
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
                    list.add(new ThuChiData(cate.getCategory_id(),cate.getCategory_name(),R.drawable.chi));
                }
                final SpinnerThuChiAdapter adapter=new SpinnerThuChiAdapter(getActivity(),R.layout.spinner_thuchi_layout,R.id.txtSpiner,list);
                spinnerCatagory.setAdapter(adapter);
                spinnerCatagory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view,
                                               int position, long id) {
                        // Here you get the current item (a User object) that is selected by its position
                        ThuChiData thuChiData = adapter.getItem(position);
                        // Here you can do the action you want to...
                        catagoryadd = db.getCategory(thuChiData.getId());
                        System.out.println(thuChiData.getId());
                        System.out.println(db.getCategory(thuChiData.getId()).getParent());
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapter) {  }
                });
                }
        });
        btnShowAddThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vContain.setVisibility(View.VISIBLE);
                vButtonShow.setVisibility(View.GONE);
                vbox.setVisibility(View.GONE);
                ArrayList<ThuChiData> list=new ArrayList<>();
                for (_category cate:db.laydanhsachCategory(1)) {
                    list.add(new ThuChiData(cate.getCategory_id(),cate.getCategory_name(),R.drawable.thu));
                }
                final SpinnerThuChiAdapter adapter=new SpinnerThuChiAdapter(getActivity(),R.layout.spinner_thuchi_layout,R.id.txtSpiner,list);
                spinnerCatagory.setAdapter(adapter);
                spinnerCatagory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view,
                                               int position, long id) {
                        // Here you get the current item (a User object) that is selected by its position
                        ThuChiData thuChiData = adapter.getItem(position);
                        // Here you can do the action you want to...
                        catagoryadd = db.getCategory(thuChiData.getId());
                        System.out.println(thuChiData.getId());
                        System.out.println(db.getCategory(thuChiData.getId()).getParent());

                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapter) {  }
                });
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sotien = txtSoTien.getText().toString();
                String ghichu = txtGhiChu.getText().toString();
                String ngaygiaodich = viewNgay.getText().toString();
                String subval = sotien.replaceAll("\\s","");
                AddSuKienChiTieuTask task;
                try {
                    vi = ((MainActivity) getActivity()).getCurrentVi();
                }catch (Exception e){
                    //
                }
                if(subval.equals("")==false) {
                    task = new AddSuKienChiTieuTask(getActivity().getApplicationContext(), dbstring.TABLE_GIAODICH, vi.getVi_id(),
                            catagoryadd.getCategory_id(), sotien,ngaygiaodich,ghichu);
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
                    Toast.makeText(getActivity().getApplicationContext(),"Số tiền không bỏ trống!",Toast.LENGTH_SHORT).show();
                }
            }
        });

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



        rView.addOnItemTouchListener(new com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Catagory.RecyclerItemClickListener(getActivity().getApplicationContext(),
                rView, new com.example.nhatlam.nhomnm_quanlychitieu.Fragments.Catagory.RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                vbox.setVisibility(View.VISIBLE);
                vContain.setVisibility(View.GONE);
                vButtonShow.setVisibility(View.GONE);

                editPosition=lstThuChi.get(position).getId();

                EditText editSoTien = (EditText)v.findViewById(R.id.editSoTien);
                EditText editGhiChu = (EditText)v.findViewById(R.id.editGhiChu);
                TextView editNgay = (TextView) v.findViewById(R.id.viewEditNgay);
                Spinner editCata = (Spinner) v.findViewById(R.id.spinnerEditCatagory);
                SuKienChiTieuProvider provider = lstThuChi.get(position);
                editSoTien.setText(provider.getSotien());
                editGhiChu.setText(provider.getGhichu());
                editNgay.setText(provider.getNgaygiaodich());
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        }));


        return v;
    }


    public void refreshData(){
        lst= db.laydanhsachGiaodich(vi);
        lstThuChi = new ArrayList<SuKienChiTieuProvider>();
        for(int i=0;i<lst.size();i++){
           SuKienChiTieuProvider cateThuChi = new SuKienChiTieuProvider(lst.get(i).getGiaodich_id(),R.drawable.thu,db.getCategory(lst.get(i).getCategory_id()).getCategory_name(),
                   lst.get(i).getSotien(),lst.get(i).getNgaygiaodich(),db.getCategory(lst.get(i).getCategory_id()).getParent(),lst.get(i).getGhichu());
            lstThuChi.add(cateThuChi);
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