package model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PhieuGiamGiaChiTiet")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PhieuGiamGiaChiTiet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "NgayBatDau")
    private LocalDate ngayBatDau;
    
    @Column(name = "NgayKetThuc")
    private LocalDate ngayKetThuc;
    
    @Column(name = "LuotSuDung")
    private int luotSuDung;
    
    @Column(name = "DienKien")
    private long dieuKien; // số tiền tối thiểu của hóa đơn để được giảm giá
    
    @Column(name = "GiaTri")
    private float giaTri; // Ví dụ: 5% 4.5% 8 %
    
    @Column(name = "TrangThai")
    private int trangThai;
}
