/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.text.NumberFormat;
import java.time.LocalDate;

/**
 *
 * @author Administrator
 */
public class PhieuTraGopViewModel {

    private int id;
    private LocalDate ngayDong;
    private String maPhieu;
    private String khachHang;
    private String maDon;
    private long tongTien;
    private long daTra;
    private long conNo;
    private boolean trangThai;

    public PhieuTraGopViewModel() {
    }

    public PhieuTraGopViewModel(LocalDate ngayDong, String maPhieu, String khachHang, String maDon, long tongTien, long daTra, long conNo, boolean trangThai) {
        this.ngayDong = ngayDong;
        this.maPhieu = maPhieu;
        this.khachHang = khachHang;
        this.maDon = maDon;
        this.tongTien = tongTien;
        this.daTra = daTra;
        this.conNo = conNo;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getNgayDong() {
        return ngayDong;
    }

    public void setNgayDong(LocalDate ngayDong) {
        this.ngayDong = ngayDong;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(String khachHang) {
        this.khachHang = khachHang;
    }

    public String getMaDon() {
        return maDon;
    }

    public void setMaDon(String maDon) {
        this.maDon = maDon;
    }

    public long getTongTien() {
        return tongTien;
    }

    public void setTongTien(long tongTien) {
        this.tongTien = tongTien;
    }

    public long getDaTra() {
        return daTra;
    }

    public void setDaTra(long daTra) {
        this.daTra = daTra;
    }

    public long getConNo() {
        return conNo;
    }

    public void setConNo(long conNo) {
        this.conNo = conNo;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String convertVND(long value) {
        NumberFormat numberFormat = NumberFormat.getInstance();
        String fomatValue = numberFormat.format(value);
        return fomatValue;
    }

    public Object[] toDataRow() {
        return new Object[]{ngayDong, maPhieu, khachHang, maDon, convertVND(tongTien), convertVND(daTra), convertVND(tongTien - daTra), trangThai == true ? "Hoàn Thành" : "Chưa Hoàn Thành"};
    }
}
