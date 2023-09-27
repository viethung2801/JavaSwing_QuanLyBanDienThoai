package model;

import java.time.LocalDate;
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
@Table(name = "KhachHang")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "HoTen")
    private String hoTen;

    @Column(name = "Email")
    private String email;

    @Column(name = "Sdt")
    private String sdt;

    @Column(name = "GioiTinh")
    private boolean gioiTinh;

    @Column(name = "NgaySinh")
    private LocalDate ngaySinh;

    @Column(name = "DiaChi")
    private String diaChi;

    @Column(name = "TrangThai")
    private int trangThai;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "IdTheTichDiem")
    private TheTichDiem theTichDiem;
}
