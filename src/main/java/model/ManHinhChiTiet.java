package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.enums.LoaiManHinh;

@Entity
@Table(name = "ManHinhChiTiet")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ManHinhChiTiet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "KichThuocManHinh")
    private float kichThuoc;
    
    @Column(name = "DoPhanGiai")
    private String doPhanGiai;
    
    @Column(name = "LoaiManHinh")
    @Enumerated(EnumType.STRING)
    private LoaiManHinh loaiManHinh;

    @Override
    public String toString() {
        return "ManHinhChiTiet{" + "id=" + id + ", kichThuoc=" + kichThuoc + ", doPhanGiai=" + doPhanGiai + ", loaiManHinh=" + loaiManHinh + '}';
    }
    
}
