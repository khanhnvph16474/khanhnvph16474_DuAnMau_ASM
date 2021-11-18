package com.example.khanhnvph16474_duanmau_asm.model;

import java.util.Date;

public class PhieuMuon {
    public int maPM;
    public String maTT;
    public int maTV;
    public int maSach;
    public int tienThue;
    public int traSach;
    public Date ngay;
    public Date gio;

    public PhieuMuon() {
    }

    public PhieuMuon(int maPM, String maTT, int maTV, int maSach, int tienThue, int traSach, Date ngay, Date gio) {
        this.maPM = maPM;
        this.maTT = maTT;
        this.maTV = maTV;
        this.maSach = maSach;
        this.tienThue = tienThue;
        this.traSach = traSach;
        this.ngay = ngay;
        this.gio = gio;
    }


}
