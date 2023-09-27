package viewmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HangResponse {
    
    private int id;
    private String tenHang;
    private boolean trangThai;

    @Override
    public String toString() {
        return "HangResponse{" + "id=" + id + ", tenHang=" + tenHang + ", trangThai=" + trangThai + '}';
    }
    
    public Object[] toDataRow() {
        return new Object[]{id, tenHang};
    }
}
