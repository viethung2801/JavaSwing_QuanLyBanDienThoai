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
public class NhanVienResponse {

    private int id;
    private String hoTen;
    private boolean gioiTinh;
    private String sdt;
    private LocalDate ngaySinh;
    private String diaChi;
    private String email;
    private boolean chucVu;
    private boolean trangThai;
    private String hinhAnh;
    private String taiKhoan;
    private String matKhau;

    @Override
    public String toString() {
        return "NhanVienResponse{" + "id=" + id + ", hoTen=" + hoTen + ", gioiTinh=" + gioiTinh + ", sdt=" + sdt + ", ngaySinh=" + ngaySinh + ", diaChi=" + diaChi + ", email=" + email + ", chucVu=" + chucVu + ", trangThai=" + trangThai + ", hinhAnh=" + hinhAnh + ", taiKhoan=" + taiKhoan + ", matKhau=" + matKhau + '}';
    }

    public NhanVienResponse(String hoTen, boolean gioiTinh, String sdt, LocalDate ngaySinh, String diaChi, String email, boolean chucVu, boolean trangThai, String hinhAnh, String taiKhoan, String matKhau) {
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.email = email;
        this.chucVu = chucVu;
        this.trangThai = trangThai;
        this.hinhAnh = hinhAnh;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
    }
    
    
}
