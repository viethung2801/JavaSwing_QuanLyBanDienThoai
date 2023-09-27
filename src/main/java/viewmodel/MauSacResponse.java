package viewmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MauSacResponse {

    private int id;
    private String maMauSac;
    private String tenMauSac;
    private boolean trangThai;

    @Override
    public String toString() {
        return "MauSacResponse{" + "id=" + id + ", maMauSac=" + maMauSac + ", tenMauSac=" + tenMauSac + ", trangThai=" + trangThai + '}';
    }

    public Object[] toDataRow() {
        return new Object[]{maMauSac, tenMauSac};
    }
}
