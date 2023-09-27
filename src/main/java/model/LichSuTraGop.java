package model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "LichSuTraGop")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LichSuTraGop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "MaLichSuTraGop")
    private String ma;

    @Column(name = "NgayThanhToan")
    private LocalDate ngayThanhToan;

    @Column(name = "TongTien")
    private long tongTien;

    @Column(name = "GhiChu")
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "IdPhieuTraGop")
    private PhieuTraGop phieuTraGop;

    @Override
    public String toString() {
        return "LichSuTraGop{" + "ma=" + ma + ", ngayThanhToan=" + ngayThanhToan + ", tongTien=" + tongTien + ", ghiChu=" + ghiChu + ", phieuTraGop=" + phieuTraGop + '}';
    }

    public LichSuTraGop(String ma, LocalDate ngayThanhToan, long tongTien, String ghiChu) {
        this.ma = ma;
        this.ngayThanhToan = ngayThanhToan;
        this.tongTien = tongTien;
        this.ghiChu = ghiChu;
    }

    public Object[] toDataRow() {
        return new Object[]{ngayThanhToan, ma, tongTien, ghiChu};
    }

}
