package model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PhieuTraGop")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PhieuTraGop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "MaPhieu")
    private String maPhieu;

    @Column(name = "TongPhaiTra")
    private long tongPhaiTra;

    @Column(name = "LaiSuat")
    private float laiSuat;

    @Column(name = "KyHan")
    private int kyHan;

    @Column(name = "NgayTao")
    private LocalDate ngayTao;

    @Column(name = "PhaiTra")
    private long phaiTra;

    @Column(name = "TrangThai")
    private boolean trangThai;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdHoaDon")
    private HoaDon hoaDon;

    @OneToMany(mappedBy = "phieuTraGop", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<LichSuTraGop> lichSuSet = new HashSet<>();

    public PhieuTraGop(String maPhieu, long tongPhaiTra, float laiSuat, int kyHan, LocalDate ngayTao, long phaiTra, boolean trangThai, HoaDon hoaDon) {
        this.maPhieu = maPhieu;
        this.tongPhaiTra = tongPhaiTra;
        this.laiSuat = laiSuat;
        this.kyHan = kyHan;
        this.ngayTao = ngayTao;
        this.phaiTra = phaiTra;
        this.trangThai = trangThai;
        this.hoaDon = hoaDon;
    }

    public void addLichSuTraGop(LichSuTraGop lichSuTraGop) {
        if (lichSuTraGop != null) {
            this.lichSuSet.add(lichSuTraGop);
        }
        lichSuTraGop.setPhieuTraGop(this);
    }

    @Override
    public String toString() {
        return "PhieuTraGop{" + "id=" + id + ", maPhieu=" + maPhieu + ", tongPhaiTra=" + tongPhaiTra + ", laiSuat=" + laiSuat + ", kyHan=" + kyHan + ", ngayTao=" + ngayTao + ", phaiTra=" + phaiTra + ", trangThai=" + trangThai + ", hoaDon=" + hoaDon + '}';
    }

    public long getTongTienDaTra() {
        long tongTien = 0;
        for (LichSuTraGop lichSuTraGop : lichSuSet) {
            tongTien += lichSuTraGop.getTongTien();
        }
        return tongTien;
    }
}
