package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.SoNo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatlam.nhomnm_quanlychitieu.Database.databasehelper;
import com.example.nhatlam.nhomnm_quanlychitieu.Fragments.SoTietKiem.SoTietKiemArrayAdapter;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._loaino;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._sono;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._sotietkiem;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._user;
import com.example.nhatlam.nhomnm_quanlychitieu.Models._vi;
import com.example.nhatlam.nhomnm_quanlychitieu.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Nghia Tran on 2017-02-06.
 */

public class SoNoFragment extends Fragment implements View.OnClickListener{

    _user user;
    databasehelper db;
    List<_vi> lstVi;
    List<_sono> listSoNo;
    _sono soNoHienTai;

    View v;
    View vBoxMainSoNo;
    View vBoxAddSoNo;
    View vBoxDetailSoNo;

    Button btnThem;
    Button btnLuu;
    Button btnTra;
    Button btnNhan;
    Button btnTroVeThem;
    Button btnTroVeChiTiet;

    EditText txtDoiTuongThem;
    EditText txtSoTienThem;
    EditText txtGhiChuThem;
    EditText txtSoTienTra;
    EditText txtSoTienNhan;

    TextView txtDoiTuongChiTiet;
    TextView txtTrangThai;
    TextView txtSoTienChiTiet;

    Spinner spViNo;
    Spinner spLoai;

    ListView lstSoNo;

    public SoNoFragment(_user user) {
        // Required empty public constructor
        this.user=user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_so_no, container, false);
        db=new databasehelper(getActivity().getApplicationContext());

        vBoxMainSoNo = v.findViewById(R.id.vBoxMainSoNo);
        vBoxAddSoNo = v.findViewById(R.id.vBoxAddSoNo);
        vBoxDetailSoNo = v.findViewById(R.id.vBoxDetailSoNo);

        vBoxMainSoNo.setVisibility(View.VISIBLE);
        vBoxAddSoNo.setVisibility(View.GONE);
        vBoxDetailSoNo.setVisibility(View.GONE);

        txtDoiTuongThem = (EditText)v.findViewById(R.id.txtDoiTuongThem);
        txtSoTienThem = (EditText)v.findViewById(R.id.txtSoTienThem);
        txtGhiChuThem = (EditText)v.findViewById(R.id.txtGhiChuThem);
        txtSoTienTra = (EditText)v.findViewById(R.id.txtSoTienTra);
        txtSoTienNhan = (EditText)v.findViewById(R.id.txtSoTienNhan);

        txtDoiTuongChiTiet = (TextView)v.findViewById(R.id.txtDoiTuongChiTiet);
        txtTrangThai = (TextView)v.findViewById(R.id.txtTrangThai);
        txtSoTienChiTiet = (TextView)v.findViewById(R.id.txtSoTienChiTiet);

        spViNo = (Spinner) v.findViewById(R.id.spViNo);
        spLoai = (Spinner) v.findViewById(R.id.spLoai);

        lstSoNo = (ListView) v.findViewById(R.id.lstSoNo);

        btnThem = (Button)v.findViewById(R.id.btnThem);
        btnLuu = (Button)v.findViewById(R.id.btnLuu);
        btnTra = (Button)v.findViewById(R.id.btnTra);
        btnNhan = (Button)v.findViewById(R.id.btnNhan);
        btnTroVeThem = (Button)v.findViewById(R.id.btnTroVeThem);
        btnTroVeChiTiet = (Button)v.findViewById(R.id.btnTroVeChiTiet);

        btnThem.setOnClickListener(this);
        btnLuu.setOnClickListener(this);
        btnTra.setOnClickListener(this);
        btnNhan.setOnClickListener(this);
        btnTroVeThem.setOnClickListener(this);
        btnTroVeChiTiet.setOnClickListener(this);

        lstVi = db.laydanhsachVi(user);
        String array_spinner[];
        array_spinner = new String[lstVi.size()];

