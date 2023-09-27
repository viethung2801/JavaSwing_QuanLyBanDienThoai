package viewmodel;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HoaDonResponse {

    private int id;
    private String maHoaDon;
    private LocalDateTime ngayTao;
    private LocalDateTime ngayThanhToan;
    private long tienGiam;
    private long tongTien;
    private long tienKhachDua;
    private long tienThua;
    private boolean traGop;
    private long tienTraTruoc;
    private long tienThieu;
    private boolean hinhThucThanhToan;
    private String maGiaoDichChuyenKhoan;

    private String tenNhanVien;
    private String tenKhachHang;
    private String maPhieuGiamGia;

    @Override
    public String toString() {
        return "HoaDonResponse{" + "id=" + id + ", maHoaDon=" + maHoaDon + ", ngayTao=" + ngayTao + ", ngayThanhToan=" + ngayThanhToan + ", tienGiam=" + tienGiam + ", tongTien=" + tongTien + ", tienKhachDua=" + tienKhachDua + ", tienThua=" + tienThua + ", traGop=" + traGop + ", tienTraTruoc=" + tienTraTruoc + ", tienThieu=" + tienThieu + ", hinhThucThanhToan=" + hinhThucThanhToan + ", maGiaoDichChuyenKhoan=" + maGiaoDichChuyenKhoan + ", tenNhanVien=" + tenNhanVien + ", tenKhachHang=" + tenKhachHang + ", maPhieuGiamGia=" + maPhieuGiamGia + '}';
    }

    public Object[] toDataRow() {
        NumberFormat nf = NumberFormat.getInstance(new Locale("vn", "VN"));
        return new Object[]{maHoaDon, tenKhachHang, ngayTao.toLocalDate(), ngayThanhToan.toLocalDate(), nf.format(tongTien) + " VND", nf.format(tienGiam) + " VND", tenNhanVien};
    }

}
