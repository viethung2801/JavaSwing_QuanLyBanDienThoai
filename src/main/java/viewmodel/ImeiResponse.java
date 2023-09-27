package viewmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImeiResponse {

    private int id;
    private String imei;
    private String tenDienThoai;
    private int idDienThoai;

    public ImeiResponse(int id, String imei) {
        this.id = id;
        this.imei = imei;
    }
    
    @Override
    public String toString() {
        return "ImeiResponse{" + "id=" + id + ", imei=" + imei + ", tenDienThoai=" + tenDienThoai + '}';
    }
    
    public Object[] toDataRow() {
        return new Object[]{id, imei};
    }
}
