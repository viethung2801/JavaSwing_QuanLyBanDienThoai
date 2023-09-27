package viewmodel;

import java.text.NumberFormat;
import java.util.Locale;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HoaDonChiTietResponse {

    private int id;
    private String tenDienThoai;
    private long donGia;
    private String imei;

    public Object[] toDataRow() {
        NumberFormat nf = NumberFormat.getInstance(new Locale("vn", "VN"));
        return new Object[]{tenDienThoai, nf.format(donGia) + " VND", imei};
    }

    @Override
    public String toString() {
        return "HoaDonChiTietResponse{" + "id=" + id + ", tenDienThoai=" + tenDienThoai + ", donGia=" + donGia + ", imei=" + imei + '}';
    }

}
