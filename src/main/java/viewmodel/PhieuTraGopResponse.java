package viewmodel;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PhieuTraGopResponse {
    
    private int id;
    private String maPhieu;
    private long tongPhaiTra;
    private float laiSuat;
    private int kyHan;
    private LocalDate ngayTao;
    private int ngayDong;
    private long phaiTra;
    private boolean trangThai;
    private String tenKhachHang;

    public PhieuTraGopResponse(int id, String maPhieu, long tongPhaiTra, float laiSuat, int kyHan, LocalDate ngayTao, int ngayDong, long phaiTra, boolean trangThai) {
        this.id = id;
        this.maPhieu = maPhieu;
        this.tongPhaiTra = tongPhaiTra;
        this.laiSuat = laiSuat;
        this.kyHan = kyHan;
        this.ngayTao = ngayTao;
        this.ngayDong = ngayDong;
        this.phaiTra = phaiTra;
        this.trangThai = trangThai;
    }
    
    @Override
    public String toString() {
        return "PhieuTraGopResponse{" + "id=" + id + ", maPhieu=" + maPhieu + ", tongPhaiTra=" + tongPhaiTra + ", laiSuat=" + laiSuat + ", kyHan=" + kyHan + ", ngayTao=" + ngayTao + ", ngayDong=" + ngayDong + ", phaiTra=" + phaiTra + ", trangThai=" + trangThai + ", tenKhachHang=" + tenKhachHang + '}';
    }

}
