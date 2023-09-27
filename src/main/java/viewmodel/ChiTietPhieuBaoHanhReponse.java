/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.util.Date;

/**
 *
 * @author virus
 */
public class ChiTietPhieuBaoHanhReponse {

    private int id;
    private String tenKH;
    private String tenDT;
    private String imei;
    private long giaDT;
    private int thoiHan;
    private Date ngayMua;
    private Date ngayHetHan;
    private boolean trangThai;

    public ChiTietPhieuBaoHanhReponse() {
    }

    public ChiTietPhieuBaoHanhReponse(String tenKH, String tenDT, String imei, long giaDT, int thoiHan, Date ngayMua, Date ngayHetHan, boolean trangThai) {
        this.tenKH = tenKH;
        this.tenDT = tenDT;
        this.imei = imei;
        this.giaDT = giaDT;
        this.thoiHan = thoiHan;
        this.ngayMua = ngayMua;
        this.ngayHetHan = ngayHetHan;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTenDT() {
        return tenDT;
    }

    public void setTenDT(String tenDT) {
        this.tenDT = tenDT;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public long getGiaDT() {
        return giaDT;
    }

    public void setGiaDT(long giaDT) {
        this.giaDT = giaDT;
    }

    public int getThoiHan() {
        return thoiHan;
    }

    public void setThoiHan(int thoiHan) {
        this.thoiHan = thoiHan;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
        this.ngayMua = ngayMua;
    }

    public Date getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(Date ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Object[] toRowData() {
        return new Object[]{this.id, this.tenKH, this.tenDT, this.imei, this.giaDT, this.thoiHan, this.ngayMua, this.ngayHetHan, this.trangThai};
    }
}
