package com.example.nhatlam.nhomnm_quanlychitieu.Fragments.SuKienChiTieu;

/**
 * Created by MinhLuan on 2/7/2017.
 */

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class MyDialogFragment extends DialogFragment implements     DatePickerDialog.OnDateSetListener {

    OnDateSetListener mDateSetListener;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(), this, yy, mm, dd);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        if (this.mDateSetListener != null) {
            this.mDateSetListener.onDateSet(view, year, monthOfYear, dayOfMonth);
        }
    }

    public void setOnDateSetListener(OnDateSetListener dateSetListener) {
        this.mDateSetListener = dateSetListener;
    }

    public interface OnDateSetListener {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth);
    }

}