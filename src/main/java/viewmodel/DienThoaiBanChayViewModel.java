/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package viewmodel;

/**
 *
 * @author Administrator
 */
public class DienThoaiBanChayViewModel {

    private String tenDT;
    private long daBan;

    public DienThoaiBanChayViewModel() {
    }

    public DienThoaiBanChayViewModel(String tenDT, long daBan) {
        this.tenDT = tenDT;
        this.daBan = daBan;
    }

    public String getTenDT() {
        return tenDT;
    }

    public void setTenDT(String tenDT) {
        this.tenDT = tenDT;
    }

    public long getDaBan() {
        return daBan;
    }

    public void setDaBan(long daBan) {
        this.daBan = daBan;
    }

    @Override
    public String toString() {
        return "DienThoaiBanChayViewModel{" + "tenDT=" + tenDT + ", daBan=" + daBan + '}';
    }

}
