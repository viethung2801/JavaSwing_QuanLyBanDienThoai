package viewmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DongSanPhamResponse {

    private int id;
    private String ten;
    private boolean trangThai;
    private String tenHang;

    @Override
    public String toString() {
        return "DongSanPhamResponse{" + "id=" + id + ", ten=" + ten + ", trangThai=" + trangThai + ", tenHang=" + tenHang + '}';
    }

    public Object[] toDataRow() {
        return new Object[]{ten, tenHang};
    }

}
