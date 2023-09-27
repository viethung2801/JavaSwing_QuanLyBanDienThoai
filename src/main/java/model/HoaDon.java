package model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "HoaDon")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "MaHoaDon")
    private String maHoaDon;

    @Column(name = "NgayTao")
    private LocalDateTime ngayTao;

    @Column(name = "NgayThanhToan")
    private LocalDateTime ngayThanhToan;

    @Column(name = "TongTien")
    private long tongTien;

    @Column(name = "TienGiam")
    private long tienGiam;

    @Column(name = "TienKhachDua")
    private long tienKhachDua;

    @Column(name = "TienThua")
    private long tienThua;

    @Column(name = "TraGop")
    private boolean traGop;

    @Column(name = "TienTraTruoc")
    private long tienTraTruoc;

    @Column(name = "TienThieu")
    private long tienThieu;

    @Column(name = "HinhThucThanhToan")
    private boolean hinhThucThanhToan;

    @Column(name = "MaGiaoDichChuyenKhoan")
    private String maGiaoDichChuyenKhoan;

    @ManyToOne
    @JoinColumn(name = "IdNhanVien")
    private NhanVien nhanVien;

    @ManyToOne
    @JoinColumn(name = "IdKhachHang")
    private KhachHang khachHang;

    @ManyToOne
    @JoinColumn(name = "IdPhieuGiamGia")
    private PhieuGiamGia phieuGiamGia;

    @OneToMany(mappedBy = "hoaDon", cascade = CascadeType.ALL)
    private Set<HoaDonChiTiet> hoaDonChiTiets = new HashSet<>();

    public void addHoaDonChiTiet(HoaDonChiTiet hoaDonChiTiet) {
        if (hoaDonChiTiet != null) {
            this.hoaDonChiTiets.add(hoaDonChiTiet);
            hoaDonChiTiet.setHoaDon(this);
        }
    }
}
