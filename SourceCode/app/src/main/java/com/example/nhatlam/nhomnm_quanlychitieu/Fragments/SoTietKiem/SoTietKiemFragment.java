package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.SoTietKiem;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhatlam.nhomnm_quanlychitieu.Database.databasehelper;
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

public class SoTietKiemFragment extends Fragment implements View.OnClickListener{

    _user user;
    databasehelper db;
    List<_vi> lstVi;
    Spinner spVi;
    Spinner spViTietKiem;
    List<_sotietkiem> listSoTietKiem;
    _sotietkiem soTietKiemHienTai;

    View v;
    View vBoxMain;
    View vBoxThem;
    View vBoxDetail;

    Button btnThem;
    Button btnLuu;
    Button btnThoat;
    Button btnGuiVao;
    Button btnRutRa;
    Button btnXoa;
    Button btnTroVe;

    EditText txtTenSo;
    EditText txtTienMucTieu;
    EditText txtTienBanDau;
    EditText txtGhiChu;
    EditText txtGuiVao;
    EditText txtRutRa;


    ListView lstSoTietKiem;

    TextView txtTenSoTietKiem;
    TextView txtMucTieu;
    TextView txtSoTienCo;
    TextView txtViTietKiem;


    public SoTietKiemFragment(_user user) {
        // Required empty public constructor
        this.user=user;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_so_tiet_kiem, container, false);
        db=new databasehelper(getActivity().getApplicationContext());
        vBoxMain = v.findViewById(R.id.vBoxMain);
        vBoxThem = v.findViewById(R.id.vBoxThem);
        vBoxDetail = v.findViewById(R.id.vBoxDetail);

        vBoxMain.setVisibility(View.VISIBLE);
        vBoxThem.setVisibility(View.GONE);
        vBoxDetail.setVisibility(View.GONE);

        txtTenSo = (EditText)v.findViewById(R.id.txtTenSo);
        txtTienMucTieu = (EditText)v.findViewById(R.id.txtTienMucTieu);
        txtTienBanDau = (EditText)v.findViewById(R.id.txtTienBanDau);
        txtGhiChu = (EditText)v.findViewById(R.id.txtGhiChu);

        txtGuiVao = (EditText)v.findViewById(R.id.txtGuiVao);
        txtRutRa = (EditText)v.findViewById(R.id.txtRutRa);

        spVi = (Spinner) v.findViewById(R.id.spVi);
        spViTietKiem = (Spinner) v.findViewById(R.id.spViTietKiem);
        lstSoTietKiem = (ListView) v.findViewById(R.id.lstSoTietKiem);

        txtTenSoTietKiem = (TextView)v.findViewById(R.id.txtTenSoTietKiem);
        txtMucTieu = (TextView)v.findViewById(R.id.txtMucTieu);
        txtSoTienCo = (TextView)v.findViewById(R.id.txtSoTienCo);
        txtViTietKiem = (TextView)v.findViewById(R.id.txtViTietKiem);

        btnThem = (Button)v.findViewById(R.id.btnThem);
        btnLuu = (Button)v.findViewById(R.id.btnLuu);
        btnThoat = (Button)v.findViewById(R.id.btnThoat);
        btnGuiVao = (Button)v.findViewById(R.id.btnGuiVao);
        btnRutRa = (Button)v.findViewById(R.id.btnRutRa);
        btnXoa = (Button)v.findViewById(R.id.btnXoa);
        btnTroVe = (Button)v.findViewById(R.id.btnTroVe);



        btnThem.setOnClickListener(this);
        btnLuu.setOnClickListener(this);
        btnThoat.setOnClickListener(this);
        btnGuiVao.setOnClickListener(this);
        btnRutRa.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
        btnTroVe.setOnClickListener(this);

        lstVi = db.laydanhsachVi(user);
        String array_spinner[];
        array_spinner = new String[lstVi.size()];

        for(int i=0;i<lstVi.size();i++){
            array_spinner[i] = lstVi.get(i).getVi_name();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item, array_spinner);
        spVi.setAdapter(adapter);
        spViTietKiem.setAdapter(adapter);