        for(int i=0;i<lstVi.size();i++){
            array_spinner[i] = lstVi.get(i).getVi_name();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item, array_spinner);
        spViNo.setAdapter(adapter);
        spViNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CapNhatDanhSachSoNo();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        _vi vi_Choose = lstVi.get(0);
        listSoNo = db.laydanhsachSono(vi_Choose);
        Mesage(String.valueOf(listSoNo.size()));
        if(listSoNo.size() > 0){
            //Toast.makeText(this.getActivity(),Integer.toString(listSoTietKiem.size()),Toast.LENGTH_SHORT).show();
            //ArrayAdapter<_sotietkiem> adapter_Sotietkiem = new ArrayAdapter<_sotietkiem>(this.getActivity(),R.layout.sotietkiem_item,listSoTietKiem);
            lstSoNo.setAdapter(new SoNoArrayAdapter(this.getActivity(),listSoNo));

            lstSoNo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    soNoHienTai = listSoNo.get(position);
                    //Mesage(soTietKiem.getSotietkiem_name());
                    changeVisibleView(View.GONE, View.GONE, View.VISIBLE);
                    txtDoiTuongChiTiet.setText(soNoHienTai.getDoituong());
                    int soTienHienTai = Integer.parseInt(soNoHienTai.getSotien());

                    ThayDoiTrangThaiChiTiet(soTienHienTai);

                }
            });
        }

        String arrLoai[]={"Cho vay","Nợ"};
        ArrayAdapter<String> adapterLoai =new ArrayAdapter<String>( this.getActivity(),android.R.layout.simple_spinner_item,arrLoai );

        adapterLoai.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spLoai.setAdapter(adapterLoai);

        /*_loaino loaiNo = new _loaino();
        loaiNo.setLoaino_name("Cho vay no");
        db.themLoaino(loaiNo);
        Mesage(String.valueOf(db.laydanhsachLoaino().size()));
        */
        return v;
    }

    private void ThayDoiTrangThaiChiTiet(int soTienHienTai){
        if(soTienHienTai < 0){
            txtTrangThai.setText("Nợ");
            txtSoTienChiTiet.setText(String.valueOf(soTienHienTai * -1));
        }else{
            txtTrangThai.setText("Cho vay");
            txtSoTienChiTiet.setText(String.valueOf(soTienHienTai));
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.btnThem:{
                changeVisibleView(View.GONE, View.VISIBLE, View.GONE);
                break;
            }
            case R.id.btnTroVeChiTiet:{
                changeVisibleView(View.VISIBLE, View.GONE, View.GONE);
                break;
            }
            case R.id.btnTroVeThem:{
                changeVisibleView(View.VISIBLE, View.GONE, View.GONE);
                break;
            }
            case R.id.btnLuu:{
                ThemSoNo();
                break;
            }
            case R.id.btnTra:{
                TraTienNo();
                break;
            }
            case R.id.btnNhan:{
                NhanTienTraNo();
                break;
            }
        }
    }

    private void TraTienNo(){
        int soTienTra = Integer.parseInt(txtSoTienTra.getText().toString());
        _vi vi_temp = db.getVI(lstVi.get(spViNo.getSelectedItemPosition()).getVi_id());
        int soTienTrongVi = Integer.parseInt(vi_temp.getSotien());
        if (soTienTrongVi >= soTienTra) {
            soTienTrongVi -= soTienTra;
            vi_temp.setSotien(String.valueOf(soTienTrongVi));
            db.chinhsuaVi(vi_temp);
            int soTienTrongSoNo = Integer.parseInt(soNoHienTai.getSotien());
            soTienTrongSoNo += soTienTra;
            soNoHienTai.setSotien(String.valueOf(soTienTrongSoNo));
            db.chinhsuaSono(soNoHienTai);
            ThayDoiTrangThaiChiTiet(soTienTrongSoNo);
        }else{
            Mesage("Số tiền trong ví không đủ.");
        }
    }

    private  void NhanTienTraNo(){
        int soTienNhan = Integer.parseInt(txtSoTienNhan.getText().toString());
        _vi vi_temp = db.getVI(lstVi.get(spViNo.getSelectedItemPosition()).getVi_id());
        int soTienTrongVi = Integer.parseInt(vi_temp.getSotien());
        soTienTrongVi += soTienNhan;
        vi_temp.setSotien(String.valueOf(soTienTrongVi));
        db.chinhsuaVi(vi_temp);

        int soTienTrongSoNo = Integer.parseInt(soNoHienTai.getSotien());
        soTienTrongSoNo -= soTienNhan;
        soNoHienTai.setSotien(String.valueOf(soTienTrongSoNo));
        db.chinhsuaSono(soNoHienTai);
        ThayDoiTrangThaiChiTiet(soTienTrongSoNo);
    }

    private void ThemSoNo(){
        String doiTuong = txtDoiTuongThem.getText().toString();
        int loaiNo = spLoai.getSelectedItemPosition();
        int soTien = Integer.parseInt(txtSoTienThem.getText().toString());
        String ghiChu = txtGhiChuThem.getText().toString();

        if (loaiNo == 0){
            //Cho vay
            _vi vi_temp = db.getVI(lstVi.get(spViNo.getSelectedItemPosition()).getVi_id());
            int soTienTrongVi = Integer.parseInt(vi_temp.getSotien());
            if (soTienTrongVi >= soTien) {
                soTienTrongVi -= soTien;
                vi_temp.setSotien(String.valueOf(soTienTrongVi));
                db.chinhsuaVi(vi_temp);
            }else{
                Mesage("Số tiền trong ví không đủ.");
                return;
            }
        }else{
            //Nợ
            _vi vi_temp = db.getVI(lstVi.get(spViNo.getSelectedItemPosition()).getVi_id());
            vi_temp.setSotien(String.valueOf(soTien));
            db.chinhsuaVi(vi_temp);
            soTien *= -1;
        }

        _sono soNo = new _sono();
        soNo.setVi_id(spViNo.getSelectedItemPosition());
        soNo.setSotien(String.valueOf(soTien));
        soNo.setGhichu(ghiChu);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = sdf.format(new Date());
        soNo.setNgaygiaodich(currentDate);
        soNo.setDoituong(doiTuong);
        soNo.setDiadiem("");
        soNo.setThoihan("");
        soNo.setTrangthai(0);
        //soNo.setLoaino_id(0);

        if(db.themSono(soNo)==true){
            Toast.makeText(this.getActivity(),"Thêm thành công!",Toast.LENGTH_SHORT).show();
            changeVisibleView(View.VISIBLE, View.GONE, View.GONE);
        }else{
            Toast.makeText(this.getActivity(),"Thêm thất bại!",Toast.LENGTH_SHORT).show();
        }
    }

    private void Mesage(String mess){
        Toast.makeText(this.getActivity(),mess,Toast.LENGTH_SHORT).show();
    }

    private void changeVisibleView(int main, int add, int detail){
        if(main == View.VISIBLE){
            CapNhatDanhSachSoNo();
        }else if(add == View.VISIBLE){
            //txtTenSo.setText("");
            //txtTienMucTieu.setText("");
            //txtTienBanDau.setText("");
            //txtGhiChu.setText("");
        }else if(detail == View.VISIBLE){
            //txtGuiVao.setText("");
            //txtRutRa.setText("");
        }
        vBoxMainSoNo.setVisibility(main);
        vBoxAddSoNo.setVisibility(add);
        vBoxDetailSoNo.setVisibility(detail);
    }

    private void CapNhatDanhSachSoNo(){
        _vi vi_Choose = lstVi.get(spViNo.getSelectedItemPosition());
        listSoNo = db.laydanhsachSono(vi_Choose);
        lstSoNo.setAdapter(new SoNoArrayAdapter(this.getActivity(),listSoNo));
        //Log.d(null, Integer.toString(listSoNo.size()));
    }
}
