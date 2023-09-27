/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

import java.time.LocalDateTime;

/**
 *
 * @author Ma
 */
public class DoanhThuThongKeResponse {
    private LocalDateTime ngay;
    private int thangNam;
    private Long doanhThu;

    public DoanhThuThongKeResponse() {
    }

    public DoanhThuThongKeResponse(LocalDateTime ngay, Long doanhThu) {
        this.ngay = ngay;
        this.doanhThu = doanhThu;
    }

    public DoanhThuThongKeResponse(int thangNam, Long doanhThu) {
        this.thangNam = thangNam;
        this.doanhThu = doanhThu;
    }

    public LocalDateTime getNgay() {
        return ngay;
    }

    public void setNgay(LocalDateTime ngay) {
        this.ngay = ngay;
    }

    public int getThangNam() {
        return thangNam;
    }

    public void setThangNam(int thangNam) {
        this.thangNam = thangNam;
    }

    public Long getDoanhThu() {
        return doanhThu;
    }

    public void setDoanhThu(Long doanhThu) {
        this.doanhThu = doanhThu;
    }

    @Override
    public String toString() {
        return "DoanhThuThongKeResponse{" + "ngay=" + ngay + ", thangNam=" + thangNam + ", doanhThu=" + doanhThu + '}';
    }


    
}
