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
public class PhieuGiamGiaResponse {

    private int id;  // id phieuGiamGia
    private String maPhieu;
    private String tenPhieu;
    private LocalDate ngayBatDau;
    private LocalDate ngayKetThuc;
    private int luotSuDung;
    private long dieuKien;
    /*
    - 0: đang diễn ra
    - 2: sắp diễn ra
    - 1: đã kết thúc
    */
    private float giaTri;
    private int trangThai;

    @Override
    public String toString() {
        return "PhieuGiamGiaResponse{" + "id=" + id + ", maPhieu=" + maPhieu + ", tenPhieu=" + tenPhieu + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc + ", luotSuDung=" + luotSuDung + ", dieuKien=" + dieuKien + ", giaTri=" + giaTri + ", trangThai=" + trangThai + '}';
    }

    public String getNgay() {
        if (trangThai == 0) {
            return "Đang diễn ra";
        } else if (trangThai > 1) {
            return "Sắp diễn ra";
        } else {
            return "Đã kết thúc";
        }
    }

    public Object[] toDataRow() {
        return new Object[]{
            tenPhieu, maPhieu, giaTri + "%", dieuKien, luotSuDung, getNgay()
        };
    }
}
