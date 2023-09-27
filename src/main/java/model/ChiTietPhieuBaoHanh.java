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
@Table(name = "ChiTietPhieuBaoHanh")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChiTietPhieuBaoHanh {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    
    @Column(name = "Imei")
    private String imei;
    
    @Column(name = "ThoiHanBaoHanh")
    private int thoiHanBaoHanh;
    
    @Column(name = "NgayMuaHang")
    private LocalDate ngayMuaHang;
    
    @Column(name = "NgayHetHan")
    private LocalDate ngayHetHan;
    
    @Column(name = "MoTa")
    private String moTa;
    
    @Column(name = "TrangThai")
    private boolean trangThai;
}
