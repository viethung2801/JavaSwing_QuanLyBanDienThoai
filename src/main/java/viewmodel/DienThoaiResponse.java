package viewmodel;

import java.text.NumberFormat;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.enums.LoaiManHinh;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DienThoaiResponse {

    private int id;
    private String maDT;
    private String tenDT;
    private String moTa;
    private int dungLuongPin;
    private int rom;
    private int ram;
    private String cpu;
    private long giaNhap;
    private long giaBan;
    private int soLuong;
    private String hinhAnh;

    private String heDieuHanh;
    private String hang;
    private String dongSanPham;
    private String mauSac; // tenMauSac

    private int cameraChinh;
    private int cameraPhu;
    private int cameraGocRong;
    private int cameraTele;

    private float kichThuoc;
    private String doPhanGiai;
    private LoaiManHinh loaiManHinh;

    public DienThoaiResponse(int id) {
        this.id = id;
    }
    
    public DienThoaiResponse(String maDT, String tenDT, int soLuong) {
        this.maDT = maDT;
        this.tenDT = tenDT;
        this.soLuong = soLuong;
    }

    @Override
    public String toString() {
        return "DienThoaiResponse{" + "id=" + id + ", maDT=" + maDT + ", tenDT=" + tenDT + ", moTa=" + moTa + ", dungLuongPin=" + dungLuongPin
                + ", rom=" + rom + ", ram=" + ram + ", cpu=" + cpu + ", giaNhap=" + giaNhap + ", giaBan=" + giaBan + ", soLuong=" + soLuong + ", hinhAnh=" + hinhAnh
                + ", heDieuHanh=" + heDieuHanh + ", hang=" + hang + ", dongSanPham=" + dongSanPham + ", mauSac=" + mauSac + ", cameraChinh="
                + cameraChinh + ", cameraPhu=" + cameraPhu + ", cameraGocRong=" + cameraGocRong + ", cameraTele=" + cameraTele + ", kichThuoc="
                + kichThuoc + ", doPhanGiai=" + doPhanGiai + ", loaiManHinh=" + loaiManHinh + '}';
    }

    // dùng trong view sản phẩm
    public Object[] toDataRow() {
        NumberFormat nf = NumberFormat.getInstance(new Locale("vn", "VN"));
        return new Object[]{tenDT, hang, ram + " GB", rom + " GB", dungLuongPin + " mAh", soLuong, nf.format(giaBan) + " VND"};
    }

    // dùng trong view bán hàng
    public Object[] toDataRow2() {
        NumberFormat nf = NumberFormat.getInstance(new Locale("vn", "VN"));
        return new Object[]{maDT, tenDT, nf.format(giaBan) + " VND", soLuong};
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DienThoaiResponse other = (DienThoaiResponse) obj;
        return this.id == other.id;
    }
    
    
}
