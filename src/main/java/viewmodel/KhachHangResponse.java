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
public class KhachHangResponse {

    private int id; // id KhachHang
    private String hoTen;
    private String email;
    private String sdt;
    private boolean gioiTinh;
    private LocalDate ngaySinh;
    private String diaChi;
    private int trangThai;

    public KhachHangResponse(int id, String hoTen, String email, String sdt, boolean gioiTinh, LocalDate ngaySinh, String diaChi, int trangThai) {
        this.id = id;
        this.hoTen = hoTen;
        this.email = email;
        this.sdt = sdt;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
    }

    public KhachHangResponse(String hoTen, int idThe, String maThe, LocalDate ngayKichHoat, int soDiem, boolean trangThaiThe) {
        this.hoTen = hoTen;
        this.idThe = idThe;
        this.maThe = maThe;
        this.ngayKichHoat = ngayKichHoat;
        this.soDiem = soDiem;
        this.trangThaiThe = trangThaiThe;
    }

    private int idThe;
    private String maThe;
    private LocalDate ngayKichHoat;
    private int soDiem;
    private boolean trangThaiThe;

    @Override
    public String toString() {
        return hoTen + " - " + sdt;
    }

    public Object[] toDataRow() {
        return new Object[]{
            id, hoTen, email, sdt, gioiTinh == true ? "Nam" : "Nữ", ngaySinh, diaChi, soDiem, trangThai == 1 ? true : false
        };
    }

    public Object[] toDataRowTheTichDiem() {
        return new Object[]{
            idThe, maThe, hoTen, ngayKichHoat, soDiem, trangThaiThe == true ? "Hoạt Động" : "Ngừng Hoạt Động"
        };
    }

}
