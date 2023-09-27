package viewmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HeDieuHanhResponse {

    private int id;
    private String tenHDH;
    private boolean trangThai;

    @Override
    public String toString() {
        return "HeDieuHanhResponse{" + "id=" + id + ", tenHDH=" + tenHDH + ", trangThai=" + trangThai + '}';
    }

    public Object[] toDataRow() {
        return new Object[]{id, tenHDH};
    }

}
