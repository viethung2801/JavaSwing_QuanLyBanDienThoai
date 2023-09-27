package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PhieuGiamGia")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PhieuGiamGia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "MaPhieu")
    private String maPhieu;

    @Column(name = "TenPhieu")
    private String tenPhieu;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdPhieuChiTiet")
    private PhieuGiamGiaChiTiet phieuGiamGiaChiTiet;
}