        spViTietKiem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CapNhatDanhSachSoTietKiem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //int idxSoTietKiem
        //Toast.makeText(this.getActivity(),Integer.toString(spViTietKiem.getSelectedItemPosition()),Toast.LENGTH_SHORT).show();

        _vi vi_Choose = lstVi.get(0);
        listSoTietKiem = db.laydanhsachSotietkiem(vi_Choose);

        if(listSoTietKiem.size() > 0){
            //Toast.makeText(this.getActivity(),Integer.toString(listSoTietKiem.size()),Toast.LENGTH_SHORT).show();
            //ArrayAdapter<_sotietkiem> adapter_Sotietkiem = new ArrayAdapter<_sotietkiem>(this.getActivity(),R.layout.sotietkiem_item,listSoTietKiem);
            lstSoTietKiem.setAdapter(new SoTietKiemArrayAdapter(this.getActivity(),listSoTietKiem));

            lstSoTietKiem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    soTietKiemHienTai = listSoTietKiem.get(position);
                    //Mesage(soTietKiem.getSotietkiem_name());
                    changeVisibleView(View.GONE, View.GONE, View.VISIBLE);
                    txtTenSoTietKiem.setText(soTietKiemHienTai.getSotietkiem_name());
                    txtMucTieu.setText(soTietKiemHienTai.getMuctieu());
                    txtSoTienCo.setText(soTietKiemHienTai.getSotienbandau());
                    txtViTietKiem.setText(spViTietKiem.getSelectedItem().toString());
                }
            });
        }

        return v;
    }

    private void CapNhatDanhSachSoTietKiem(){
        _vi vi_Choose = lstVi.get(spViTietKiem.getSelectedItemPosition());
        listSoTietKiem = db.laydanhsachSotietkiem(vi_Choose);
        lstSoTietKiem.setAdapter(new SoTietKiemArrayAdapter(this.getActivity(),listSoTietKiem));
    }

    private void Mesage(String mess){
        Toast.makeText(this.getActivity(),mess,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.btnThem:{
                changeVisibleView(View.GONE, View.VISIBLE, View.GONE);
                break;
            }
            case R.id.btnLuu:{
                ThemSoTietKiem();
                break;
            }
            case R.id.btnThoat:{
                changeVisibleView(View.VISIBLE, View.GONE, View.GONE);
                break;
            }
            case R.id.btnTroVe:{
                changeVisibleView(View.VISIBLE, View.GONE, View.GONE);
                break;
            }
            case R.id.btnGuiVao:{
                String strSoTien = txtGuiVao.getText().toString();
                if (strSoTien.length() == 0){
                    Mesage("Chưa nhập số tiền gửi vào.");
                }else{
                    int soTien = Integer.parseInt(strSoTien);
                    _vi vi_temp = db.getVI(lstVi.get(spViTietKiem.getSelectedItemPosition()).getVi_id());
                    int soTienTrongVi = Integer.parseInt(vi_temp.getSotien());
                    if (soTienTrongVi >= soTien){
                        soTienTrongVi -= soTien;
                        vi_temp.setSotien(String.valueOf(soTienTrongVi));
                        db.chinhsuaVi(vi_temp);
                        soTietKiemHienTai.setSotienbandau(String.valueOf(Integer.parseInt(soTietKiemHienTai.getSotienbandau()) + soTien));
                        db.chinhsuaSotietkiem(soTietKiemHienTai);
                        Mesage("Gửi tiền thành công.");
                        txtSoTienCo.setText(soTietKiemHienTai.getSotienbandau());
                    }else{
                        Mesage("Số tiền trong ví không đủ.");
                    }
                }
                break;
            }
            case R.id.btnRutRa:{
                String strSoTien = txtRutRa.getText().toString();
                if (strSoTien.length() == 0){
                    Mesage("Chưa nhập số tiền rút ra.");
                }else{
                    int soTien = Integer.parseInt(strSoTien);
                    int soTienTrongSoTietKiem = Integer.parseInt(soTietKiemHienTai.getSotienbandau());
                    if(soTienTrongSoTietKiem >= soTien){
                        soTienTrongSoTietKiem -= soTien;
                        soTietKiemHienTai.setSotienbandau(String.valueOf(soTienTrongSoTietKiem));
                        db.chinhsuaSotietkiem(soTietKiemHienTai);
                        _vi vi_temp = db.getVI(lstVi.get(spViTietKiem.getSelectedItemPosition()).getVi_id());
                        vi_temp.setSotien(String.valueOf(String.valueOf(Integer.parseInt(vi_temp.getSotien()) + soTien)));
                        db.chinhsuaVi(vi_temp);
                        Mesage("Rút tiền thành công.");
                        txtSoTienCo.setText(soTietKiemHienTai.getSotienbandau());
                    }else{
                        Mesage("Số tiền trong sổ tiết kiệm không đủ.");
                    }
                }
                break;
            }
            case R.id.btnXoa:{
                int soTienTrongSoTietKiem = Integer.parseInt(soTietKiemHienTai.getSotienbandau());
                if(soTienTrongSoTietKiem > 0){
                    _vi vi_temp = db.getVI(lstVi.get(spViTietKiem.getSelectedItemPosition()).getVi_id());
                    vi_temp.setSotien(String.valueOf(String.valueOf(Integer.parseInt(vi_temp.getSotien()) + soTienTrongSoTietKiem)));
                    db.chinhsuaVi(vi_temp);
                    db.xoaSotietkiem(soTietKiemHienTai);
                    Mesage("Xóa thành công.");
                }
                changeVisibleView(View.VISIBLE, View.GONE, View.GONE);
                break;
            }
        }
    }

    private void ThemSoTietKiem(){
        String tenSo = txtTenSo.getText().toString();
        String tienMucTieu = txtTienMucTieu.getText().toString();
        String soTienBanDau = txtTienBanDau.getText().toString();
        String ghiChu = txtGhiChu.getText().toString();
        int idVi = lstVi.get(spVi.getSelectedItemPosition()).getVi_id();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDate = sdf.format(new Date());

        _sotietkiem sotietkiem = new _sotietkiem();
        sotietkiem.setVi_id(idVi);
        sotietkiem.setMuctieu(tienMucTieu);
        sotietkiem.setSotietkiem_name(tenSo);
        sotietkiem.setSotienbandau(soTienBanDau);
        sotietkiem.setGhichu(ghiChu);
        sotietkiem.setNgaytao(currentDate);

        if(db.themSotietkiem(sotietkiem)){
            Toast.makeText(this.getActivity(),"Thêm thành công!",Toast.LENGTH_SHORT).show();
            changeVisibleView(View.VISIBLE, View.GONE, View.GONE);
        }else{
            Toast.makeText(this.getActivity(),"Thêm thất bại!",Toast.LENGTH_SHORT).show();
        }
    }

    private void changeVisibleView(int main, int them, int detail){
        if(main == View.VISIBLE){
            CapNhatDanhSachSoTietKiem();
        }else if(them == View.VISIBLE){
            txtTenSo.setText("");
            txtTienMucTieu.setText("");
            txtTienBanDau.setText("");
            txtGhiChu.setText("");
        }else if(detail == View.VISIBLE){
            txtGuiVao.setText("");
            txtRutRa.setText("");
        }
        vBoxMain.setVisibility(main);
        vBoxThem.setVisibility(them);
        vBoxDetail.setVisibility(detail);


/*
        if(vBoxMain.getVisibility() == View.VISIBLE){
            txtTenSo.setText("");
            txtTienMucTieu.setText("");
            txtTienBanDau.setText("");
            txtGhiChu.setText("");
            vBoxMain.setVisibility(View.GONE);
            vBoxThem.setVisibility(View.VISIBLE);
        }else{
            vBoxMain.setVisibility(View.VISIBLE);
            vBoxThem.setVisibility(View.GONE);
        }
*/
    }
}
